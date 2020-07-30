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
        setContentView(R.layout.activity_leaderboard);

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
        results.setText(leaderBoardData);

    }

    private void gotoMenu() {
        Intent intent = new Intent(LeaderBoard.this, MenuActivity.class);
        startActivity(intent);
    }

    private void setupData(String result) {

        String[] resultData = result.split(";");
        myData.add(new TestScoreData(resultData[0],
                Integer.parseInt(resultData[1]),
                Integer.parseInt(resultData[2]),
                Integer.parseInt(resultData[3])));
    }
}