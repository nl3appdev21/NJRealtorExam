
package com.nl3designs.njrealtorexam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;;

public class MainActivity extends AppCompatActivity {

    private TextView tv_question;
    private TextView tv_details;
    private TextView tv_tryagain;
    private TextView tv_tries;
    private TextView tv_correct;
    private Button btn_answer0, btn_answer1, btn_answer2, btn_answer3;
    private Button btn_menu;
    private Button[] btnArray = new Button[4];
    private ImageView iv_questionimage;
    private QuestionManager questionManager;
    public static final int QUESTION_MODE = 0;
    public static final int PASS_MODE = 1;
    public static final int FAIL_MODE = 2;
    int tries = 0;
    int correct = 0;
    int numQuestions = 0;
    int myQuestionAnswer = -0;
    HashMap<String,Integer> imageMap = new HashMap<>();
    public static String results = "";
    StorageManager storageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storageManager = new StorageManager(this);
        if(results.equals("")){
            results = storageManager.load("results");
        }

        setupImageMap();

        questionManager = new QuestionManager(this);
        tv_question = findViewById(R.id.question);
        btn_answer0 = findViewById(R.id.answer_0);
        btn_answer1 = findViewById(R.id.answer_1);
        btn_answer2 = findViewById(R.id.answer_2);
        btn_answer3 = findViewById(R.id.answer_3);
        btnArray[0] = btn_answer0;  // array to set wrong answer backround to red
        btnArray[1] = btn_answer1;  // array to set wrong answer backround to red
        btnArray[2] = btn_answer2;  // array to set wrong answer backround to red
        btnArray[3] = btn_answer3;  // array to set wrong answer backround to red
        tv_details = findViewById(R.id.details);
        tv_tryagain = findViewById(R.id.tryagain);
        tv_tries = findViewById(R.id.tries);
        tv_correct = findViewById(R.id.correct);
        btn_menu = findViewById(R.id.menu);
        iv_questionimage = findViewById(R.id.questionImage);

        final Button btn_next = findViewById(R.id.next);
        btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // new code new code
                Log.d("skip", " 00 reset onclick ");
                btn_answer0.setClickable(true);
                btn_answer1.setClickable(true);
                btn_answer2.setClickable(true);
                btn_answer3.setClickable(true);

                btn_next.setVisibility(View.INVISIBLE);

                // old code old code
                // disableAnswerBtn(false);

                // new code new code
                /*
                    bwasta.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                        test.setClickable(false);

                        btn_answer0.setClickable(false);
                        btn_answer0.setClickable(true);

                    }
                });
                */

                // use colors from my colors file !!!
                Log.d("skip", " 00 reset button color back to lt-blue ");
                btn_answer0.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.LtBlue_08));
                btn_answer1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.LtBlue_08));
                btn_answer2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.LtBlue_08));
                btn_answer3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.LtBlue_08));
                btn_answer0.setTextColor(Color.WHITE);
                btn_answer1.setTextColor(Color.WHITE);
                btn_answer2.setTextColor(Color.WHITE);
                btn_answer3.setTextColor(Color.WHITE);
                myQuestionAnswer = -0;
                changePictureMode(QUESTION_MODE);
                QuestionItem questionItem = questionManager.getNext();
                if (questionItem != null) {  // if not null
                    setQuestionScreen(questionItem);
                } else {
                    questionManager.currentIndex = 0;
                    testComplete();  // new code runs end-of-test
                }
            }
        });

        btn_answer0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // new code new code
                // btn_answer0.setClickable(false);
                Log.d("skip", " ans0 turn onclick off ");
                // disableAnswerBtn(true);

                btn_next.setVisibility(View.VISIBLE);
                if(questionManager.checkAnswer(0)){
                    myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    btn_answer0.setBackgroundColor(Color.GREEN);
                    btn_answer0.setTextColor(Color.BLACK);
                    tv_details.setText(questionManager.getCurrentQuestion().details);
                    changePictureMode(PASS_MODE);
                    correct += 1;
                }else{
                    myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    btn_answer0.setBackgroundColor(Color.RED);
                    btnArray[myQuestionAnswer].setBackgroundColor(Color.GREEN);
                    btn_answer0.setTextColor(Color.BLACK);
                    tv_details.setText(questionManager.getCurrentQuestion().details);
                    changePictureMode(FAIL_MODE);
                }
                tries += 1;
                scoreCount();
            }
        });
        btn_answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // new code new code
                // btn_answer1.setClickable(false);
                Log.d("skip", " ans1 turn onclick off ");
                // disableAnswerBtn(true);

                btn_next.setVisibility(View.VISIBLE);
                if(questionManager.checkAnswer(1)){
                    myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    btn_answer1.setBackgroundColor(Color.GREEN);
                    btn_answer1.setTextColor(Color.BLACK);
                    changePictureMode(PASS_MODE);
                    correct += 1;
                }else{
                    myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    btn_answer1.setBackgroundColor(Color.RED);
                    btnArray[myQuestionAnswer].setBackgroundColor(Color.GREEN);
                    btn_answer1.setTextColor(Color.BLACK);
                    changePictureMode(FAIL_MODE);
                }
                tries += 1;
                scoreCount();
            }
        });
        btn_answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // new code new code
                // btn_answer2.setClickable(false);
                Log.d("skip", " ans2 turn onclick off ");
                // disableAnswerBtn(true);

                btn_next.setVisibility(View.VISIBLE);
                if(questionManager.checkAnswer(2)){
                    myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    btn_answer2.setBackgroundColor(Color.GREEN);
                    btn_answer2.setTextColor(Color.BLACK);
                    changePictureMode(PASS_MODE);
                    correct += 1;
                }else{
                    myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    btn_answer2.setBackgroundColor(Color.RED);
                    btnArray[myQuestionAnswer].setBackgroundColor(Color.GREEN);
                    btn_answer2.setTextColor(Color.BLACK);
                    changePictureMode(FAIL_MODE);
                }
                tries += 1;
                scoreCount();
            }
        });
        btn_answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // new code new code
                // btn_answer3.setClickable(false);
                Log.d("skip", " ans3 turn onclick off ");
                // disableAnswerBtn(true);

                btn_next.setVisibility(View.VISIBLE);
                if(questionManager.checkAnswer(3)){
                    myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    btn_answer3.setBackgroundColor(Color.GREEN);
                    btn_answer3.setTextColor(Color.BLACK);
                    changePictureMode(PASS_MODE);
                    correct += 1;
                }else{
                    myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    btn_answer3.setBackgroundColor(Color.RED);
                    btnArray[myQuestionAnswer].setBackgroundColor(Color.GREEN);
                    btn_answer3.setTextColor(Color.BLACK);
                    changePictureMode(FAIL_MODE);
                }
                tries += 1;
                scoreCount();
            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMenu();
            }
        });
        QuestionItem questionItem = questionManager.getNext();
        setQuestionScreen(questionItem);
    }

    private void setQuestionScreen(QuestionItem questionItem) {

        iv_questionimage.setImageResource(imageMap.get(questionItem.catagory));
        tv_question.setText(questionItem.question);
        btn_answer0.setText(questionItem.answers[0]);
        btn_answer1.setText(questionItem.answers[1]);
        btn_answer2.setText(questionItem.answers[2]);
        btn_answer3.setText(questionItem.answers[3]);

        numQuestions = questionManager.questionitems.size(); // new code to gets num of questions
    }

    private void changePictureMode(int mode){

        switch (mode) {
            case QUESTION_MODE:
                iv_questionimage.setVisibility(View.VISIBLE);
                tv_tryagain.setVisibility(View.INVISIBLE);
                tv_details.setVisibility(View.INVISIBLE);
                break;

            case PASS_MODE:
                iv_questionimage.setImageResource(R.mipmap.happyface); // new code happyface
                iv_questionimage.setVisibility(View.VISIBLE);
                tv_tryagain.setVisibility(View.INVISIBLE);
                tv_details.setVisibility(View.INVISIBLE);
                break;

            case FAIL_MODE:
                iv_questionimage.setImageResource(R.mipmap.sadface); // new code sadface
                iv_questionimage.setVisibility(View.VISIBLE);
                tv_tryagain.setVisibility(View.INVISIBLE);
                tv_details.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void scoreCount() {

        tv_tries.setText(" # of tries = " + String.valueOf(tries));
        tv_correct.setText(" # correct = " + String.valueOf(correct));

        // new code new code
        Log.d("skip", " 11 do score count and disable onclick ");
        btn_answer0.setClickable(false);
        btn_answer1.setClickable(false);
        btn_answer2.setClickable(false);
        btn_answer3.setClickable(false);
    }

    /*

    //  old code old code
    private void disableAnswerBtn(boolean disable){
        btn_answer0.setEnabled(!disable);
        btn_answer1.setEnabled(!disable);
        btn_answer2.setEnabled(!disable);
        btn_answer3.setEnabled(!disable);
    }

    */

    private void testComplete() {  // new code for end-of-test

        Intent intent = new Intent(MainActivity.this, TestComplete.class);

        intent.putExtra("tries", tries);
        intent.putExtra("correct", correct);
        intent.putExtra("numQuestions", numQuestions);

        startActivity(intent);
    }

    private void gotoMenu() {
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    private void setupImageMap(){
        imageMap.put("oldtype",R.mipmap.njreal01);  // sets all tyes in both old and new file
        imageMap.put("mortgage",R.mipmap.mortgage);
        imageMap.put("law",R.mipmap.law);
        imageMap.put("commission",R.mipmap.math);
        imageMap.put("advirtising",R.mipmap.advirtising);
        imageMap.put("ownership",R.mipmap.ownership2);
        imageMap.put("sad",R.mipmap.sadface);
        imageMap.put("happy",R.mipmap.happyface);

    }
}





/*
package com.nl3designs.njrealtorexam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;;

public class MainActivity extends AppCompatActivity {

    boolean aic = false;
    private TextView tv_question;
    private TextView tv_details;
    private TextView tv_tryagain;
    private TextView tv_tries;
    private TextView tv_correct;
    private Button btn_answer0, btn_answer1, btn_answer2, btn_answer3;
    private Button btn_menu;
    private Button[] btnArray = new Button[4];
    private ImageView iv_questionimage;
    private QuestionManager questionManager;
    public static final int QUESTION_MODE = 0;
    public static final int PASS_MODE = 1;
    public static final int FAIL_MODE = 2;
    int tries = 0;
    int correct = 0;
    int numQuestions = 0;
    int myQuestionAnswer = -0;
    HashMap<String,Integer> imageMap = new HashMap<>();
    public static String results = "";
    StorageManager storageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storageManager = new StorageManager(this);
        if(results.equals("")){
            results = storageManager.load("results");
        }

        setupImageMap();

        questionManager = new QuestionManager(this);
        tv_question = findViewById(R.id.question);
        btn_answer0 = findViewById(R.id.answer_0);
        btn_answer1 = findViewById(R.id.answer_1);
        btn_answer2 = findViewById(R.id.answer_2);
        btn_answer3 = findViewById(R.id.answer_3);
        btnArray[0] = btn_answer0;  // array to set wrong answer backround to red
        btnArray[1] = btn_answer1;  // array to set wrong answer backround to red
        btnArray[2] = btn_answer2;  // array to set wrong answer backround to red
        btnArray[3] = btn_answer3;  // array to set wrong answer backround to red
        tv_details = findViewById(R.id.details);
        tv_tryagain = findViewById(R.id.tryagain);
        tv_tries = findViewById(R.id.tries);
        tv_correct = findViewById(R.id.correct);
        btn_menu = findViewById(R.id.menu);
        iv_questionimage = findViewById(R.id.questionImage);

        final Button btn_next = findViewById(R.id.next);
        btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("skip", " a0 aic = " + aic);
                btn_next.setVisibility(View.INVISIBLE);
                //  aic = true;  //  ?????????????????????????????????????????????????????????????????????
                // disableAnswerBtn(false);
                Log.d("skip", " a00 aic = " + aic);
                // use colors from my colors file !!!
                btn_answer0.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.LtBlue_08));
                btn_answer1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.LtBlue_08));
                btn_answer2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.LtBlue_08));
                btn_answer3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.LtBlue_08));
                myQuestionAnswer = -0;
                changePictureMode(QUESTION_MODE);
                QuestionItem questionItem = questionManager.getNext();
                if (questionItem != null) {  // if not null
                    setQuestionScreen(questionItem);
                } else {
                    questionManager.currentIndex = 0;
                    testComplete();  // new code runs end-of-test
                }
            }
        });




        if (!aic) {  //  ???????????????????????????????????


            btn_answer0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("skip", " a1 aic = " + aic);
                    btn_next.setVisibility(View.VISIBLE);
                    aic = true;
                    // disableAnswerBtn(true);
                    Log.d("skip", " a2 aic = " + aic);
                    if (questionManager.checkAnswer(0)) {
                        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                        btn_answer0.setBackgroundColor(Color.GREEN);
                        tv_details.setText(questionManager.getCurrentQuestion().details);
                        changePictureMode(PASS_MODE);
                        correct += 1;
                    } else {
                        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                        btn_answer0.setBackgroundColor(Color.RED);
                        btnArray[myQuestionAnswer].setBackgroundColor(Color.GREEN);
                        tv_details.setText(questionManager.getCurrentQuestion().details);
                        changePictureMode(FAIL_MODE);
                    }
                    tries += 1;
                    aic = true;
                    scoreCount();
                }
            });


        }  // ??????????????????????????????????????????????




        if (!aic) {  //  ???????????????????????????????????


            btn_answer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("skip", " a3 aic = " + aic);
                    btn_next.setVisibility(View.VISIBLE);
                    aic = true;
                    // disableAnswerBtn(true);
                    Log.d("skip", " a4 aic = " + aic);
                    if (questionManager.checkAnswer(1)) {
                        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                        btn_answer1.setBackgroundColor(Color.GREEN);
                        changePictureMode(PASS_MODE);
                        correct += 1;
                    } else {
                        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                        btn_answer1.setBackgroundColor(Color.RED);
                        btnArray[myQuestionAnswer].setBackgroundColor(Color.GREEN);
                        changePictureMode(FAIL_MODE);
                    }
                    tries += 1;
                    scoreCount();
                }
            });


        }



        if (!aic) {  //  ???????????????????????????????????


            btn_answer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("skip", " a5 aic = " + aic);
                    btn_next.setVisibility(View.VISIBLE);
                    aic = true;
                    // disableAnswerBtn(true);
                    Log.d("skip", " a6 aic = " + aic);
                    if (questionManager.checkAnswer(2)) {
                        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                        btn_answer2.setBackgroundColor(Color.GREEN);
                        changePictureMode(PASS_MODE);
                        correct += 1;
                    } else {
                        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                        btn_answer2.setBackgroundColor(Color.RED);
                        btnArray[myQuestionAnswer].setBackgroundColor(Color.GREEN);
                        changePictureMode(FAIL_MODE);
                    }
                    tries += 1;
                    scoreCount();
                }
            });


        }



        if (!aic) {  //  ???????????????????????????????????


            btn_answer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("skip", " a7 aic = " + aic);
                    btn_next.setVisibility(View.VISIBLE);
                    aic = true;
                    // disableAnswerBtn(true);
                    Log.d("skip", " a8 aic = " + aic);
                    if (questionManager.checkAnswer(3)) {
                        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                        btn_answer3.setBackgroundColor(Color.GREEN);
                        changePictureMode(PASS_MODE);
                        correct += 1;
                    } else {
                        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                        btn_answer3.setBackgroundColor(Color.RED);
                        btnArray[myQuestionAnswer].setBackgroundColor(Color.GREEN);
                        changePictureMode(FAIL_MODE);
                    }
                    tries += 1;
                    scoreCount();
                }
            });


        }





        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMenu();
            }
        });
        QuestionItem questionItem = questionManager.getNext();
        setQuestionScreen(questionItem);
    }

    private void setQuestionScreen(QuestionItem questionItem) {

        iv_questionimage.setImageResource(imageMap.get(questionItem.catagory));
        tv_question.setText(questionItem.question);
        btn_answer0.setText(questionItem.answers[0]);
        btn_answer1.setText(questionItem.answers[1]);
        btn_answer2.setText(questionItem.answers[2]);
        btn_answer3.setText(questionItem.answers[3]);

        numQuestions = questionManager.questionitems.size(); // new code to gets num of questions
    }

    private void changePictureMode(int mode){

        switch (mode) {
            case QUESTION_MODE:
                iv_questionimage.setVisibility(View.VISIBLE);
                tv_tryagain.setVisibility(View.INVISIBLE);
                tv_details.setVisibility(View.INVISIBLE);

                aic = false;

                break;

            case PASS_MODE:
                iv_questionimage.setImageResource(R.mipmap.happyface); // new code happyface
                iv_questionimage.setVisibility(View.VISIBLE);
                tv_tryagain.setVisibility(View.INVISIBLE);
                tv_details.setVisibility(View.INVISIBLE);

                aic = true;

                break;

            case FAIL_MODE:
                iv_questionimage.setImageResource(R.mipmap.sadface); // new code sadface
                iv_questionimage.setVisibility(View.VISIBLE);
                tv_tryagain.setVisibility(View.INVISIBLE);
                tv_details.setVisibility(View.INVISIBLE);

                aic = true;

                break;
        }
    }

    private void scoreCount() {
        tv_tries.setText(" # of tries = " + String.valueOf(tries));
        tv_correct.setText(" # correct = " + String.valueOf(correct));
    }

    private void disableAnswerBtn(boolean disable){

        Log.d("skip", " 00 aic = " + aic);
        // btn_answer0.setEnabled(!disable);
        // btn_answer1.setEnabled(!disable);
        // btn_answer2.setEnabled(!disable);
        // btn_answer3.setEnabled(!disable);

    }

    private void testComplete() {  // new code for end-of-test

        Intent intent = new Intent(MainActivity.this, TestComplete.class);

        intent.putExtra("tries", tries);
        intent.putExtra("correct", correct);
        intent.putExtra("numQuestions", numQuestions);

        startActivity(intent);
    }

    private void gotoMenu() {
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    private void setupImageMap(){
        imageMap.put("oldtype",R.mipmap.njreal01);  // sets all tyes in both old and new file
        imageMap.put("mortgage",R.mipmap.mortgage);
        imageMap.put("law",R.mipmap.law);
        imageMap.put("commission",R.mipmap.math);
        imageMap.put("advirtising",R.mipmap.advirtising);
        imageMap.put("ownership",R.mipmap.ownership2);
        imageMap.put("sad",R.mipmap.sadface);
        imageMap.put("happy",R.mipmap.happyface);

    }
}

*/