package com.nl3designs.njrealtorexam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LeaderBoard extends AppCompatActivity {

    TextView tv_userResults;
    private Button btn_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        tv_userResults = findViewById(R.id.userResults);
        String results = getIntent().getStringExtra("results");
        tv_userResults.setText(String.valueOf(results));
        btn_menu = findViewById(R.id.menu);

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMenu();
            }
        });

    }

    private void gotoMenu() {
        Intent intent = new Intent(LeaderBoard.this, MenuActivity.class);
        startActivity(intent);
    }

}