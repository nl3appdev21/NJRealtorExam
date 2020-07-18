package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class LeaderBoard extends AppCompatActivity {

    ListView lv_userResults;
    private Button btn_menu;
    ListView listView;
    TextView results;
    List<TestScoreData> myData = new ArrayList<TestScoreData>();
    StorageManager store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  ??  setContentView(R.layout.list_item);
        //  setContentView(R.layout.leaderboard_bkup);
        setContentView(R.layout.activity_leaderboard);
        //  Log.d("skip", "lb");

        //  ???
        store = new StorageManager(this);
        getScore();

        /*
        // new lb code
        listView = findViewById(R.id.userResults);
        DataAdapter adapter = new DataAdapter(this,myData);
        listView.setAdapter(adapter); // loads items into adapter

        lv_userResults = findViewById(R.id.userResults);
        String result = getIntent().getStringExtra("results");

        if (result != null) {
            String[] results = result.split(",");
            for (int l = 0; l < results.length; l++) {
                setupData(results[l]);
            }
        }




        private void saveScore(){
        String leaderBoardData = store.load("leaderboard");
        //String score = ";" + tries + "," + correct + "," + testScore;
        String score = "\n" + "right - " + correct + " of - " + tries + " score - " + testScore;
        // System.out.println("your score is  = " + score);
        leaderBoardData+= score;
        store.save(leaderBoardData,"leaderboard");
        showLb();
    }




        */


        //  lv_userResults.setText(String.valueOf(results));  ?? keep
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
        Log.d("skip", "store 33 = " + leaderBoardData);
        results = findViewById(R.id.results);
        results.setText(leaderBoardData);
        //  String[] session = leaderBoardData.split(",");
        //  String session = "," + tries + "," + correct + "," + testScore;

    }

    private void gotoMenu() {
        Intent intent = new Intent(LeaderBoard.this, MenuActivity.class);
        startActivity(intent);
    }

    private void setupData(String result) {
        String[] resultData = result.split(";");
        myData.add(new TestScoreData (Integer.parseInt(resultData[0]),
                                      Integer.parseInt(resultData[1]),
                                      Integer.parseInt(resultData[2]),
                                      Integer.parseInt(resultData[3])));
    }
}