package com.confetti.demo.service;


import com.confetti.demo.model.Quiz;
import com.confetti.demo.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    private QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> findAllByStatus(String status) {
        return quizRepository.findAllByStatus(status);
    }

    public List<Quiz> findAllByStatusOrderByStartDateTime(String status) {
        return quizRepository.findAllByStatusOrderByStartDateTime(status);
    }

    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }
}
