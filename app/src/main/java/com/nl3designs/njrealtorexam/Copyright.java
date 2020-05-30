package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CopyRight extends AppCompatActivity {

    private Button BtnBack2Menu;
    // private TextView TvCredits;
    // private TextView TvCopyRight;
    // private ImageView IvCopyRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.copyright);

        BtnBack2Menu = findViewById(R.id.btn_back2Menu);
        // TvCredits = findViewById(R.id.tv_credits);
        // TvCopyRight = findViewById(R.id.tv_copyright);
        // IvCopyRight = findViewById(R.id.iv_copyright);

        BtnBack2Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back2Menu();
            }
        });
    }

    private void back2Menu() {

        Intent intent = new Intent(CopyRight.this, MenuActivity.class);
        startActivity(intent);
    }
}