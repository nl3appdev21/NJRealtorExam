package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TestComplete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcomplete);

        //  ??  new code
        Button btnm = findViewById(R.id.btn_menu);
        btnm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMenu();
            }
        });

        //  ??  new code
        Button btnr = findViewById(R.id.btn_retest);
        btnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeTest();
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