package com.example.polls.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionAndNumberUser {
   private String question;
   @JsonProperty("number_users_answers")
   private int numUsersAnswers;

    public QuestionAndNumberUser(String question, int numUsersAnswers) {
        this.question = question;
        this.numUsersAnswers = numUsersAnswers;
    }

    public QuestionAndNumberUser() {
    }

    @Override
    public String toString() {
        return "QuestionAndNumberUser{" +
                "question='" + question + '\'' +
                ", numUsersAnswers=" + numUsersAnswers +
                '}';
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getNumUsersAnswers() {
        return numUsersAnswers;
    }

    public void setNumUsersAnswers(int numUsersAnswers) {
        this.numUsersAnswers = numUsersAnswers;
    }
}
