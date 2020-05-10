package com.nl3designs.njrealtorexam;

import android.util.Log;

public class TestScoreData {
    String name;
    int tries;
    int correct;
    //  String passTest;  //  ??  int or srting output  **
    int passTest;  //  ??  int or srting output  **

    public TestScoreData(String name, int tries, int correct, int passTest){  //  ??  int or srting output  **
                //  ??  (String name, int tries, int correct, String passTest){  //  ??  int or srting output  **

        this.name = name;
        this.tries = tries;
        this.correct = correct;
        this.passTest = passTest;

    }
}