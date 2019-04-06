package com.confetti.demo.dto;

import com.confetti.demo.model.Choice;

public class SubmissionDTO {

    private Long quizId;
    private Long questionId;
    private String publicKey;
    private Choice choice;

    public SubmissionDTO(Long quizId, Long questionId, String publicKey, Choice choice) {
        this.quizId = quizId;
        this.questionId = questionId;
        this.publicKey = publicKey;
        this.choice = choice;
    }

    public SubmissionDTO() {
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public Choice getChoice() {
        return choice;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }
}
