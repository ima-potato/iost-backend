package com.confetti.demo.controller;

import com.confetti.demo.model.Choice;
import com.confetti.demo.model.Question;
import com.confetti.demo.model.Quiz;
import com.confetti.demo.service.IOSTService;
import com.confetti.demo.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    private QuizService quizService;
    private IOSTService iostService;

    @Autowired
    public QuizController(QuizService quizService,
                          IOSTService iostService) {
        this.quizService = quizService;
        this.iostService = iostService;
    }

    @GetMapping("")
    public List<Quiz> getAllPending() {
        return quizService.findAllByStatus("PENDING");
    }

    @PostMapping("")
    public Quiz save(@RequestBody Quiz quiz) {
        List<Question> questions = quiz.getQuestions();

        for (Question question : questions) {
            List<Choice> choices = question.getChoices();
            for (Choice choice : choices) {
                choice.setQuestion(question);
            }
            question.setQuiz(quiz);
        }

        quiz.setQuestions(questions);
        Quiz savedQuiz = quizService.save(quiz);

        iostService.createQuiz(savedQuiz);

        return savedQuiz;
    }
}
