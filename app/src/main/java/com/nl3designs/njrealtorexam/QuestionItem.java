package com.nl3designs.njrealtorexam;

import android.util.Log;

public class QuestionItem {

    int id;
    int correct;
    String details;
    String question;
    String catagory;
    String[] answers;

    public QuestionItem(){}

    public String getQuestion() {
        return question;
    }

}