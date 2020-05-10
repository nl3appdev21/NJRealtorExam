package com.nl3designs.njrealtorexam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
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

    //  private TextView tv_passTest;  //  ??
    private Button btn_answer0, btn_answer1, btn_answer2, btn_answer3;
    //  ??  private Button btn_next;
    private Button btn_menu;
    private ImageView iv_questionimage;
    private QuestionManager questionManager;
    public static final int QUESTION_MODE = 0;
    public static final int PASS_MODE = 1;
    public static final int FAIL_MODE = 2;
    // private int pictureMode = QUESTION_MODE;
    int tries = 0;
    int correct = 0;
    //  ??  int passTest = 21;  //  ?? beta passtest code change from 0 to 21
    int numCorrect = 0;  //  new new
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
        tv_details = findViewById(R.id.details);
        tv_tryagain = findViewById(R.id.tryagain);
        tv_tries = findViewById(R.id.tries);
        tv_correct = findViewById(R.id.correct);
        btn_menu = findViewById(R.id.menu);
        //  ??  btn_next = findViewById(R.id.next);
        iv_questionimage = findViewById(R.id.questionImage);

        final Button btn_next = findViewById(R.id.next);
        btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btn_next.setVisibility(View.INVISIBLE);
                changePictureMode(QUESTION_MODE);
                QuestionItem questionItem = questionManager.getNext();
                if (questionItem != null) {
                    setQuestionScreen(questionItem);
                } else {
                    //  new beta code
                    showEndScreen();
                    questionManager.currentIndex = 0;

                    //  new beta code for dialog bow
                    showDialog();  //  ?? beta call dialog box
                    testComplete();  //  new code for eot !
                    //  showLeaderBoard();  //  ??  beta call  leader board
                    //  new beta code for dialog bow
                }
            }
        });

        Button btn0 = findViewById(R.id.answer_0);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next.setVisibility(View.VISIBLE);
                if(questionManager.checkAnswer(0)){
                    tv_details.setText(questionManager.getCurrentQuestion().details);
                    changePictureMode(PASS_MODE);
                    correct += 1;
                }else{
                    changePictureMode(FAIL_MODE);
                }
                tries += 1;
                scoreCount();
            }
        });
        btn0 = findViewById(R.id.answer_1);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next.setVisibility(View.VISIBLE);
                if(questionManager.checkAnswer(1)){
                    changePictureMode(PASS_MODE);
                    correct += 1;
                }else{
                    changePictureMode(FAIL_MODE);
                }
                tries += 1;
                scoreCount();
            }
        });
        btn0 = findViewById(R.id.answer_2);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next.setVisibility(View.VISIBLE);
                if(questionManager.checkAnswer(2)){
                    changePictureMode(PASS_MODE);
                    correct += 1;
                }else{
                    changePictureMode(FAIL_MODE);
                }
                tries += 1;
                scoreCount();
            }
        });
        btn0 = findViewById(R.id.answer_3);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next.setVisibility(View.VISIBLE);
                if(questionManager.checkAnswer(3)){
                    changePictureMode(PASS_MODE);
                    correct += 1;
                }else{
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
        iv_questionimage.setImageResource(imageMap.get(questionItem.catagory));  //  ??  new new  test
        tv_question.setText(questionItem.question);
        btn_answer0.setText(questionItem.answers[0]);
        btn_answer1.setText(questionItem.answers[1]);
        btn_answer2.setText(questionItem.answers[2]);
        btn_answer3.setText(questionItem.answers[3]);
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
                iv_questionimage.setImageResource(R.mipmap.happyface);  //  ??  new new  test
                iv_questionimage.setVisibility(View.VISIBLE);
                //  ??  iv_questionimage.setVisibility(View.INVISIBLE);
                tv_tryagain.setVisibility(View.INVISIBLE);
                tv_details.setVisibility(View.INVISIBLE);
                //  ??  tv_details.setVisibility(View.VISIBLE);

                break;

            case FAIL_MODE:
                Log.d("skip", "fail show sad face");
                iv_questionimage.setImageResource(R.mipmap.sadface);  //  ??  new new  test
                iv_questionimage.setVisibility(View.VISIBLE);
                //  ??  iv_questionimage.setVisibility(View.INVISIBLE);
                tv_tryagain.setVisibility(View.INVISIBLE); // was visible
                tv_details.setVisibility(View.INVISIBLE);

                break;
        }

    }

    private void scoreCount() {

        numCorrect = correct;  //  new new

        if(numCorrect >= 4){
        }

        tv_tries.setText(" # of tries = " + String.valueOf(tries));
        tv_correct.setText(" # correct = " + String.valueOf(correct));

    }

    private void showEndScreen(){
        Intent intent = new Intent(MainActivity.this, TestCompleteActivity.class);

        intent.putExtra("tries", tries);
        intent.putExtra("correct", correct);
        //  ??  intent.putExtra("passTest",passTest);  //  ??

        startActivity(intent);
    }

    private void showDialog() {
        new AlertDialog.Builder(this
        )
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

    //  new beta code

    private void testComplete() {    //  test complete
        iv_questionimage.setImageResource(R.mipmap.happyface);  //  ??  new new  test
        Intent intent = new Intent(MainActivity.this, TestComplete.class);
        startActivity(intent);
    }

    private void gotoMenu() {
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    private void setupImageMap(){
        imageMap.put("law",R.mipmap.law);
        imageMap.put("commision",R.mipmap.math);
        imageMap.put("advirtising",R.mipmap.advirtising);
        imageMap.put("ownership",R.mipmap.ownership2);
        imageMap.put("sad",R.mipmap.sadface);
        imageMap.put("happy",R.mipmap.happyface);

    }
}