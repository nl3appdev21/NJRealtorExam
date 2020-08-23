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

import java.util.ArrayList;
import java.util.List;

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

            //  randswitch
            Switch sw = findViewById(R.id.randSwitch);
            sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    store.save(String.valueOf(isChecked),"flashRand");
                }
            });

            boolean swState = Boolean.parseBoolean(store.load("flashRand"));
            sw.setChecked(swState);

            //  nextcardswitch
            Switch sw1 = findViewById(R.id.nextCardSwitch);
            sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    store.save(String.valueOf(isChecked),"nextCard");
                }
            });

            boolean sw1State = Boolean.parseBoolean(store.load("nextCard"));
            sw1.setChecked(sw1State);
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

        //TODO: code to load array for catagory switches for settings screen

    private void test() {
        List<Switch> switches = new ArrayList<>();
        switches.add((Switch)findViewById(R.id.newTypeSwitch));
        switches.add((Switch)findViewById(R.id.mortgageSwitch));
        switches.add((Switch)findViewById(R.id.lawSwitch));
        switches.add((Switch)findViewById(R.id.mathSwitch));
        switches.add((Switch)findViewById(R.id.advirtisingSwitch));
        switches.add((Switch)findViewById(R.id.ownershipSwitch));

        for(int c=0; c<switches.size(); c++){
            final int index = c;
            switches.get(c).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    loadNewQuestions(index,isChecked);
                }
            });
        }
    }

    private void loadNewQuestions(int qIndex, boolean notAll){

    }
}
