package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.graphics.Color;
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
    private TextView TvDirections;
    private ImageView IvImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        BtnTakeTest = findViewById(R.id.btn_takeTest);
        BtnTakeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTest();
            }
        });

        IvImage = findViewById(R.id.iv_testImage);
        TvDirections = findViewById(R.id.tv_directions);
        BtnTestInstuctions = findViewById(R.id.btn_testInstructions);
        BtnTestInstuctions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDirections();
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

    private void showDirections() {

        IvImage.setVisibility(View.GONE);
        TvDirections.setVisibility(View.VISIBLE);
        BtnTestInstuctions.setVisibility(View.GONE);
        TvMontra.setVisibility(View.GONE);
        IbtnCopyRight.setVisibility(View.GONE);

        TvDirections.setTextColor(Color.WHITE);
        //  TvDirections.setText("this is a journey into /n sound, pump up the volume !");
        //  TvDirections.setText( dir1 + " " + dir2 + " " + dir3 + " " + dir4 + " " + dir5 + " ! " );
    }

    private void showCopyRight() {

        Intent intent = new Intent(MenuActivity.this, CopyRight.class);
        startActivity(intent);
    }

    private void startTest() {

        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(intent);
    }
}