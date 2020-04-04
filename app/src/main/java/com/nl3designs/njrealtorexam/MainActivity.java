package com.nl3designs.njrealtorexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tv_question;
    Button btn_answer0, btn_answer1, btn_answer2, btn_answer3;

    List<QuestionItem> questionitems;
    int currentQuestion = 0;

    int correct = 0, wrong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_question = findViewById(R.id.question);
        btn_answer0 = findViewById(R.id.answer_0);
        btn_answer1 = findViewById(R.id.answer_1);
        btn_answer2 = findViewById(R.id.answer_2);
        btn_answer3 = findViewById(R.id.answer_3);

        loadAllQuestion();
        setQuestionScreen(2);

    }

    private void setQuestionScreen(int number) {
        tv_question.setText(questionitems.get(number).question);
        btn_answer0.setText(questionitems.get(number).answers[0]);
        btn_answer1.setText(questionitems.get(number).answers[1]);
        btn_answer2.setText(questionitems.get(number).answers[2]);
        btn_answer3.setText(questionitems.get(number).answers[3]);

    }


    private void loadAllQuestion() {  //  question or questions
        String jsonStr = loadJSONFromNjexams("njrealtorexam.json");
        Gson gson = new Gson();
        Type type = new TypeToken<List<QuestionItem>>() {}.getType();
        questionitems = gson.fromJson(jsonStr, type);
        Log.d("Skip",questionitems.get(2).question);
        Log.d("Skip",questionitems.get(2).answers[1]);

    }

    private String loadJSONFromNjexams(String file) {     //  ?????
        String json = "";
        try {
            InputStream is = getAssets().open(file);     //  ??????
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
}