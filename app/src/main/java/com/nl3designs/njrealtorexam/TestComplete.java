package com.nl3designs.njrealtorexam;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TestComplete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcomplete);

        Log.d("skip","new new test-comp");  //  ??
        Log.d("skip","new new test-comp");  //  ??


        //  ??  do end of test  ??
        //  ??  do end of test  ??
        //  ??  do end of test  ??


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

        private void testComplete() {  //  ??  ??  got to end of test  ??  ??

            if(testScore < 70) {
                Log.d("skip", "you failed !  " + testScore + " %");
                iv_questionimage.setImageResource(R.mipmap.sadface);  //  ????????????????????
            }else{
                Log.d("skip", "you passed !!!!!!  " + testScore + " %");
                iv_questionimage.setImageResource(R.mipmap.happyface);  //  ????????????????????
            }

            Intent intent = new Intent(MainActivity.this, TestComplete.class);
            startActivity(intent);
        }

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


        //  ??  do end of test  ??
        //  ??  do end of test  ??
        //  ??  do end of test  ??


        //  ??  new code
        Button btnr = findViewById(R.id.btn_takeTest);
        btnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeTest();
            }
        });

        //  ??  new code
        Button btnm = findViewById(R.id.btn_menu);
        btnm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMenu();
            }
        });
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