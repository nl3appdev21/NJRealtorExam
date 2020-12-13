package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CopyRight extends AppCompatActivity {
    private TextView tvName;
    private Button btnBack2Menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.copyright);
        btnBack2Menu = findViewById(R.id.btn_back2Menu);
        btnBack2Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back2Menu();
            }
        });
        tvName = findViewById(R.id.tv_name);
        tvName.setText("Version Name: " + BuildConfig.VERSION_NAME);

    }

    private void back2Menu() {
        Intent intent = new Intent(CopyRight.this, MenuActivity.class);
        startActivity(intent);
    }
}