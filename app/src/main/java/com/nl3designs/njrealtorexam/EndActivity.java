package com.nl3designs.njrealtorexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        tv_result = findViewById(R.id.result);

        int correct = getIntent().getIntExtra("correct", 0);
        //  ??  int correct = getIntent().getStringExtra("correct", 0);
        int wrong = getIntent().getIntExtra("wrong", 0);
        //  ??  int  wrong = getIntent().getStringExtra("wrong", 0);

        tv_result.setText("Correct : " + correct + "\nWrong: " + wrong);
    }
}
