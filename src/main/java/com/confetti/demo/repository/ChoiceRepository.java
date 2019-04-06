package com.confetti.demo.repository;

import com.confetti.demo.model.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {

    public List<Choice> findAllByQuestion_Id(Long questionId);
    public List<Choice> findAllByQuestion_IdAndCorrectIsTrue(Long questionId);

}
