package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private Button BtnTakeTest;
    private ImageButton IbtnCopyRight;
    private TextView TvMontra;
    private Button BtnTestInstuctions;
    private Button BtnFlashCards;
    private Button BtnSettings;
    private Button BtnLb;
    private ImageView IvDirections;
    private ImageView IvImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        QuestionManager questionManager = QuestionManager.getInstance();
        questionManager.loadAllQuestion(this,null);
        questionManager.reset();

        BtnFlashCards = findViewById(R.id.btn_flashcards);
        BtnFlashCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFlashCards();
            }
        });

        BtnSettings = findViewById(R.id.btn_settings);
        BtnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSettings();
            }
        });

        BtnTakeTest = findViewById(R.id.btn_takeTest);
        BtnTakeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTest();
            }
        });

        BtnTestInstuctions = findViewById(R.id.btn_testInstructions);
        BtnTestInstuctions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInstructions();
            }
        });

        BtnLb = findViewById(R.id.btn_lb);
        BtnLb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLb();
            }
        });

        IbtnCopyRight = findViewById(R.id.ibtn_logo);
        TvMontra = findViewById(R.id.tv_montra);
        IbtnCopyRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCopyRight();
            }
        });
    }

    private void showInstructions() {

        Intent intent = new Intent(MenuActivity.this, Instructions.class);
        startActivity(intent);
    }

    private void showLb() {
        Intent intent = new Intent(MenuActivity.this, LeaderBoard_Bkup.class);
        //  ????????  Intent intent = new Intent(MenuActivity.this, LeaderBoard.class);
        startActivity(intent);

    }

    private void showCopyRight() {
        //  QuestionManager.getInstance().loadAllQuestion(this, "commission");  //  ??
        Intent intent = new Intent(MenuActivity.this, CopyRight.class);
        startActivity(intent);
    }

    private void startTest() {

        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void startFlashCards() {

        Intent intent = new Intent(MenuActivity.this, FlashCards.class);
        startActivity(intent);
    }

    private void showSettings() {

        Intent intent = new Intent(MenuActivity.this, Settings.class);
        startActivity(intent);
    }
}