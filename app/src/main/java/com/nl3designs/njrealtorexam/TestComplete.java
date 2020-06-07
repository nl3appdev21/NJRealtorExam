package com.nl3designs.njrealtorexam;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class TestComplete extends AppCompatActivity {

    int tries;
    int correct;
    int numQuestions;
    int testScore;
    double numOfQuestions;

    ImageView iv_passFail;
    TextView tv_testResults1;
    TextView tv_testResults2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcomplete);

        iv_passFail = findViewById(R.id.iv_passFail);
        tv_testResults1 = findViewById(R.id.tv_testResults1);
        tv_testResults2 = findViewById(R.id.tv_testResults2);

        Intent intent = getIntent();
        TextView results1 = (TextView) findViewById(R.id.tv_testResults1);
        TextView results2 = (TextView) findViewById(R.id.tv_testResults2);
        ImageView passfail = (ImageView) findViewById(R.id.passFail);

        tries = getIntent().getIntExtra("tries",0);
        correct = getIntent().getIntExtra("correct",0);
        numQuestions = getIntent().getIntExtra("numQuestions",0);
        numOfQuestions = numQuestions;

        getScore();
        showDialogBox();
        testComplete();

        Button btnr = findViewById(R.id.btn_takeTest);
        btnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeTest();
            }
        });

        Button btnm = findViewById(R.id.btn_menu);
        btnm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMenu();
            }
        });
    }

    private void getScore() {

        testScore = (int)(((correct/numOfQuestions)*100));  // cast testscore to int
    }

    private void showDialogBox() {

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
        }else{
            new AlertDialog.Builder(this)
                    .setTitle(" Test Results ")
                    .setMessage(" Congrates you passed, you are smarter than a 6th grader! ")
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

    private void testComplete(){

        if(testScore < 70) {
            iv_passFail.setImageResource(R.mipmap.redquitbtn);
            tv_testResults1.setText(" sorry you got " + correct + " of " + numQuestions + " correct ");
            tv_testResults2.setText(" your score is: " + testScore + "%" + " , retake test");
            tv_testResults1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Red_08));
            tv_testResults2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Red_08));
        }else{
            iv_passFail.setImageResource(R.mipmap.bluecert);
            tv_testResults1.setText(" congrats you got " + correct + " of " + numQuestions + " correct ");
            tv_testResults2.setText(" your passing score is: " + testScore + "%");
            tv_testResults1.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.Black));
            tv_testResults2.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.Black));
            tv_testResults1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Yellow_08));
            tv_testResults2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Yellow_08));
        }
    }

    private void takeTest(){
        Intent intent = new Intent(TestComplete.this, MainActivity.class);
        startActivity(intent);
    }

    private void gotoMenu(){
        Intent intent = new Intent(TestComplete.this, MenuActivity.class);
        startActivity(intent);
    }
}