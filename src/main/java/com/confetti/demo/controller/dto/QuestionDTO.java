package com.confetti.demo.controller.dto;

import java.util.List;

public class QuestionDTO {

    private String question;
    private Integer questionNumber;
    private List<ChoiceDTO> choices;

    public QuestionDTO(String question, Integer questionNumber, List<ChoiceDTO> choices) {
        this.question = question;
        this.questionNumber = questionNumber;
        this.choices = choices;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public List<ChoiceDTO> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceDTO> choices) {
        this.choices = choices;
    }
}
