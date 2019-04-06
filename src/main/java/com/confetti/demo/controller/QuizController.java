package com.confetti.demo.controller;

import com.confetti.demo.dto.QuizDTO;
import com.confetti.demo.model.Choice;
import com.confetti.demo.model.Question;
import com.confetti.demo.model.Quiz;
import com.confetti.demo.service.ChoiceService;
import com.confetti.demo.service.QuestionService;
import com.confetti.demo.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController extends BaseAPIController{

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

//        Quiz quiz = createQuiz(quizDTO);

        List<Question> questions = quiz.getQuestions();

        for(int i = 0; i < questions.size(); i++) {
            Integer questionNumber = i + 1;

            Question question = questions.get(i);

            List<Choice> choices = question.getChoices();

            question.setQuestionNumber(questionNumber);

            for (Choice choice : choices) {
                choice.setQuestion(question);
            }
            question.setQuiz(quiz);
        }

        quiz.setQuestions(questions);
//
        return quizService.save(quiz);
    }


    public Quiz createQuiz(QuizDTO quizDTO) {

        return null;
    }

}
