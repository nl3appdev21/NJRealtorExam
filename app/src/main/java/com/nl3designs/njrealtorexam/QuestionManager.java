package com.nl3designs.njrealtorexam;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class QuestionManager {

    List<QuestionItem> questionitems;
    int currentQuestion = 0;
    int correct = 0, wrong = 0;
    int currentIndex = -1;

    public QuestionManager(Context context){
        loadAllQuestion(context);
    }

    private void loadAllQuestion(Context context) {  //  question or questions
        String jsonStr = loadJSONFromNjexams("njrealtorexam.json",context);
        Gson gson = new Gson();
        Type type = new TypeToken<List<QuestionItem>>() {}.getType();
        questionitems = gson.fromJson(jsonStr, type);

    }

    public QuestionItem getNext(){
        currentIndex++;
        QuestionItem q = questionitems.get(currentIndex);
        return q;
    }

    private String loadJSONFromNjexams(String file,Context context) {     //  ?????
        String json = "";
        try {
            InputStream is = context.getAssets().open(file);     //  ??????
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public boolean checkAnswer (int answerIndex){
        QuestionItem qi = questionitems.get(currentIndex);
        boolean result = qi.correct == answerIndex;
        return result;
    }

}