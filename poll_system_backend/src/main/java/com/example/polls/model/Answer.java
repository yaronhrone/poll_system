package com.example.polls.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Answer {
    private int id;
    @JsonProperty("question_id")
    private int idQuestion;
    @JsonProperty("answer_number")
   private int answerNumber;
    @JsonProperty("user_id")
   private int userId;


    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", idQuestion=" + idQuestion +
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

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
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

    public Answer(int id, int idQuestion, int answerNumber, int userId) {
        this.id = id;
        this.idQuestion = idQuestion;
        this.answerNumber = answerNumber;
        this.userId = userId;
    }
}