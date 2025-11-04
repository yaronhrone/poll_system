package com.example.polls.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnswerResponse {
        private String question;
        @JsonProperty("answer_number")
        private int answerNumber;
        private String answer;


        public AnswerResponse() {
        }

    public AnswerResponse(String question, int answerNumber, String answer) {
        this.question = question;
        this.answerNumber = answerNumber;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "AnswerResponse{" +
                "question='" + question + '\'' +
                ", answerNumber=" + answerNumber +
                ", answer='" + answer + '\'' +
                '}';
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getAnswerNumber() {
            return answerNumber;
        }

        public void setAnswerNumber(int answerNumber) {
            this.answerNumber = answerNumber;
        }
    }


