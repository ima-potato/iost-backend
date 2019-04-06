package com.confetti.demo.dto;

import com.confetti.demo.model.Choice;

import java.util.List;
import java.util.Map;

public class HashedDTO {
    private Long quizID;
    private List<Long> questionIDList;
    private Map<String, Choice> hashMap;
    private String publicKey;

    public HashedDTO(Long quizID, List<Long> questionIDList, Map<String, Choice> hashMap, String publicKey) {
        this.quizID = quizID;
        this.questionIDList = questionIDList;
        this.hashMap = hashMap;
        this.publicKey = publicKey;
    }

    public Long getQuizID() {
        return quizID;
    }

    public void setQuizID(Long quizID) {
        this.quizID = quizID;
    }

    public List<Long> getQuestionIDList() {
        return questionIDList;
    }

    public void setQuestionIDList(List<Long> questionIDList) {
        this.questionIDList = questionIDList;
    }

    public Map<String, Choice> getHashMap() {
        return hashMap;
    }

    public void setHashMap(Map<String, Choice> hashMap) {
        this.hashMap = hashMap;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
