package com.example.users.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Question {

        private int id;
        private  String question;
        @JsonProperty("first_option")
        private String firstOption;
        @JsonProperty("second_option")
        private String secondOption;
        @JsonProperty("third_option")
        private String thirdOption;
        @JsonProperty("fourth_option")
        private String fourthOption;

        public Question(String fourthOption, String thirdOption, String secondOption, String firstOption, String question, int id) {
            this.fourthOption = fourthOption;
            this.thirdOption = thirdOption;
            this.secondOption = secondOption;
            this.firstOption = firstOption;
            this.question = question;
            this.id = id;
        }

        public Question() {
        }

        @Override
        public String toString() {
            return "Poll{" +
                    "id=" + id +
                    ", question='" + question + '\'' +
                    ", firstOption='" + firstOption + '\'' +
                    ", secondOption='" + secondOption + '\'' +
                    ", thirdOption='" + thirdOption + '\'' +
                    ", fourthOption='" + fourthOption + '\'' +
                    '}';
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

        public String getFirstOption() {
            return firstOption;
        }

        public void setFirstOption(String firstOption) {
            this.firstOption = firstOption;
        }

        public String getSecondOption() {
            return secondOption;
        }

        public void setSecondOption(String secondOption) {
            this.secondOption = secondOption;
        }

        public String getThirdOption() {
            return thirdOption;
        }

        public void setThirdOption(String thirdOption) {
            this.thirdOption = thirdOption;
        }

        public String getFourthOption() {
            return fourthOption;
        }

        public void setFourthOption(String fourthOption) {
            this.fourthOption = fourthOption;
        }
    }


