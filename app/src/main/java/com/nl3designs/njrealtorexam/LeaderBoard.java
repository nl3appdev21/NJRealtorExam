package com.nl3designs.njrealtorexam;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LeaderBoard extends AppCompatActivity {

    TextView tv_userResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        tv_userResults = findViewById(R.id.userResults);
        String results = getIntent().getStringExtra("results");
        tv_userResults.setText(String.valueOf(results));

    }
}