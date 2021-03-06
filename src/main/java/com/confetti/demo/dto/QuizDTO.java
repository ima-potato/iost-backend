package com.confetti.demo.dto;

import com.confetti.demo.model.Choice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public class QuizDTO {

    private String quizName;
    private LocalDateTime startDateTime;
    private Map<String, Choice> hashedQuestions;
    private BigDecimal prizePool;
    private String sponsor;
    private String status;

    public QuizDTO(String quizName, LocalDateTime startDateTime, Map<String,Choice> questions, BigDecimal prizePool, String sponsor, String status) {
        this.quizName = quizName;
        this.startDateTime = startDateTime;
        this.hashedQuestions = questions;
        this.prizePool = prizePool;
        this.sponsor = sponsor;
        this.status = status;
    }

    public QuizDTO() {
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Map<String, Choice> getHashedQuestions() {
        return hashedQuestions;
    }

    public void setHashedQuestions(Map<String, Choice> hashedQuestions) {
        this.hashedQuestions = hashedQuestions;
    }

    public BigDecimal getPrizePool() {
        return prizePool;
    }

    public void setPrizePool(BigDecimal prizePool) {
        this.prizePool = prizePool;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
