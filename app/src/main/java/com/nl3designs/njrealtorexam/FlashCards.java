package com.nl3designs.njrealtorexam;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class FlashCards extends AppCompatActivity {

        private TextView tv_question;
        private Button btn_answer, btn_answer0, btn_answer1, btn_answer2, btn_answer3;
        private Button btn_menu;
        private Button[] btnArray = new Button[4];
        private ImageView iv_questionimage;
        private QuestionManager questionManager;
        public static String results = "";
        StorageManager storageManager;

        private TextView tv_tries;
        public static final int PASS_MODE = 1;  //  ????????????????????????????????
        public static final int QUESTION_MODE = 0;  //  ??????????????????????????????
        int tries = 0;
        int numQuestions = 0;
        int myQuestionAnswer = -0;
        int myans = -0;
        String myflashans = "";
        String myflashans3 = "";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.flashcards);

            storageManager = new StorageManager(this);
            if(results.equals("")){
                results = storageManager.load("results");
            }

            questionManager = new QuestionManager(this);
            numQuestions = questionManager.questionitems.size();
            tv_question = findViewById(R.id.tv_question);
            btn_answer = findViewById(R.id.btn_answer);
            btnArray[0] = btn_answer0;
            btnArray[1] = btn_answer1;
            btnArray[2] = btn_answer2;
            btnArray[3] = btn_answer3;
            tv_tries = findViewById(R.id.tries);
            btn_menu = findViewById(R.id.btn_menu);
            iv_questionimage = findViewById(R.id.questionImage);
            final Button btn_next = findViewById(R.id.btn_next);

            btn_next.setOnClickListener(new View.OnClickListener() {  //  ??  onclick listener for next button

                @Override
                public void onClick(View v) {

                    btn_next.setVisibility(View.INVISIBLE);
                    myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    myans = myQuestionAnswer;
                    enableAnswerBtn();
                    // myQuestionAnswer = -0;
                    // myans = -0;

                    changePictureMode(QUESTION_MODE);  //  help help help  ?????????????????????????
                    QuestionItem questionItem = questionManager.getNext();  //  ?? call to question manager ??
                    Log.d("skip", " new 00 myans = " + myans);
                    if (questionItem != null) {  // if not null
                        setQuestionScreen(questionItem);
                        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                        myans = myQuestionAnswer;
                        Log.d("skip", " new 11 myans = " + myans);
                    } else {
                        questionManager.currentIndex = 0;
                    }
                }
            });

            btn_answer.setOnClickListener(new View.OnClickListener() {  //  ??  onclick listener for answer button
                @Override
                public void onClick(View v) {

                    btn_next.setVisibility(View.VISIBLE);
                    //  myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    //  myans = myQuestionAnswer;
                    Log.d("skip", " 3 this is myans = " + myans);
                    Log.d("skip", " aa this is myans = " + myans);
                    disableAnswerBtn();
                    showAnswer();

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

            btn_answer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Green_08));
            btn_answer.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.White));
            btn_answer.setText("show answer");

            tries += 1;
            iv_questionimage.setImageResource(questionManager.categoryMap.get(questionItem.catagory));
            scoreCount();
            tv_question.setText(questionItem.question);

            // btn_answer0.setText(questionItem.answers[0]);
            // btn_answer1.setText(questionItem.answers[1]);
            // btn_answer2.setText(questionItem.answers[2]);
            // btn_answer3.setText(questionItem.answers[3]);

            Log.d("skip", "ans 00" + questionItem.answers[0]);
            Log.d("skip", "ans 11" + questionItem.answers[1]);
            Log.d("skip", "ans 22" + questionItem.answers[2]);
            Log.d("skip", "ans 33" + questionItem.answers[3]);

            switch (myans) {
                case 0:
                    myflashans = questionItem.answers[0];
                    Log.d("skip", " correct = " + myans + "  -  " + " 00a myflashans = " + myflashans);
                    // btn_answer.setText(myflashans);
                    break;

                case 1:
                    myflashans = questionItem.answers[1];
                    Log.d("skip", " correct = " + myans + "  -  " + " 11a myflashans = " + myflashans);
                    //  btn_answer.setText(myflashans);
                    break;

                case 2:
                    myflashans = questionItem.answers[2];
                    Log.d("skip", " correct = " + myans + "  -  " + " 22a myflashans = " + myflashans);
                    //  btn_answer.setText(myflashans);
                    break;

                case 3:
                    myflashans = questionItem.answers[3];
                    Log.d("skip", " correct = " + myans + "  -  " + " 33a myflashans = " + myflashans);
                    //  btn_answer.setText(myflashans);
                    break;

            }

            //  btn_answer.setText(myflashans3);

        }

    private void showAnswer(){

        // btn_answer0.setText(questionItem.answers[0]);
        // btn_answer1.setText(questionItem.answers[1]);
        // btn_answer2.setText(questionItem.answers[2]);
        // btn_answer3.setText(questionItem.answers[3]);
        btn_answer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Yellow_08));
        btn_answer.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.Black));

        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
        myans = myQuestionAnswer;
        myflashans3 = myflashans;
        btn_answer.setText(questionManager.getCurrentAnswer());
/*
        switch (myans) {
            case 0:
                // myflashans = questionItem.answers[0];
                Log.d("skip", " correct = " + myans + "  -  " + " 00b myflashans = " + myflashans);
                btn_answer.setText(myflashans3);
                break;

            case 1:
                // myflashans = questionItem.answers[1];
                Log.d("skip", " correct = " + myans + "  -  " + " 11b myflashans = " + myflashans);
                btn_answer.setText(myflashans3);
                break;

            case 2:
                // myflashans = questionItem.answers[2];
                Log.d("skip", " correct = " + myans + "  -  " + " 22b myflashans = " + myflashans);
                btn_answer.setText(myflashans3);
                break;

            case 3:
                // myflashans = questionItem.answers[3];
                Log.d("skip", " correct = " + myans + "  -  " + " 33b myflashans = " + myflashans);
                btn_answer.setText(myflashans3);
                break;

        }
        */

    }

        private void changePictureMode(int mode){ // set picture and text for answer

            if (tries == numQuestions) {  //  ??????????????????????????????????????????
                testComplete();
            }

                switch (mode) {
                case QUESTION_MODE:
                    iv_questionimage.setVisibility(View.VISIBLE);
                    break;

                case PASS_MODE:
                    iv_questionimage.setImageResource(R.mipmap.goldbulb);
                    iv_questionimage.setVisibility(View.VISIBLE);
                    break;
            }
        }

        private void scoreCount() {

            tv_tries.setText(String.valueOf(tries)+"/"+numQuestions);
        }

        private void disableAnswerBtn() {

            btn_answer.setClickable(false);
        }

        private void enableAnswerBtn() {

            btn_answer.setClickable(true);
            //  btn_answer.setText("Show Answer");
        }

        private void testComplete() {

            new AlertDialog.Builder(this)
                    .setTitle("Done with flashcards")
                    .setMessage("Congrates the force is strong with you, your test awaits you!")
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

            Intent intent = new Intent(com.nl3designs.njrealtorexam.FlashCards.this, MenuActivity.class);
            startActivity(intent);
        }

        private void gotoMenu() {
            Intent intent = new Intent(com.nl3designs.njrealtorexam.FlashCards.this, MenuActivity.class);
            startActivity(intent);
        }
    }