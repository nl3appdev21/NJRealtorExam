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
        private TextView tv_tries;
        private TextView tv_correct;
        private TextView tv_quote;
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
        int setTextInt = 0;  //  ?? new new
        int numQuestions = 0;
        int myQuestionAnswer = -0;
        public static String results = "";
        StorageManager storageManager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            storageManager = new StorageManager(this);
            if (results.equals("")) {
                results = storageManager.load("results");
            }

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
            tv_tries = findViewById(R.id.tries);
            tv_correct = findViewById(R.id.correct);
            tv_quote = findViewById(R.id.quote);
            btn_menu = findViewById(R.id.menu);
            iv_questionimage = findViewById(R.id.questionImage);

            final Button btn_next = findViewById(R.id.next);
            btn_next.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    tv_quote.setVisibility(View.INVISIBLE);
                    btn_next.setVisibility(View.INVISIBLE);
                    enableAnswerBtn();
                    myQuestionAnswer = -0;

                    changePictureMode(QUESTION_MODE);
                    QuestionItem questionItem = questionManager.getNext();  //  ?? call to question manager ??
                    if (questionItem != null) {  // if not null
                        setQuestionScreen(questionItem);
                    } else {
                        questionManager.currentIndex = 0;
                        testComplete();  // new code runs end-of-test
                    }
                }
            });

            //  ??  what code goes here  ??
            //  if(questionManager.ckcg( ?? )){
            //    Log.d("skip", " this is a test of ckcg call ");
            //  }

            btn_answer0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    btn_next.setVisibility(View.VISIBLE);
                    if (questionManager.checkAnswer(0)) {
                        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                        btn_answer0.setBackgroundColor(Color.GREEN);
                        btn_answer0.setTextColor(Color.BLACK);
                        changePictureMode(PASS_MODE);
                        correct += 1;
                    } else {
                        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                        btn_answer0.setBackgroundColor(Color.RED);
                        btnArray[myQuestionAnswer].setBackgroundColor(Color.GREEN);
                        btnArray[myQuestionAnswer].setTextColor(Color.BLACK);
                        changePictureMode(FAIL_MODE);
                    }
                    tries += 1;
                    scoreCount();
                    disableAnswerBtn();
                }
            });
            btn_answer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    btn_next.setVisibility(View.VISIBLE);
                    if (questionManager.checkAnswer(1)) {
                        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                        btn_answer1.setBackgroundColor(Color.GREEN);
                        btn_answer1.setTextColor(Color.BLACK);
                        changePictureMode(PASS_MODE);
                        correct += 1;
                    } else {
                        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                        btn_answer1.setBackgroundColor(Color.RED);
                        btnArray[myQuestionAnswer].setBackgroundColor(Color.GREEN);
                        btnArray[myQuestionAnswer].setTextColor(Color.BLACK);
                        changePictureMode(FAIL_MODE);
                    }
                    tries += 1;
                    scoreCount();
                    disableAnswerBtn();
                }
            });
            btn_answer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    btn_next.setVisibility(View.VISIBLE);
                    if (questionManager.checkAnswer(2)) {
                        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                        btn_answer2.setBackgroundColor(Color.GREEN);
                        btn_answer2.setTextColor(Color.BLACK);
                        changePictureMode(PASS_MODE);
                        correct += 1;
                    } else {
                        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                        btn_answer2.setBackgroundColor(Color.RED);
                        btnArray[myQuestionAnswer].setBackgroundColor(Color.GREEN);
                        btnArray[myQuestionAnswer].setTextColor(Color.BLACK);
                        changePictureMode(FAIL_MODE);
                    }
                    tries += 1;
                    scoreCount();
                    disableAnswerBtn();
                }
            });
            btn_answer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    btn_next.setVisibility(View.VISIBLE);
                    if (questionManager.checkAnswer(3)) {
                        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                        btn_answer3.setBackgroundColor(Color.GREEN);
                        btn_answer3.setTextColor(Color.BLACK);
                        changePictureMode(PASS_MODE);
                        correct += 1;
                    } else {
                        myQuestionAnswer = (questionManager.getCurrentQuestion().correct);
                        btn_answer3.setBackgroundColor(Color.RED);
                        btnArray[myQuestionAnswer].setBackgroundColor(Color.GREEN);
                        btnArray[myQuestionAnswer].setTextColor(Color.BLACK);
                        changePictureMode(FAIL_MODE);
                    }
                    tries += 1;
                    scoreCount();
                    disableAnswerBtn();
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
            btn_answer0.setText(questionItem.answers[0]);
            btn_answer1.setText(questionItem.answers[1]);
            btn_answer2.setText(questionItem.answers[2]);
            btn_answer3.setText(questionItem.answers[3]);

            numQuestions = questionManager.questionitems.size(); // new code to gets num of questions
        }

        private void changePictureMode(int mode) { // set picture and text for answer

            setTextInt++;

            switch (setTextInt) {
                case 1:
                    tv_quote.setText("You are the best!");
                    break;
                case 2:
                    tv_quote.setText("Can'nt stop won't stop!");
                    break;
                case 3:
                    tv_quote.setText("Super agent in the making!");
                    break;
                case 4:
                    tv_quote.setText("You are a super star!");
                    break;
                case 5:
                    tv_quote.setText("You are smart for a human!");
                    break;
                case 6:
                    tv_quote.setText("Hay can you see my answers?");
                    break;
                case 7:
                    Log.d("skip", "reset setTextInt");
                    setTextInt = 0;
                    break;
            }

            switch (mode) {
                case QUESTION_MODE:
                    iv_questionimage.setVisibility(View.VISIBLE);
                    break;

                case PASS_MODE:
                    //  iv_questionimage.setImageResource(R.mipmap.happyface); // new code happyface
                    iv_questionimage.setImageResource(R.mipmap.goldbulb);
                    tv_quote.setVisibility(View.VISIBLE);
                    tv_quote.setTextSize(25);
                    tv_quote.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.Yellow_06));
                    iv_questionimage.setVisibility(View.VISIBLE);
                    break;

                case FAIL_MODE:
                    //  iv_questionimage.setImageResource(R.mipmap.sadface); // new code sadface
                    iv_questionimage.setImageResource(R.mipmap.redxbtn);
                    tv_quote.setVisibility(View.VISIBLE);
                    tv_quote.setTextSize(15);
                    tv_quote.setText("Focus, you can do it!");
                    tv_quote.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.White));
                    iv_questionimage.setVisibility(View.VISIBLE);
                    break;
            }
        }

        private void scoreCount() {

            tv_tries.setText(String.valueOf(tries) + "/" + numQuestions);
            tv_correct.setText(String.valueOf(correct));

        }

        // new code new code
        private void disableAnswerBtn() {
            btn_answer0.setClickable(false);
            btn_answer1.setClickable(false);
            btn_answer2.setClickable(false);
            btn_answer3.setClickable(false);
        }

        private void enableAnswerBtn() {

            // new code new code
            btn_answer0.setClickable(true);
            btn_answer1.setClickable(true);
            btn_answer2.setClickable(true);
            btn_answer3.setClickable(true);

            btn_answer0.setTextColor(Color.WHITE);
            btn_answer1.setTextColor(Color.WHITE);
            btn_answer2.setTextColor(Color.WHITE);
            btn_answer3.setTextColor(Color.WHITE);

            // use colors from my colors file !
            btn_answer0.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.LtBlue_08));
            btn_answer1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.LtBlue_08));
            btn_answer2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.LtBlue_08));
            btn_answer3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.LtBlue_08));
        }

        private void testComplete() {  // new code for end-of-test

            Intent intent = new Intent(com.nl3designs.njrealtorexam.FlashCards.this, TestComplete.class);

            intent.putExtra("tries", tries);
            intent.putExtra("correct", correct);
            intent.putExtra("numQuestions", numQuestions);

            startActivity(intent);
        }

        private void gotoMenu() {
            Intent intent = new Intent(com.nl3designs.njrealtorexam.FlashCards.this, MenuActivity.class);
            startActivity(intent);
        }

    }
