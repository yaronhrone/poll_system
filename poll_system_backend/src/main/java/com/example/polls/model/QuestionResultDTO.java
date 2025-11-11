package com.example.polls.model;

import java.util.List;

public class QuestionResultDTO {
    private int id;
    private String question;
    private List<OptionResult> option;

    public QuestionResultDTO(int id, String question, List<OptionResult> option) {
        this.id = id;
        this.question = question;
        this.option = option;
    }

    public List<OptionResult> getOption() {
        return option;
    }

    public void setOption(List<OptionResult> option) {
        this.option = option;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public static class OptionResult {
        private String text;
        private int votes;

        public OptionResult(String text, int votes) {
            this.text = text;
            this.votes = votes;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getVotes() {
            return votes;
        }

        public void setVotes(int votes) {
            this.votes = votes;
        }

        @Override
        public String toString() {
            return "OptionResult{" +
                    "text='" + text + '\'' +
                    ", votes=" + votes +
                    '}';
        }
    }
}
