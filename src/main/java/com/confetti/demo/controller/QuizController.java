package com.confetti.demo.controller;

import com.confetti.demo.dto.HashedDTO;
import com.confetti.demo.dto.QuizDTO;
import com.confetti.demo.model.Choice;
import com.confetti.demo.model.Question;
import com.confetti.demo.model.Quiz;
import com.confetti.demo.service.ChoiceService;
import com.confetti.demo.service.QuestionService;
import com.confetti.demo.service.QuizService;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private QuizService quizService;
    private ChoiceService choiceService;
    private QuestionService questionService;


    @Autowired
    public QuizController(QuizService quizService, QuestionService questionService, ChoiceService choiceService) {
        this.quizService = quizService;
        this.choiceService = choiceService;
        this.questionService = questionService;
    }

    @GetMapping("")
    public List<Quiz> getAllPending() {
        return quizService.findAllByStatus("PENDING");
    }

    @PostMapping("")
    public Quiz save(@RequestBody Quiz quiz) {

        List<Question> questions = quiz.getQuestions();

        for(Question question: questions ) {
            List<Choice> choices = question.getChoices();
            for(Choice choice: choices) {
                choice.setQuestion(question);
            }
            question.setQuiz(quiz);
        }

        quiz.setQuestions(questions);
//
        return quizService.save(quiz);
    }

    public HashedDTO buildDTO (Quiz quiz, String publicKey) {
        String questionHash;
        Choice answerHash;
        List<Long> questionIDList = new ArrayList<>();
        Map<String, Choice> mapping = new HashMap<>();

        for (Question q : quiz.getQuestions()
        ) {
            questionIDList.add(q.getId());
            questionHash = HashFunction(q.getQuestion());
            answerHash = q.getChoices().stream()
                    .filter(choice -> choice.isCorrect() == true)
                    .collect(Collectors.toList()).get(0);
            mapping.put(questionHash, answerHash);
        }

        return new HashedDTO(quiz.getId(),questionIDList,mapping,publicKey);
    }

    public String HashFunction (String input) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = digest.digest(
                input.getBytes(StandardCharsets.UTF_8));
        String sha256hex = new String(Hex.encode(hash));

        return sha256hex;
    }

}
