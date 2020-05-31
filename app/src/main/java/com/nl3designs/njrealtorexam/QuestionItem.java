package com.nl3designs.njrealtorexam;

import android.util.Log;
//  clean up code

public class QuestionItem {

    int id;
    String catagory;
    String question;
    String[] answers;
    int correct;
    String details;

    public QuestionItem(){}

    public String getQuestion() {
        return question;
    }

}