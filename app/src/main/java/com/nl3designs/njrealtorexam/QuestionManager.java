package com.nl3designs.njrealtorexam;

import android.content.Context;
import android.util.Log;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionManager {

    List<QuestionItem> questionitems;
    List<QuestionItem> defaultQuestionitems;
    int currentIndex = -1;
    Map<String,Integer> categoryMap = new HashMap<>();
    boolean isRandom;

    public QuestionManager(Context context){
        loadAllQuestion(context);
    }

    private void loadAllQuestion(Context context) {
        String jsonStr = loadJSONFromNjexams("njrealtorexam.json",context);
        Gson gson = new Gson();
        Type type = new TypeToken<List<QuestionItem>>() {}.getType();
        questionitems = gson.fromJson(jsonStr, type);
        setUpCategories();
        verifyCategories();
        StorageManager store = new StorageManager(context);
        String s = store.load("flashRand");
        if(s.equals("true")){
            isRandom = true;
        }else{
            isRandom = false;
        }
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public QuestionItem getPrev(){
        if(!isRandom) {
            if(currentIndex > 0) {
                currentIndex--;
                // Log.d("skip", "prev currentIndex = " + currentIndex);

                if (currentIndex < questionitems.size()) {
                    QuestionItem q = questionitems.get(currentIndex);
                    return q;
                } else {
                    return null;
                }
            }
            return  null;
        }else{
            currentIndex = (int)(Math.random()*questionitems.size());
            QuestionItem q = questionitems.get(currentIndex);
            return q;
        }
    }

    public QuestionItem getNext(){

        for(QuestionItem item : questionitems){
            Log.d("skip", "item id = " + item.id);
        }

        Log.d("skip", " ************************************* ");

        currentIndex++;
        // Log.d("skip", "next currentIndex = " + currentIndex);
        if (currentIndex < questionitems.size()) {
            QuestionItem q = questionitems.get(currentIndex);
            return q;
        } else {
            return null;
        }

    }

    public QuestionItem getCurrentQuestion(){

        if(currentIndex >= 0 && (currentIndex < questionitems.size())){
            return questionitems.get(currentIndex);
        }else{
            return null;
        }
    }

    public void randomize () {
        Collections.shuffle(questionitems);
        int x = 5;
        Log.d("skip", "call rand");

    }

    public String getCurrentAnswer(){
        if(currentIndex >= 0 && (currentIndex < questionitems.size())){
            QuestionItem item = questionitems.get(currentIndex);
            return item.answers[item.correct];
        }else{
            return null;
        }
    }

    private String loadJSONFromNjexams(String file,Context context) {
        String json = "";
        try {
            InputStream is = context.getAssets().open(file);
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

    public void verifyCategories(){

        for(QuestionItem q : questionitems) {
            boolean valid = categoryMap.containsKey(q.catagory);
            if(!valid) {
                throw new IllegalArgumentException("Error Invalid Category = " + q.catagory);
            }
        }
    }

    private void setUpCategories(){

        categoryMap.put("newtype",R.mipmap.njreal01);  // use for new category if not sure ???
        categoryMap.put("mortgage",R.mipmap.mortgage);
        categoryMap.put("law",R.mipmap.law);
        categoryMap.put("commission",R.mipmap.math);
        categoryMap.put("advirtising",R.mipmap.advirtising);
        categoryMap.put("ownership",R.mipmap.ownership2);

    }

    public boolean checkAnswer (int answerIndex){
        QuestionItem qi = questionitems.get(currentIndex);
        boolean result = qi.correct == answerIndex;
        return result;
    }
}