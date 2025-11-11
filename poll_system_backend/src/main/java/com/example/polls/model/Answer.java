package com.example.polls.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Answer {
    private int id;
    @JsonProperty("question_id")
    private int questionId;
    @JsonProperty("answer_number")
   private int answerNumber;
    @JsonProperty("user_id")
   private int userId;


    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", idQuestion=" + questionId +
                ", answerNumber=" + answerNumber +
                ", userId=" + userId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int idQuestion) {
        this.questionId = idQuestion;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Answer() {
    }

    public Answer(int id, int questionId, int answerNumber, int userId) {
        this.id = id;
        this.questionId = questionId;
        this.answerNumber = answerNumber;
        this.userId = userId;
    }
}