package com.nl3designs.njrealtorexam;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class FlashCards extends AppCompatActivity {

    private TextView tv_question;
    private Button btn_answer;
    private Button btn_menu;
    private Button[] btnArray = new Button[4];
    private ImageView iv_questionimage;
    private QuestionManager questionManager;
    public static String results = "";
    StorageManager storageManager;
    private TextView tv_tries;
    int tries = 0;
    int numQuestions = 0;
    int myQuestionAnswer = -0;
    int myans = -0;

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
        tv_tries = findViewById(R.id.tries);
        btn_menu = findViewById(R.id.btn_menu);
        iv_questionimage = findViewById(R.id.questionImage);
        final Button btn_next = findViewById(R.id.btn_next);

        btn_next.setOnClickListener(new View.OnClickListener() {  //  ??  onclick listener for next button

            @Override
            public void onClick(View v) {

                // btn_next.setVisibility(View.INVISIBLE);
                myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                myans = myQuestionAnswer;
                enableAnswerBtn();
                checkForEnd();
                QuestionItem questionItem = questionManager.getNext();

                if (questionItem != null) {  // if not null
                    setQuestionScreen(questionItem);
                    myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                    myans = myQuestionAnswer;
                } else {
                    questionManager.currentIndex = 0;
                }
            }
        });

        btn_answer.setOnClickListener(new View.OnClickListener() {  //  ??  onclick listener for answer button
            @Override
            public void onClick(View v) {

                btn_next.setVisibility(View.VISIBLE);
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

        ////**************************************************
        Switch sw = findViewById(R.id.randSwitch);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                StorageManager store = new StorageManager(FlashCards.this);
                store.save(String.valueOf(isChecked),"flashRand");
                Log.d("skip",String.valueOf(isChecked));
            }
        });

    }

    private void setQuestionScreen(QuestionItem questionItem) {

        btn_answer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Green_08));
        btn_answer.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.White));
        btn_answer.setText("show answer");
        tries += 1;
        iv_questionimage.setImageResource(questionManager.categoryMap.get(questionItem.catagory));
        scoreCount();
        tv_question.setText(questionItem.question);

    }

    private void showAnswer(){

        btn_answer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Yellow_08));
        btn_answer.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.Black));
        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
        myans = myQuestionAnswer;
        btn_answer.setText(questionManager.getCurrentAnswer());

    }

        private void checkForEnd(){

            if (tries == numQuestions) {
                endOfCards();
            }

        }

        private void scoreCount() {

            tv_tries.setText(String.valueOf(tries)+" / "+numQuestions);
        }

        private void disableAnswerBtn() {

            btn_answer.setClickable(false);
        }

        private void enableAnswerBtn() {

            btn_answer.setClickable(true);
            //  btn_answer.setText("Show Answer");
        }

        private void endOfCards() {

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