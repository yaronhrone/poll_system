package com.example.users.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Answer {
    private int id;
    @JsonProperty("answer_number")
    private int answerNumber;
    @JsonProperty("question_id")
    private int questionId;
    @JsonProperty("user_id")
    private int userId;

    public Answer() {
    }

    public Answer(int id, int answerNumber, int questionId, int userId) {
        this.id = id;
        this.answerNumber = answerNumber;
        this.questionId = questionId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answerNumber=" + answerNumber +
                ", questionId=" + questionId +
                ", userId=" + userId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}