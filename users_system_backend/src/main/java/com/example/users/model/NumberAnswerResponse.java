package com.example.users.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NumberAnswerResponse {
    @JsonProperty("answer_number")
    private int answerNumber;
    @JsonProperty("num_users")
    private int numUsers;
    private String question;

    public NumberAnswerResponse() {
    }

    @Override
    public String toString() {
        return "NumberAnswerResponse{" +
                "answerNumber=" + answerNumber +
                ", numUsers=" + numUsers +
                ", question='" + question + '\'' +
                '}';
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }

    public int getNumUsers() {
        return numUsers;
    }

    public void setNumUsers(int numUsers) {
        this.numUsers = numUsers;
    }

    public NumberAnswerResponse(int answerNumber, int numUsers, String question) {
        this.answerNumber = answerNumber;
        this.numUsers = numUsers;
        this.question = question;
    }
}


