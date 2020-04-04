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

    private TextView tv_question;
    private TextView tv_details;
    private TextView tv_tryagain;
    private Button btn_answer0, btn_answer1, btn_answer2, btn_answer3;
    private Button btn_next;
    private Button btn_mainmenu;
    private QuestionManager questionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionManager = new QuestionManager(this);
        tv_question = findViewById(R.id.question);
        btn_answer0 = findViewById(R.id.answer_0);
        btn_answer1 = findViewById(R.id.answer_1);
        btn_answer2 = findViewById(R.id.answer_2);
        btn_answer3 = findViewById(R.id.answer_3);
        tv_details = findViewById(R.id.details);
        tv_tryagain = findViewById(R.id.tryagain);
        btn_mainmenu = findViewById(R.id.main_menu);
        btn_next = findViewById(R.id.next);

        final Button btn_next = findViewById(R.id.next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionItem questionItem = questionManager.getNext();
                setQuestionScreen(questionItem);
            }
        });

        //  **

        Button btn0 = findViewById(R.id.answer_0);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionManager.checkAnswer(0)){
                    tv_details.setVisibility(View.VISIBLE);
                    btn_next.setVisibility(View.VISIBLE);
                }else{
                    Log.d("Skip","false");
                }
            }
        });
        btn0 = findViewById(R.id.answer_1);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionManager.checkAnswer(1)){
                    Log.d("Skip","true");
                }else{
                    Log.d("Skip","false");
                }
            }
        });
        btn0 = findViewById(R.id.answer_2);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionManager.checkAnswer(2)){
                    Log.d("Skip","true");
                }else{
                    Log.d("Skip","false");
                }
            }
        });        btn0 = findViewById(R.id.answer_3);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionManager.checkAnswer(3)){
                    Log.d("Skip","true");
                }else{
                    Log.d("Skip","false");
                }
            }
        });

        //  **

        QuestionItem questionItem = questionManager.getNext();
        setQuestionScreen(questionItem);


    }

    private void setQuestionScreen(QuestionItem questionItem) {
        tv_question.setText(questionItem.question);
        btn_answer0.setText(questionItem.answers[0]);
        btn_answer1.setText(questionItem.answers[1]);
        btn_answer2.setText(questionItem.answers[2]);
        btn_answer3.setText(questionItem.answers[3]);
    }

}