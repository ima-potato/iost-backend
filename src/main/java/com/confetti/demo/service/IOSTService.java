package com.confetti.demo.service;

import iost.Client;
import iost.IOST;
import iost.Keychain;
import iost.crypto.Base58;
import iost.crypto.Ed25519;
import iost.crypto.KeyPair;
import iost.model.account.Account;
import iost.model.transaction.Transaction;
import iost.model.transaction.TxReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class IOSTService {

    private String URL = "192.168.50.23:30002";
    private String accountName = "User";
    private byte[] privateKey;

    private Client client;
    private IOST iost;
    private Keychain account;

    @Autowired
    public IOSTService(Client client, IOST iost, Keychain account) {
        this.client = client;
        this.iost = iost;
        this.account = account;

        this.client = new Client(this.URL);

        try {
            this.privateKey = Base58.decode("2yquS3ySrGWPEKywCPzX4RTJugqRh7kJSo5aehsLYPEWkUxBWA39oMrZ7ZxuM4fgyXYs2cPwh5n8aNNpH5x2VyK1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.account = new Keychain(accountName);
        KeyPair kp = new Ed25519(privateKey);
        account.addKey("owner", kp);
        account.addKey("active", kp);

        this.iost = new IOST();
    }

    private void sendTxAndPrint(Transaction tx) {
        String txHash = null;
        try {
            txHash = this.client.sendTx(tx);
        } catch (IOException e) {
            System.out.println("");
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

    public Transaction callContract (String cid, String abi, Object data) {
        return iost.callABI(cid,abi,data);
    }

    public void createQuiz(Object data) {
        // make a call
        Transaction tx = iost.callABI("Contract4BxHBkLoAqZfZLNJKiFPE722uDi9r4z7aAT9oaLWgRez", "createQuiz", data); // 智能合约，abi，抵押者，收益者，iost数量
        tx.addApprove("iost", "10000");

        // sign
        account.publish(tx);

        this.sendTxAndPrint(tx);
    }
}
