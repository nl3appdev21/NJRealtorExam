package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private Button BtnTakeTest;
    private ImageButton IbtnCopyRight;
    private TextView TvMontra;
    private Button BtnTestInstuctions;
    private Button BtnFlashCards;
    private ImageView IvDirections;
    private ImageView IvImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        BtnFlashCards = findViewById(R.id.btn_flashcards);
        BtnFlashCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFlashCards();
            }
        });

        BtnTakeTest = findViewById(R.id.btn_takeTest);
        BtnTakeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTest();
            }
        });

        // IvImage = findViewById(R.id.iv_testImage);
        //  IvDirections = findViewById(R.id.iv_directions);
        BtnTestInstuctions = findViewById(R.id.btn_testInstructions);
        BtnTestInstuctions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInstructions();
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

         /*
        IvImage.setVisibility(View.GONE);
        IvDirections.setVisibility(View.VISIBLE);
        BtnTestInstuctions.setVisibility(View.GONE);
        TvMontra.setVisibility(View.GONE);
        IbtnCopyRight.setVisibility(View.GONE);
         */

    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }

    private void showCopyRight() {

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

}