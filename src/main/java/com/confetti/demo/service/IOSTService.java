package com.confetti.demo.service;

import com.confetti.demo.model.Choice;
import com.confetti.demo.model.Question;
import com.confetti.demo.model.Quiz;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import iost.Client;
import iost.IOST;
import iost.Keychain;
import iost.crypto.Base58;
import iost.crypto.Ed25519;
import iost.crypto.KeyPair;
import iost.model.transaction.Transaction;
import iost.model.transaction.TxReceipt;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Service
public class IOSTService {
    private static final String CONTRACT_ID = "ContractHQP1nTV7dHN6X8H7v5pDwxTgDjBYvQ68HP8eJxquNnXh";
    private Client client;
    private IOST iost;
    private Keychain account;

    private ObjectMapper objectMapper;

    @Autowired
    public IOSTService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.client = new Client("http://localhost:30001/");

        try {
            byte[] privateKeyBytes = Base58.decode("2yquS3ySrGWPEKywCPzX4RTJugqRh7kJSo5aehsLYPEWkUxBWA39oMrZ7ZxuM4fgyXYs2cPwh5n8aNNpH5x2VyK1");
            this.account = new Keychain("admin");
            KeyPair kp = new Ed25519(privateKeyBytes);
            account.addKey("owner", kp);
            account.addKey("active", kp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.iost = new IOST();
    }

    public void createQuiz(Quiz quiz) {
        List<Map<String, String>> hashList = new ArrayList<>();

        for (Question question : quiz.getQuestions()) {
            Map<String, String> map = new HashMap<>();
            map.put("questionHash", hashString(question.getQuestion()));
            Choice correctChoice = question.getChoices().stream()
                    .filter(Choice::isCorrect)
                    .collect(Collectors.toList()).get(0);
            map.put("answerHash", hashString(correctChoice.getDescription()));
            hashList.add(map);
        }

        try {
            Transaction tx = iost.callABI(CONTRACT_ID,
                    "createQuiz",
                    quiz.getId(),
                    quiz.getQuizName(),
                    quiz.getSponsor(),
                    quiz.getPrizepool(),
                    objectMapper.writeValueAsString(hashList));
            tx.addApprove("iost", "10000");
            account.publish(tx);
            this.sendTxAndPrint(tx);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void recordCorrectAnswer(Question question, String accountName) {
        Choice correctChoice = question.getChoices().stream()
                .filter(Choice::isCorrect)
                .collect(Collectors.toList()).get(0);

        Transaction tx = iost.callABI(CONTRACT_ID,
                "recordCorrectAnswer",
                question.getQuiz().getId(),
                question.getQuestionNumber(),
                hashString(question.getQuestion()),
                hashString(correctChoice.getDescription()),
                accountName);

        account.publish(tx);
        this.sendTxAndPrint(tx);
    }

    public void markQuizFinished(Long quizId) {
        Transaction tx = iost.callABI(CONTRACT_ID,
                "markQuizFinished",
                quizId);
        account.publish(tx);
        this.sendTxAndPrint(tx);
    }

    private String hashString(String input) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        return new String(Hex.encode(hash));
    }

    private void sendTxAndPrint(Transaction tx) {
        String txHash = null;
        try {
            txHash = this.client.sendTx(tx);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Hash Tx：" + txHash);
        try {
            TxReceipt receipt = this.client.polling(txHash, 1000, 90);
            if (receipt.status_code.equals("SUCCESS")) {
                System.out.println("Receipt，gas usage : " + receipt.gas_usage);
            } else {
                System.out.println("Receipt，gas log：");
                System.out.println(receipt.message);
            }
        } catch (TimeoutException e) {
            System.out.println("ERROR，message:");
            e.printStackTrace();
        }
    }
}
