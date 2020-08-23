package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Settings extends AppCompatActivity {

    private ImageButton IbtnCopyRight;
    List<CategoryItem> switches = new ArrayList<>();
    Set<String> selectedCat = new HashSet<>();

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

            switchesSetup();
        }

    @Override
    protected void onPause() {
        super.onPause();
        QuestionManager.getInstance().loadAllQuestion(this,selectedCat);
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

    private void switchesSetup() {
        switches.add(new CategoryItem("newtype",(Switch)findViewById(R.id.newTypeSwitch)));
        switches.add(new CategoryItem("mortgage",(Switch)findViewById(R.id.mortgageSwitch)));
        switches.add(new CategoryItem("law",(Switch)findViewById(R.id.lawSwitch)));
        switches.add(new CategoryItem("commission",(Switch)findViewById(R.id.mathSwitch)));
        switches.add(new CategoryItem("advirtising",(Switch)findViewById(R.id.advirtisingSwitch)));
        switches.add(new CategoryItem("ownership",(Switch)findViewById(R.id.ownershipSwitch)));

        for(int c=0; c<switches.size(); c++){
            final int index = c;
            switches.get(c).uiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    updateSelection(index,isChecked);
                }
            });
        }
    }

    private void updateSelection(int qIndex, boolean isSelected){
        if(isSelected) {
            selectedCat.add(switches.get(qIndex).name);
        }else{
            selectedCat.remove(switches.get(qIndex).name);
        }
    }

    private class CategoryItem{
            private Switch uiSwitch;
            private String name;

        public CategoryItem(String name, Switch uiSwitch) {
            this.uiSwitch = uiSwitch;
            this.name = name;
        }
    }
}