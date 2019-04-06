package com.confetti.demo.repository;

import com.confetti.demo.model.Choice;
import com.confetti.demo.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    public List<Question> findAllByQuiz_Id(Long quizId);
    public List<Question> findAllByQuiz_IdOrderByQuestionNumber(Long quizId);
    public Question findAllByQuiz_IdAndQuestionNumber(Long quizId, Integer questionNumber);

}
