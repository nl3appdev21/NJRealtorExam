package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoard_Bkup extends AppCompatActivity{

        private ListView results;
        private DataAdapter adapter;
        private Button btn_menu;
        private ListView listView;

        private List<TestScoreData> myData = new ArrayList<TestScoreData>();
        private StorageManager store;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.leaderboard_bkup);

            store = new StorageManager(this);
            fetchScore();

            btn_menu = findViewById(R.id.menu);
            btn_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoMenu();
                }
            });

        }

        private void fetchScore(){
            store.load("leaderboard", new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()) {
                        DocumentSnapshot snapshot = task.getResult();
                        Object obj = snapshot.get("scores");
                        if(obj != null) {
                            getScore(snapshot.get("scores").toString());
                        }
                    }else{
                        getScore("");
                    }
                }
            });
        }

        private void getScore(String leaderBoardData){
            results = findViewById(R.id.results);

        listView = findViewById(R.id.results);

        if (!leaderBoardData.equals("")) {

            //TODO: code to see leaderboarddata if app stops running
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

            //TODO: code to see result if app stops running
            // Log.d("skip", " result = " + result);

            String[] resultData = result.split(",");
            myData.add(new TestScoreData(resultData[0],
                    Integer.parseInt(resultData[1]),
                    Integer.parseInt(resultData[2]),
                    Integer.parseInt(resultData[3])));
        }
}