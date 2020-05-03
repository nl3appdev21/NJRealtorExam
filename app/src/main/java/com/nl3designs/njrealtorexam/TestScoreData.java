package com.nl3designs.njrealtorexam;

import android.util.Log;

public class TestScoreData {
    String name;
    int tries;
    int correct;
    //  String passTest;  //  ??
    int passTest;

    public TestScoreData(String name, int tries, int correct, int passTest){
                //  ??  (String name, int tries, int correct, String passTest){

        this.name = name;
        this.tries = tries;
        this.correct = correct;
        this.passTest = passTest;

    }
}