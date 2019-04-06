package com.confetti.demo.service;

import com.confetti.demo.model.Choice;
import com.confetti.demo.repository.ChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoiceService {


    private ChoiceRepository choiceRepository;


    @Autowired
    public ChoiceService(ChoiceRepository choiceRepository){
        this.choiceRepository = choiceRepository;

    }


    public List<Choice> getAllChoicesByQuestionId(Long questionId) {
        return choiceRepository.findAllByQuestion_Id(questionId);
    }

    public List<Choice> getAllChoicesByQuestionIdAndIsCorrect(Long questionId) {
        return choiceRepository.findAllByQuestion_IdAndCorrectIsTrue(questionId);
    }

    public List<Choice> saveAll(List<Choice> choices) {
        return choiceRepository.saveAll(choices);
    }


}
