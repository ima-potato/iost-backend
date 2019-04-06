package com.confetti.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quizzes")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String quizName;

    @OneToMany
            (mappedBy = "quiz",
                    fetch=FetchType.LAZY,
                    cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @Column
    private BigDecimal prizepool;

    @Column
    private String sponsor;

    @Column
    private LocalDateTime startDateTime;

    @Column
    private String status;

    public Quiz(String quizName, List<Question> questions, BigDecimal prizepool, String sponsor, LocalDateTime startDateTime, String status) {
        this.quizName = quizName;
        this.questions = questions;
        this.prizepool = prizepool;
        this.sponsor = sponsor;
        this.startDateTime = startDateTime;
        this.status = status;
    }



    public Quiz() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public BigDecimal getPrizepool() {
        return prizepool;
    }

    public void setPrizepool(BigDecimal prizepool) {
        this.prizepool = prizepool;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
