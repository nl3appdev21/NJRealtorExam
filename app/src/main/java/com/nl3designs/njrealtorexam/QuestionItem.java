package com.nl3designs.njrealtorexam;

public class QuestionItem {

    int id;
    String category;
    String question;
    String[] answers;
    int correct;
    String details;

    public QuestionItem(){
    }

    public String getQuestion() {
        return question;
    }

}