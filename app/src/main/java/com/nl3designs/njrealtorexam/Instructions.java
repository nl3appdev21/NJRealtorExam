package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Instructions extends AppCompatActivity {

    private Button btnBack2Menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testinstructions);
        btnBack2Menu = findViewById(R.id.btn_back2Menu);
        btnBack2Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back2Menu();
            }
        });
    }

    private void back2Menu() {

        Intent intent = new Intent(com.nl3designs.njrealtorexam.Instructions.this, MenuActivity.class);
        startActivity(intent);
    }
}