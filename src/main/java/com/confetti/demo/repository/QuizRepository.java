package com.confetti.demo.repository;

import com.confetti.demo.model.Question;
import com.confetti.demo.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findAllByStatus(String status);
    List<Quiz> findAllByStatusOrderByStartDateTime(String status);
}
