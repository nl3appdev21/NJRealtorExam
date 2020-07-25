package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoard_Bkup extends AppCompatActivity{

        ListView results;
        DataAdapter adapter;
        private Button btn_menu;
        ListView listView;

        List<TestScoreData> myData = new ArrayList<TestScoreData>();
        StorageManager store;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.leaderboard_bkup);
            Log.d("skip", "lb 201");

            store = new StorageManager(this);
            getScore();

            btn_menu = findViewById(R.id.menu);
            btn_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoMenu();
                }
            });

        }

        private void getScore(){
            String leaderBoardData = store.load("leaderboard");
            Log.d("skip", "lb 202  getscore" + leaderBoardData);
            results = findViewById(R.id.results);

        listView = findViewById(R.id.results);

        if (leaderBoardData != null) {
            String[] entries = leaderBoardData.split(";");
            for (int l = 0; l < entries.length; l++) {
                if (!entries[l].equals("")) {  // to check vale of a string, use this code
                    setupData(entries[l]);
                }
            }
            adapter = new DataAdapter(this,myData);
            listView.setAdapter(adapter); // loads items into adapter
        }

            String[] session = leaderBoardData.split(",");
        }

        private void gotoMenu() {
            Log.d("skip", "lb 203");

            Intent intent = new Intent(com.nl3designs.njrealtorexam.LeaderBoard_Bkup.this, MenuActivity.class);
            startActivity(intent);
        }

        private void setupData(String result) {
            Log.d("skip", "lb 204");

            String[] resultData = result.split(",");
            myData.add(new TestScoreData(resultData[0],
                    Integer.parseInt(resultData[1]),
                    Integer.parseInt(resultData[2]),
                    Integer.parseInt(resultData[3])));
        }
}