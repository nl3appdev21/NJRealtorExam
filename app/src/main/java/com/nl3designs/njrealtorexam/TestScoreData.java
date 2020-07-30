package com.nl3designs.njrealtorexam;

import android.util.Log;

public class TestScoreData {

    String name;
    int tries;  //  ??  questions  ??
    int correct;  //  ??  number correct  ??
    int score;  //  ??  score  ??

    public TestScoreData(String name, int tries, int correct, int score){
    //  ??  public TestScoreData(int level,String name, int tries, int correct, int score){

        this.name = name;
        this.tries = tries;
        this.correct = correct;
        this.score = score;
    }
}