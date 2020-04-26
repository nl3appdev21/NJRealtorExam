package com.nl3designs.njrealtorexam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private Button BtnActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        BtnActivity = findViewById(R.id.btn_flashCards);

        BtnActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFlashCards();
            }
        });

        BtnActivity = findViewById(R.id.btn_takeTest);

        BtnActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTest();
            }
        });

    }

    private void startFlashCards() {

        Intent intent = new Intent(MenuActivity.this, FlashActivity.class);
        startActivity(intent);
    }

    private void startTest() {

        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(intent);
    }
}