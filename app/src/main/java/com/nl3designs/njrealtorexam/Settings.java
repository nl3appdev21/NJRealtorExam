package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Settings extends AppCompatActivity {

    // save cat items in array list and saving space for store mgt
    List<CategoryItem> switches = new ArrayList<>();
    Set<String> selectedCat = new HashSet<>();
    StorageManager store;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.newsettings);
            store = new StorageManager(Settings.this);

            switchesSetup();

            //TODO convert to method
            String selected = store.load("customCards");
            for(CategoryItem ci : switches){
                if(selected.contains(ci.name)){
                    ci.setChecked(true);
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
        5 - advertising
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

        switches.add(new CategoryItem("randomize",(LinearLayout)findViewById(R.id.btn_flashcard)));

        switches.add(new CategoryItem("advertising",(LinearLayout)findViewById(R.id.btn_advertising)));
        switches.add(new CategoryItem("commission",(LinearLayout)findViewById(R.id.btn_commission)));
        switches.add(new CategoryItem("law",(LinearLayout)findViewById(R.id.btn_law)));
        switches.add(new CategoryItem("mortgage",(LinearLayout)findViewById(R.id.btn_math)));
        switches.add(new CategoryItem("newtype",(LinearLayout)findViewById(R.id.btn_newtype)));
        switches.add(new CategoryItem("ownership",(LinearLayout)findViewById(R.id.btn_ownership)));

        for(int c=0; c<switches.size(); c++){
            final int index = c;
            switches.get(c).uiBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View buttonView) {
                    updateSelection(index);
                }
            });
        }
    }

    private void updateSelection(int qIndex){
        CategoryItem selectedCategory = switches.get(qIndex);
        boolean isSelected = selectedCategory.isSelected;
        isSelected = !isSelected;

        if(isSelected) {
            selectedCat.add(selectedCategory.name);
        }else{
            selectedCat.remove(selectedCategory.name);
        }
        selectedCategory.setChecked(isSelected);
    }

    private class CategoryItem{
            private Boolean isSelected = false;
            private LinearLayout uiBtn;
            private String name;

        public CategoryItem(String name, LinearLayout uiSwitch) {
            this.uiBtn = uiSwitch;
            this.name = name;
        }

        public void setChecked (boolean selected){
            isSelected = selected;
            View indicator = uiBtn.getChildAt(0);
            indicator.setBackground(ResourcesCompat.getDrawable(getResources(),selected ? R.drawable.green_circle : R.drawable.empty_circle,null));
        }

    }
}