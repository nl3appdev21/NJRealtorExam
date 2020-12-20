package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Settings extends AppCompatActivity {

    private ImageButton ibtnCopyRight;
    List<CategoryItem> switches = new ArrayList<>();
    Set<String> selectedCat = new HashSet<>();
    StorageManager store;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.settings);
            store = new StorageManager(Settings.this);

            // TODO: delete this code  lines 30 to 51

            /*
            // new test code  ????
            HashMap<String, String> myMap = new HashMap<>();
            myMap.put("emp1", "skip");
            myMap.put("emp2", "nash");
            myMap.put("emp3", "dex");
            myMap.put("emp4", "fred");
            myMap.put("emp5", "dean");
            myMap.put("emp6", "bob");

            myMap.put("emp4", "skip");  //  will over write emp4 from fred to skip
            myMap.remove("emp4");

            for(String value : myMap.values()){
                Log.d("skip", value);
            }
            // new test code  ????

            */

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

            //TODO convert to method
            String selected = store.load("customCards");
            for(CategoryItem ci : switches){
                if(selected.contains(ci.name)){
                    ci.uiSwitch.setChecked(true);
                }
            }

        }

    @Override
    protected void onPause() {
        super.onPause();
        QuestionManager.getInstance().loadAllQuestion(this,selectedCat);

        //TODO convert to method
        /*
        1 - newtype - is a sudo category for unknown type
        2 - mortgage
        3 - law
        4 - commission
        5 - advirtising
        6 - ownership
         */

        String finalCatString = "";
        int c = 0;
        for(String name  : selectedCat) {
            if (c == 0) {
                finalCatString += name;
            } else {
                finalCatString += "," + name;
            }
            c+=1;
        }

        store.save(finalCatString, "customCards");
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

        //TODO: code to load array for category switches for settings screen

    private void switchesSetup() {
        switches.add(new CategoryItem("newtype",(Switch)findViewById(R.id.newTypeSwitch)));
        switches.add(new CategoryItem("mortgage",(Switch)findViewById(R.id.mortgageSwitch)));
        switches.add(new CategoryItem("law",(Switch)findViewById(R.id.lawSwitch)));
        switches.add(new CategoryItem("commission",(Switch)findViewById(R.id.commissionSwitch)));
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