package com.confetti.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String question;

    @Column
    private Integer questionNumber;

    @OneToMany
            (
            mappedBy = "question",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Choice> choices = new ArrayList<>();

    @ManyToOne
    @JoinTable(name="questions_quiz",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "quiz_id"))
    private Quiz quiz;

    public Question(String question, List<Choice> choices) {
        this.question = question;
        this.choices = choices;
    }

    public Question(String question, List<Choice> choices, Quiz quiz) {
        this.question = question;
        this.choices = choices;
        this.quiz = quiz;
    }

    public Question(String question, Integer questionNumber, List<Choice> choices, Quiz quiz) {
        this.question = question;
        this.questionNumber = questionNumber;
        this.choices = choices;
        this.quiz = quiz;
    }

    public Question() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }


}
