package com.confetti.demo.dto;

public class SubmissionABI {

    private Long quizId;
    private Long questionId;
    private String questionHash;
    private String answerHash;
    private String publicKey;
//    private Boolean finished;

    public SubmissionABI(Long quizId, Long questionId, String questionHash, String answerHash, String publicKey, Boolean finished) {
        this.quizId = quizId;
        this.questionId = questionId;
        this.questionHash = questionHash;
        this.answerHash = answerHash;
        this.publicKey = publicKey;
//        this.finished = finished;
    }

    public SubmissionABI() {
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

    public String getQuestionHash() {
        return questionHash;
    }

    public void setQuestionHash(String questionHash) {
        this.questionHash = questionHash;
    }

    public String getAnswerHash() {
        return answerHash;
    }

    public void setAnswerHash(String answerHash) {
        this.answerHash = answerHash;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

//    public Boolean getFinished() {
//        return finished;
//    }

//    public void setFinished(Boolean finished) {
//        this.finished = finished;
//    }
}
