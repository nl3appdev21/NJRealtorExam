package com.nl3designs.njrealtorexam;

import android.util.Log;

public class TestScoreData {
    int level;  //  ?? silver, gold, diamond  ??
    String name;
    int tries;  //  ??  questions  ??
    int correct;  //  ??  number correct  ??
    int score;  //  ??  score  ??

    public TestScoreData(int level, int tries, int correct, int score){
    //  ??  public TestScoreData(int level,String name, int tries, int correct, int score){

            Log.d("skip", "test score data");

        this.level = level;
        //  ??  this.name = name;
        this.tries = tries;
        this.correct = correct;
        this.score = score;
    }
}