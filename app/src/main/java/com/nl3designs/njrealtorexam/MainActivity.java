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

import java.util.HashMap;

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
    //  new code
    //  public static final int xxtries = 0;  //  new code
    //  public static final int xxcorrect = 0;  //  new code
    //  public static final int xxnumQuestions = 0;  //  new code

    int tries = 0;  //  new code
    int correct = 0;  //  new code
    int numQuestions = 0;  //  new code

    //  old code del 4 lines
    //  int tries = 0;
    //  int correct = 0;
    //  int testScore = 0;
    //  int numQuestions = 0; //  new new

    int numCorrect = 0;  //  new new
    int myQuestionAnswer = -0;  //  new new
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
        btnArray[0] = btn_answer0;  //  ??  use array to set wrong answer backround to red  ??
        btnArray[1] = btn_answer1;  //  ??  use array to set wrong answer backround to red  ??
        btnArray[2] = btn_answer2;  //  ??  use array to set wrong answer backround to red  ??
        btnArray[3] = btn_answer3;  //  ??  use array to set wrong answer backround to red  ??
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
                btn_next.setVisibility(View.INVISIBLE);
                disableAnswerBtn(false);
                btn_answer0.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.LtBlue_08));
                btn_answer1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.LtBlue_08));
                btn_answer2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.LtBlue_08));
                btn_answer3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.LtBlue_08));
                myQuestionAnswer = -0;
                changePictureMode(QUESTION_MODE);
                QuestionItem questionItem = questionManager.getNext();
                if (questionItem != null) {  //  ??  not null  ??
                    setQuestionScreen(questionItem);
                } else {
                    //  old code del  **  showEndScreen();
                    questionManager.currentIndex = 0;
                    //  old code del  **  showDialog();  //  ?? beta call dialog box
                    testComplete();  //  new code will be end of test
                }
            }
        });

        btn_answer0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next.setVisibility(View.VISIBLE);
                disableAnswerBtn(true);
                if(questionManager.checkAnswer(0)){
                    //  ??  ??
                    myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    Log.d("skip"," pp ans-0 correct answer number is: " + myQuestionAnswer);
                    btn_answer0.setBackgroundColor(Color.GREEN);
                    //  ??  ??
                    tv_details.setText(questionManager.getCurrentQuestion().details);
                    changePictureMode(PASS_MODE);
                    correct += 1;
                }else{
                    //  ??  ??
                    myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    Log.d("skip"," ff ans-0 correct answer number is: " + myQuestionAnswer);
                    btn_answer0.setBackgroundColor(Color.RED);
                    btnArray[myQuestionAnswer].setBackgroundColor(Color.GREEN);
                    tv_details.setText(questionManager.getCurrentQuestion().details);  //  new line of code
                    //  ??  ??
                    changePictureMode(FAIL_MODE);
                }
                tries += 1;
                scoreCount();
            }
        });
        btn_answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next.setVisibility(View.VISIBLE);
                disableAnswerBtn(true);
                if(questionManager.checkAnswer(1)){
                    //  ??  ??
                    myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    Log.d("skip"," pp ans-1 correct answer number is: " + myQuestionAnswer);
                    btn_answer1.setBackgroundColor(Color.GREEN);
                    //  ??  ??
                    changePictureMode(PASS_MODE);
                    correct += 1;
                }else{
                    //  ??  ??
                    myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    Log.d("skip"," ff ans-1 correct answer number is: " + myQuestionAnswer);
                    btn_answer1.setBackgroundColor(Color.RED);
                    btnArray[myQuestionAnswer].setBackgroundColor(Color.GREEN);
                    //  ??  ??
                    changePictureMode(FAIL_MODE);
                }
                tries += 1;
                scoreCount();
            }
        });
        btn_answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next.setVisibility(View.VISIBLE);
                disableAnswerBtn(true);
                if(questionManager.checkAnswer(2)){
                    //  ??  ??
                    myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    Log.d("skip"," pp ans-2 correct answer number is: " + myQuestionAnswer);
                    btn_answer2.setBackgroundColor(Color.GREEN);
                    //  ??  ??
                    changePictureMode(PASS_MODE);
                    correct += 1;
                }else{
                    //  ??  ??
                    myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    Log.d("skip"," ff ans-2 correct answer number is: " + myQuestionAnswer);
                    btn_answer2.setBackgroundColor(Color.RED);
                    btnArray[myQuestionAnswer].setBackgroundColor(Color.GREEN);

                    //  ??  ??
                    changePictureMode(FAIL_MODE);
                }
                tries += 1;
                scoreCount();
            }
        });
        btn_answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next.setVisibility(View.VISIBLE);
                disableAnswerBtn(true);
                if(questionManager.checkAnswer(3)){
                    //  ??  ??
                    myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    Log.d("skip"," pp ans-3 correct answer number is: " + myQuestionAnswer);
                    btn_answer3.setBackgroundColor(Color.GREEN);
                    //  ??  ??
                    changePictureMode(PASS_MODE);
                    correct += 1;
                }else{
                    //  ??  ??
                    myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    Log.d("skip"," ff ans-3 correct answer number is: " + myQuestionAnswer);
                    btn_answer3.setBackgroundColor(Color.RED);
                    btnArray[myQuestionAnswer].setBackgroundColor(Color.GREEN);
                    //  ??  ??
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

        Log.d("skip","code for oncreate");
        QuestionItem questionItem = questionManager.getNext();  //  ?????????
        setQuestionScreen(questionItem);  //  ????????????
    }

    private void setQuestionScreen(QuestionItem questionItem) {
        Log.d("skip","setquestionscreen");
        iv_questionimage.setImageResource(imageMap.get(questionItem.catagory));  //  ??  new new  test  ??  ??  ??
        tv_question.setText(questionItem.question);
        btn_answer0.setText(questionItem.answers[0]);
        btn_answer1.setText(questionItem.answers[1]);
        btn_answer2.setText(questionItem.answers[2]);
        btn_answer3.setText(questionItem.answers[3]);

        //  ??  new code to get size or num of questions  ?????????????????????????????????????????????????
        numQuestions = questionManager.questionitems.size();
        Log.d("skip", " ??  number of questions " + numQuestions);
    }

    private void changePictureMode(int mode){

        switch (mode) {
            case QUESTION_MODE:
                iv_questionimage.setVisibility(View.VISIBLE);
                tv_tryagain.setVisibility(View.INVISIBLE);
                tv_details.setVisibility(View.INVISIBLE);
                break;

            case PASS_MODE:
                Log.d("skip", "pass show happy face");
                iv_questionimage.setImageResource(R.mipmap.happyface);  //  ??  new new  test  ??  ??  ??
                iv_questionimage.setVisibility(View.VISIBLE);
                tv_tryagain.setVisibility(View.INVISIBLE);
                tv_details.setVisibility(View.INVISIBLE);
                break;

            case FAIL_MODE:
                Log.d("skip", "fail show sad face");
                iv_questionimage.setImageResource(R.mipmap.sadface);  //  ??  new new  test  ??  ??  ??
                iv_questionimage.setVisibility(View.VISIBLE);
                tv_tryagain.setVisibility(View.INVISIBLE);
                tv_details.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void scoreCount() {
        tv_tries.setText(" # of tries = " + String.valueOf(tries));
        tv_correct.setText(" # correct = " + String.valueOf(correct));
    }

    private void disableAnswerBtn(boolean disable){
        btn_answer0.setEnabled(!disable);
        btn_answer1.setEnabled(!disable);
        btn_answer2.setEnabled(!disable);
        btn_answer3.setEnabled(!disable);
    }

    //  ??  do end math  ??
    //  ??  do end math  ??
    //  ??  do end math  ??

    /*  ??
    int tries = 0;
    int correct = 0;
    int testScore = 0;
    int numCorrect = 0;  //  new new
    int numQuestions = 0; //  new new
    int myQuestionAnswer = -0;  //  new new

    private void showEndScreen(){

        numCorrect = correct;  //  new new  ??  ??
        double numofQuestions = numQuestions;  //  ??  ??  get lenght from json  ??  ??
        Log.d("skip","numcorrect = " + numCorrect);
        testScore = (int)(((numCorrect/numofQuestions)*100));  //  ??  cast to int  ??
        Log.d("skip","testScore is = " + testScore + " %");

        Intent intent = new Intent(MainActivity.this, TestCompleteActivity.class);

        intent.putExtra("tries", tries);
        intent.putExtra("correct", correct);
        //  ??  intent.putExtra("passTest",passTest);  //  ??  ??

        startActivity(intent);
    }

   */

    private void testComplete() {  //  ??  ??  got to end of test  ??  ??

        // if(testScore < 70) {
        //     Log.d("skip", "you failed !  " + testScore + " %");
        //     iv_questionimage.setImageResource(R.mipmap.sadface);  //  ??  new new  test
        // }else{
        //     Log.d("skip", "you passed !!!!!!  " + testScore + " %");
        //     iv_questionimage.setImageResource(R.mipmap.happyface);  //  ??  new new  test
        // }

        Intent intent = new Intent(MainActivity.this, TestComplete.class);

        intent.putExtra("tries", tries);
        intent.putExtra("correct", correct);
        intent.putExtra("numQuestions", numQuestions);

        startActivity(intent);
    }

    /*
    private void showDialog() {

        if(testScore < 70) {
            new AlertDialog.Builder(this)
                    .setTitle(" Test Results ")
                    .setMessage(" Sorry you did not passed, you can retake the test ")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        if(testScore > 70) {
            new AlertDialog.Builder(this)
                    .setTitle(" Test Results ")
                    .setMessage(" Congrates you passed, you are smarter than a 6th grader !!! ")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    //  ??  do end math  ??
    //  ??  do end math  ??
    //  ??  do end math  ??

    */

    private void gotoMenu() {
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    private void setupImageMap(){
        imageMap.put("oldtype",R.mipmap.njreal01);  //  ??  ??  set all tyes in  both old and new file  ??  ??
        imageMap.put("law",R.mipmap.law);
        imageMap.put("commision",R.mipmap.math);
        imageMap.put("advirtising",R.mipmap.advirtising);
        imageMap.put("ownership",R.mipmap.ownership2);
        imageMap.put("sad",R.mipmap.sadface);
        imageMap.put("happy",R.mipmap.happyface);

    }
}