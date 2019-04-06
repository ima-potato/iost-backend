package com.confetti.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "choices")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Choice {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    private Boolean correct;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinTable
            (name="choices_question",
            joinColumns = @JoinColumn(name = "choice_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Question question;

    public Choice(String description, Boolean correct, Question question) {
        this.description = description;
        this.correct = correct;
        this.question = question;
    }

    public Choice(String description, Boolean correct) {
        this.description = description;
        this.correct = correct;
    }

    public Choice() {
    }

    public Boolean getCorrect() {
        return correct;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }
}
