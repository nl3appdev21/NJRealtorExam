package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    TextView mTextField;
    MediaPlayer mySound;
    CountDownTimer myTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Button btn = findViewById(R.id.btn_stop);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySound.stop();
                startGames();
            }
        });

        mySound = MediaPlayer.create(this,R.raw.guitarjingle4);
        mySound.start();

        myTimer = new CountDownTimer( 24000,700 ) {

            public   void   onTick ( long   millisUntilFinished ) {
            }

            public   void   onFinish () {
                mySound.stop();
                startGames();
            }
        }.start ();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mySound.release();
    }

    private void startGames(){
        myTimer.cancel();
        Intent myIntent = new Intent(this, MenuActivity.class);
        startActivity(myIntent);
        finish();
    }
}