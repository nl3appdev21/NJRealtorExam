package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class FlashCards extends AppCompatActivity {

        private TextView tv_question;
        private TextView tv_correct;
        private Button btn_answer;
        private Button btn_menu;
        private Button[] btnArray = new Button[4];
        private ImageView iv_questionimage;
        private QuestionManager questionManager;
        public static String results = "";
        StorageManager storageManager;

        private TextView tv_tries;
        public static final int PASS_MODE = 1;
        public static final int QUESTION_MODE = 0;
        int tries = 0;
        int numQuestions = 0;
        int myQuestionAnswer = -0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.flashcards);

            storageManager = new StorageManager(this);
            if(results.equals("")){
                results = storageManager.load("results");
            }

            questionManager = new QuestionManager(this);
            tv_question = findViewById(R.id.tv_question);
            btn_answer = findViewById(R.id.btn_answer);

            btnArray[0] = btn_answer;  // array to set wrong answer backround to red

            tv_tries = findViewById(R.id.tries);
            tv_correct = findViewById(R.id.correct);  //  ??
            btn_menu = findViewById(R.id.btn_menu);
            iv_questionimage = findViewById(R.id.questionImage);

            final Button btn_next = findViewById(R.id.btn_next);
            btn_next.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    btn_next.setVisibility(View.INVISIBLE);
                    enableAnswerBtn();
                    myQuestionAnswer = -0;

                    changePictureMode(QUESTION_MODE);
                    QuestionItem questionItem = questionManager.getNext();  //  ?? call to question manager ??
                    if (questionItem != null) {  // if not null
                        setQuestionScreen(questionItem);
                    } else {
                        questionManager.currentIndex = 0;
                        //  ??  testComplete();  // new code runs end-of-test
                    }
                }
            });

            btn_answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    btn_next.setVisibility(View.VISIBLE);
                    if(questionManager.checkAnswer(0)){
                        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                        showAnswer(PASS_MODE);
                        //  ??  changePictureMode(PASS_MODE);
                    }
                    tries += 1;
                    //  ??  scoreCount();
                    //  ??  disableAnswerBtn();
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

            iv_questionimage.setImageResource(questionManager.categoryMap.get(questionItem.catagory));
            tv_question.setText(questionItem.question);
            btn_answer.setText(questionItem.answers[0]);  //  ??

            numQuestions = questionManager.questionitems.size(); // new code to gets num of questions
        }

    private void showAnswer(int mode){ // set picture and text for answer
    //  ??  private void showAnswer(int mode){ // set picture and text for answer

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

        private void changePictureMode(int mode){ // set picture and text for answer

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

        // new code new code
        private void disableAnswerBtn() {
            btn_answer.setClickable(false);
        }

        private void enableAnswerBtn() {

            // new code new code
            btn_answer.setClickable(true);

            btn_answer.setTextColor(Color.WHITE);

            // use colors from my colors file !
            btn_answer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.LtBlue_08));

        }

        private void testComplete() {  // new code for end-of-test

            Intent intent = new Intent(com.nl3designs.njrealtorexam.FlashCards.this, TestComplete.class);

            intent.putExtra("tries", tries);
            intent.putExtra("numQuestions", numQuestions);

            startActivity(intent);
        }

        private void gotoMenu() {
            Intent intent = new Intent(com.nl3designs.njrealtorexam.FlashCards.this, MenuActivity.class);
            startActivity(intent);
        }
    }