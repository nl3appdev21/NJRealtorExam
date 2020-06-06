package com.nl3designs.njrealtorexam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        btn = findViewById(R.id.btn_menu);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMenu();
            }
        });
    }

    private void gotoMenu() {
        finish();
    }
}