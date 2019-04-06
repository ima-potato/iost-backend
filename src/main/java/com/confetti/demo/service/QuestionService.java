package com.confetti.demo.service;

import com.confetti.demo.model.Question;
import com.confetti.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;
    private ChoiceService choiceService;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, ChoiceService choiceService) {
        this.questionRepository = questionRepository;
        this.choiceService = choiceService;
    }

    public List<Question> findAllByQuizId(Long quizId) {
        return questionRepository.findAllByQuiz_Id(quizId);
    }

    public List<Question> findAllByQuizIdOrderByQuestionNumber(Long quizId) {
        return questionRepository.findAllByQuiz_IdOrderByQuestionNumber(quizId);
    }

    public Question findAllByQuizIdAndAndQuestionNumber(Long quizId, Integer questionNumber) {
        return questionRepository.findAllByQuiz_IdAndQuestionNumber(quizId, questionNumber);
    }

    public Iterable<Question> saveAll(List<Question> questions) {
        return questionRepository.saveAll(questions);
    }

}
