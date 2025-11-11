package com.example.polls.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultPoll {
    private String question;
    @JsonProperty("first_option")
    private String firstOption;
    @JsonProperty("second_option")
    private String secondOption;
    @JsonProperty("third_option")
    private String thirdOption;
    @JsonProperty("fourth_option")
    private String fourthOption;
    @JsonProperty("first_option_count")
    private int countFirstOption;
    @JsonProperty("second_option_count")
    private int countSecondOption;
    @JsonProperty("third_option_count")
    private int countThirdOption;
    @JsonProperty("fourth_option_count")
    private int countFourthOption;

    public ResultPoll(String question, String firstOption, String secondOption, String thirdOption, String fourthOption, int countFirstOption, int countSecondOption, int countThirdOption, int countFourthOption) {
        this.question = question;
        this.firstOption = firstOption;
        this.secondOption = secondOption;
        this.thirdOption = thirdOption;
        this.fourthOption = fourthOption;
        this.countFirstOption = countFirstOption;
        this.countSecondOption = countSecondOption;
        this.countThirdOption = countThirdOption;
        this.countFourthOption = countFourthOption;
    }

    @Override
    public String toString() {
        return "ResultPoll{" +
                "question='" + question + '\'' +
                ", firstOption='" + firstOption + '\'' +
                ", secondOption='" + secondOption + '\'' +
                ", thirdOption='" + thirdOption + '\'' +
                ", fourthOption='" + fourthOption + '\'' +
                ", countFirstOption=" + countFirstOption +
                ", countSecondOption=" + countSecondOption +
                ", countThirdOption=" + countThirdOption +
                ", countFourthOption=" + countFourthOption +
                '}';
    }

    public ResultPoll() {
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

    public int getCountFirstOption() {
        return countFirstOption;
    }

    public void setCountFirstOption(int countFirstOption) {
        this.countFirstOption = countFirstOption;
    }

    public int getCountSecondOption() {
        return countSecondOption;
    }

    public void setCountSecondOption(int countSecondOption) {
        this.countSecondOption = countSecondOption;
    }

    public int getCountThirdOption() {
        return countThirdOption;
    }

    public void setCountThirdOption(int countThirdOption) {
        this.countThirdOption = countThirdOption;
    }

    public int getCountFourthOption() {
        return countFourthOption;
    }

    public void setCountFourthOption(int countFourthOption) {
        this.countFourthOption = countFourthOption;
    }
}
