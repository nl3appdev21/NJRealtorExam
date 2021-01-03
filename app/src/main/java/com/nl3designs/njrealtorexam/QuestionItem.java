package com.nl3designs.njrealtorexam;

//TODO - comment out all buttons in menu screen except flashcards and made new branch tempdev !

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