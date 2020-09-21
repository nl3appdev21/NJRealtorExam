package com.nl3designs.njrealtorexam;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QuestionManager{
    private static final QuestionManager instance = new QuestionManager();
    List<QuestionItem> questionitems;
    int currentIndex = -1;
    Map<String,Integer> categoryMap = new HashMap<>();
    boolean isRandom;

    public static QuestionManager getInstance(){
        return instance;
    }

    public void loadQuestions(Context context,String category) {
        Set<String> singleCat = new HashSet<>();
        singleCat.add(category);
        if(category.equals("")){
            loadAllQuestion(context,null);
        }else {
            loadAllQuestion(context, singleCat);
        }
    }

    // TODO : note changed last question from law to mortgage for test, replace with real mortgage

    public void loadAllQuestion(Context context, Set<String> categories) {
        String jsonStr = loadJSONFromNjexams("njrealtorexam.json",context);
        Gson gson = new Gson();
        Type type = new TypeToken<List<QuestionItem>>() {}.getType();
        questionitems = gson.fromJson(jsonStr, type);
        if(categories!=null){
            questionitems = getQuestionsForCategories(categories);
        }
        setUpCategories();
        verifyCategories();
        StorageManager store = new StorageManager(context);
        String s = store.load("flashRand");
        if(s.equals("true")){
            isRandom = true;
        }else{
            isRandom = false;
        }
        currentIndex = -1;
    }

    public void loadFromStoredCat(Context context){   // method is not used or called !!
        //get categories from StorageManager

        StorageManager store = new StorageManager(context);
        String[] selected = store.load("customCards").split(",");
        Set<String> categories = null;

        // convert to Set
        // add new code hereqwq
        // load new split array into a set / list

        loadAllQuestion(context, categories);
    }

    public boolean isEmpty(){
        return questionitems == null || questionitems.size() == 0;
    }


    public void reset(){
        currentIndex = -1;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public QuestionItem getPrev(){
        if(!isRandom) {
            if(currentIndex > 0) {
                currentIndex--;

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

        currentIndex++;
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
        int x = questionitems.size();
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

    public List<QuestionItem> getQuestionsForCategory(String category){
        Set<String> singleCat = new HashSet<>();
        singleCat.add(category);
        return getQuestionsForCategories(singleCat);
    }

    public List<QuestionItem> getQuestionsForCategories(Set<String> categories){
        List<QuestionItem> catQuestions = new ArrayList<>();
        for(QuestionItem q : questionitems) {
            if(categories.contains(q.catagory)){
                catQuestions.add(q);
            }
        }
        return catQuestions;
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