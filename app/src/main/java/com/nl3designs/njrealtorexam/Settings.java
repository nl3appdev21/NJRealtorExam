package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    private ImageButton IbtnCopyRight;
    //private TextView TvMontra;
    StorageManager store;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.settings);
            store = new StorageManager(Settings.this);

            IbtnCopyRight = findViewById(R.id.ibtn_logo);
            //TvMontra = findViewById(R.id.tv_montra);
            IbtnCopyRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCopyRight();
                }
            });

            Switch sw = findViewById(R.id.randSwitch);
            sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    store.save(String.valueOf(isChecked),"flashRand");
                }
            });

            boolean swState = Boolean.parseBoolean(store.load("flashRand"));
            sw.setChecked(swState);
        }

        @Override
        public void onBackPressed() {

            startActivity(new Intent(this, MenuActivity.class));
            finish();
        }

        private void showCopyRight() {

            Intent intent = new Intent(com.nl3designs.njrealtorexam.Settings.this, CopyRight.class);
            startActivity(intent);
        }

}
