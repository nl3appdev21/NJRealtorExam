package com.nl3designs.njrealtorexam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FlashActivity extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        btn = findViewById(R.id.btn_menu);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMenu();
            }
        });

        btn = findViewById(R.id.btn_takeTest);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTest();
            }
        });

    }

    private void gotoMenu() {
        finish();
    }

    private void startTest() {

        Intent intent = new Intent(FlashActivity.this, MainActivity.class);
        //  Intent intent = new Intent(MenuActivity.this, TestActivity.class);
        startActivity(intent);
    }

}