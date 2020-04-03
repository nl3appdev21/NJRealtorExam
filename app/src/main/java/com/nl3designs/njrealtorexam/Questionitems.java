package com.nl3designs.njrealtorexam;

public class Questionitems {

    private String question, answers0, answers1, answers2, answers3, correct;

    public Questionitems(String question, String answers0, String answers1, String answerss2, String answers3, String correct) {
        this.question = question;
        this.answers0 = answers0;
        this.answers1 = answers1;
        this.answers2 = answers2;
        this.answers3 = answers3;
        this.correct = correct;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswers0() {
        return answers0;
    }

    public String getAnswers1() {
        return answers1;
    }

    public String getAnswers2() {
        return answers2;
    }

    public String getAnswers3() {
        return answers3;
    }

    public String getCorrect() {
        return correct;
    }
}