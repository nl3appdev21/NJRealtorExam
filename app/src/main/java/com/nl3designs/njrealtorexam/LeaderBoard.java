package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class LeaderBoard extends AppCompatActivity {

    ListView lv_userResults;
    private Button btn_menu;
    ListView listView;
    List<TestScoreData> myData = new ArrayList<TestScoreData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

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

        //  lv_userResults.setText(String.valueOf(results));  ?? keep
        btn_menu = findViewById(R.id.menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMenu();
            }
        });
    }

    private void gotoMenu() {
        Intent intent = new Intent(LeaderBoard.this, MenuActivity.class);
        startActivity(intent);
    }

    private void setupData(String result) {
        String[] resultData = result.split(";");
        myData.add(new TestScoreData(resultData[0], Integer.parseInt(resultData[1]), Integer.parseInt(resultData[2]), Integer.parseInt(resultData[3])));
    }
}