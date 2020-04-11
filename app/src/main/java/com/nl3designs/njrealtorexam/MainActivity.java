package com.nl3designs.njrealtorexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_question;
    private TextView tv_details;
    private TextView tv_tryagain;
    private Button btn_answer0, btn_answer1, btn_answer2, btn_answer3;
    private Button btn_next;
    private Button btn_mainmenu;
    private ImageView iv_questionimage;
    private QuestionManager questionManager;
    public static final int QUESTION_MODE = 0;
    public static final int PASS_MODE = 1;
    public static final int FAIL_MODE = 2;
    private int pictureMode = QUESTION_MODE;

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
        iv_questionimage = findViewById(R.id.questionImage);
        changePictureMode(QUESTION_MODE);

        final Button btn_next = findViewById(R.id.next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePictureMode(QUESTION_MODE);
                QuestionItem questionItem = questionManager.getNext();
                setQuestionScreen(questionItem);
            }
        });

        Button btn0 = findViewById(R.id.answer_0);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionManager.checkAnswer(0)){
                    tv_details.setText(questionManager.getCurrentQuestion().details);
                    changePictureMode(PASS_MODE);
                    btn_next.setVisibility(View.VISIBLE);
                }else{
                    changePictureMode(FAIL_MODE);
                }
            }
        });
        btn0 = findViewById(R.id.answer_1);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionManager.checkAnswer(1)){
                    changePictureMode(PASS_MODE);
                    btn_next.setVisibility(View.VISIBLE);
                }else{
                    changePictureMode(FAIL_MODE);
                }
            }
        });
        btn0 = findViewById(R.id.answer_2);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionManager.checkAnswer(2)){
                    changePictureMode(PASS_MODE);
                    btn_next.setVisibility(View.VISIBLE);
                }else{
                    changePictureMode(FAIL_MODE);
                }
            }
        });        btn0 = findViewById(R.id.answer_3);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionManager.checkAnswer(3)){
                    changePictureMode(PASS_MODE);
                    btn_next.setVisibility(View.VISIBLE);
                }else{
                    changePictureMode(FAIL_MODE);
                }
            }
        });

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

    private void changePictureMode(int mode){
        switch (mode) {
            case QUESTION_MODE:
                iv_questionimage.setVisibility(View.VISIBLE);
                tv_tryagain.setVisibility(View.INVISIBLE);
                tv_details.setVisibility(View.INVISIBLE);

                break;

            case PASS_MODE:
                iv_questionimage.setVisibility(View.INVISIBLE);
                tv_tryagain.setVisibility(View.INVISIBLE);
                tv_details.setVisibility(View.VISIBLE);

                break;

            case FAIL_MODE:
                iv_questionimage.setVisibility(View.INVISIBLE);
                tv_tryagain.setVisibility(View.VISIBLE);
                tv_details.setVisibility(View.INVISIBLE);

                break;
        }
    }

}