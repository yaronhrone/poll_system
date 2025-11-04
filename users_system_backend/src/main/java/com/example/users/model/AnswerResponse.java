package com.example.users.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnswerResponse {

    @JsonProperty("question")
    private String Question;
    @JsonProperty("answer_number")
    private int answerNumber;

    public AnswerResponse() {
    }

    public AnswerResponse(int answerNumber, String question) {
        this.answerNumber = answerNumber;
        Question = question;
    }

    @Override
    public String toString() {
        return "AnswerResponse{" +
                "Question='" + Question + '\'' +
                ", answerNumber=" + answerNumber +
                '}';
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }
}
