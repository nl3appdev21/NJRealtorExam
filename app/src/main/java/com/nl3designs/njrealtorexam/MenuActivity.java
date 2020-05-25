package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private Button BtnTakeTest;
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
    }

    private void showDirections() {

        Log.d("skip", "test test test");
        IvImage.setVisibility(View.GONE);
        TvDirections.setVisibility(View.VISIBLE);
        BtnTestInstuctions.setVisibility(View.GONE);

        String dir1 = " d11 ";
        String dir2 = " d22 ";
        String dir3 = " d33 ";
        String dir4 = " d44 ";
        String dir5 = " d55 ";

        TvDirections.setTextColor(Color.MAGENTA);
        //  TvDirections.setText("this is a journey into /n sound, pump up the volume !");
        TvDirections.setText( dir1 + " " + dir2 + " " + dir3 + " " + dir4 + " " + dir5 + " ! " );
    }

    private void startTest() {

        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(intent);
    }
}