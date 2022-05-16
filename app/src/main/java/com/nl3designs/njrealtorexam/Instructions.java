package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class Instructions extends AppCompatActivity {

    private Button btnTakeTest;
    private ImageButton ibtnCopyRight;
    private TextView TvMontra;
    private Button btnTestInstuctions;
    private Button btnFlashCards;
    private Button btnSettings;
    private Button btnLb;
    private Button btnResources;
    private ImageView ivDirections;
    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testinstructions);
        QuestionManager questionManager = QuestionManager.getInstance();
        questionManager.reset();  // resets question index

        // ***********
        // TODO : code to change menu inage based on os and screen size !!!

        ivImage = findViewById(R.id.iv_testImage);
        Utils.resizeImageHeight(ivImage,this);

        // ***********

        // TODO : change names to camel case
        btnFlashCards = findViewById(R.id.btn_flashcards);
        btnFlashCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFlashcard("flashcardvideo3", "Flashcard Video");
            }
        });

        btnSettings = findViewById(R.id.btn_settings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFlashcard("settingsvideo5", "Settings Video");
            }
        });

        btnTakeTest = findViewById(R.id.btn_takeTest);
        btnTakeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFlashcard("splashvideo1", "Take Test Not Made Video");
            }
        });

        btnLb = findViewById(R.id.btn_leaderboard);
        btnLb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFlashcard("lbvideo4", "Leaderboard Video");
            }
        });

        btnTakeTest = findViewById(R.id.btn_devInfo);
        btnTakeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFlashcard("devinfovideo6", "Dev Info Video");
            }
        });

        Button btn = findViewById(R.id.btn_back2Menu);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // TODO : firebase test code
        //asynchronously retrieve multiple documents

        CollectionReference boardRef = FirebaseFirestore.getInstance().collection("leaderboard");
        boardRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<LeaderboardItem> scores = new ArrayList<>();
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        scores.add(document.toObject(LeaderboardItem.class));
                    }
                }
                int f= 6;
            }
        });
    }

    private void showFlashcard(String s, String s2) {
        Intent intent = new Intent(Instructions.this, VideoScreen.class);
        intent.putExtra("video", s);
        intent.putExtra("title", s2);
        startActivity(intent);
    }

}