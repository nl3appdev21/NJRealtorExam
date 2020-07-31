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
            results = findViewById(R.id.results);

        listView = findViewById(R.id.results);

        if (!leaderBoardData.equals("")) {

            //TODO: code used to test empty string, clean up
            // Log.d("skip", " leaderboarddata = " + leaderBoardData);

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

            Intent intent = new Intent(com.nl3designs.njrealtorexam.LeaderBoard_Bkup.this, MenuActivity.class);
            startActivity(intent);
        }

        private void setupData(String result) {

            //TODO: code used to test empty string, clean up
            //  Log.d("skip", " result = " + result);

            String[] resultData = result.split(",");
            myData.add(new TestScoreData(resultData[0],
                    Integer.parseInt(resultData[1]),
                    Integer.parseInt(resultData[2]),
                    Integer.parseInt(resultData[3])));
        }
}