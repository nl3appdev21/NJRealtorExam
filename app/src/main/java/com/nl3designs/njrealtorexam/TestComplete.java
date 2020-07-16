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
    private Button btnlb;
    ImageView iv_passFail;
    TextView tv_testResults1;
    TextView tv_testResults2;
    StorageManager store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcomplete);
        store = new StorageManager(this);
        Log.d("skip", "test comp comp comp !!!");

        btnlb = findViewById(R.id.btn_lb);
        iv_passFail = findViewById(R.id.iv_passFail);
        tv_testResults1 = findViewById(R.id.tv_testResults1);
        tv_testResults2 = findViewById(R.id.tv_testResults2);

        Intent intent = getIntent();
        //TextView results1 = (TextView) findViewById(R.id.tv_testResults1);
        //TextView results2 = (TextView) findViewById(R.id.tv_testResults2);
        // ImageView passfail = (ImageView) findViewById(R.id.passFail);  // ?????????????????

        //results1.setText(String.valueOf(getIntent().getIntExtra("results1", 0)));
        //Log.d("skip", "results1 = !!! " + results1);
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

        Button btnlb = findViewById(R.id.btn_lb);
        btnlb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLb();
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
            btnlb.setVisibility(View.INVISIBLE);
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

        saveScore();
    }

    private void saveScore(){
        String leaderBoardData = store.load("leaderboard");
        //  String score = ";" + tries + "," + correct + "," + testScore;
        //  String score = "\n" + "right - " + correct + " of - " + tries + " score - " + testScore + "\n";
        String score = "\n" + "Skip : " + "got " + correct + " of " + tries + " correct for " + testScore + " %" + "\n";


        //  ??  nash got 4 of 5 correct for 60 %   ??

        // System.out.println("your score is  = " + score);
        leaderBoardData+= score;
        store.save(leaderBoardData,"leaderboard");
        showLb();
    }

    private void takeTest(){
        Log.d("skip", "retest 11111");
        Intent intent = new Intent(TestComplete.this, MainActivity.class);
        startActivity(intent);
    }

    private void showLb(){

        Log.d("skip", "lb lb 22222");
        //  score ??
        Intent intent = new Intent(TestComplete.this, LeaderBoard.class);
        startActivity(intent);
    }

    private void gotoMenu(){
        Log.d("skip", "menu 33333");
        Intent intent = new Intent(TestComplete.this, MenuActivity.class);
        startActivity(intent);
    }
}