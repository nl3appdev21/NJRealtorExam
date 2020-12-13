package com.nl3designs.njrealtorexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TestCompleteActivity extends AppCompatActivity {

    TextView tv_tries;
    TextView tv_correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcomplete);
        tv_tries = findViewById(R.id.tries);
        tv_correct = findViewById(R.id.correct);
        int tries = getIntent().getIntExtra("tries",0);
        int correct = getIntent().getIntExtra("correct", 0);
        tv_tries.setText("  # of tries = " + String.valueOf(tries));
        tv_correct.setText(" # correct = " + String.valueOf(correct));
    }
}