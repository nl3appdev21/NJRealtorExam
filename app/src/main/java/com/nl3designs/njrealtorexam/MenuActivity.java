package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
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

public class MenuActivity extends AppCompatActivity {

    private Button btnTakeTest;
    private ImageButton ibtnCopyRight;
    private TextView TvMontra;
    private Button btnTestInstuctions;
    private Button btnFlashCards;
    private Button btnSettings;
    private Button btnLb;
    private ImageView ivDirections;
    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        QuestionManager questionManager = QuestionManager.getInstance();
        questionManager.reset();  // resets question index


        // ***********
        // TODO : code to change menu inage based on os and screen size !!!

        ivImage = findViewById(R.id.iv_testImage);
        Point size = new Point();
        WindowManager w = getWindowManager();
        int sc = 0; 
        int sw = 0;
        int sh = 0;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            w.getDefaultDisplay().getSize(size);
            Display d = w.getDefaultDisplay();
            sw = size.x;
            sh = size.y;
            sc = sw * sh;
            Log.d("skip", " x * y is: " + sc);
            Log.d("skip", "size is: " + size);
            Log.d("skip", "size-x is: " + size.x);
            Log.d("skip", "size-y is: " + size.y);
                if(sc <= 1937525) {
                    ivImage.setImageResource(R.mipmap.nashlogo);
                    Log.d("skip", " menu screen help change max height from 225dp to 165dp");
                }
        }

        // ***********


        // TODO : change names to camel case
        btnFlashCards = findViewById(R.id.btn_flashcards);
        btnFlashCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFlashCards();
            }
        });

        btnSettings = findViewById(R.id.btn_settings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSettings();
            }
        });

        btnTakeTest = findViewById(R.id.btn_takeTest);
        btnTakeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTest();
            }
        });

        btnTestInstuctions = findViewById(R.id.btn_testInstructions);
        btnTestInstuctions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInstructions();
            }
        });

        btnLb = findViewById(R.id.btn_lb);
        btnLb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLb();
            }
        });

        ibtnCopyRight = findViewById(R.id.ibtn_logo);
        TvMontra = findViewById(R.id.tv_montra);
        ibtnCopyRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCopyRight();
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


    private void showInstructions() {
        Intent intent = new Intent(MenuActivity.this, Instructions.class);
        startActivity(intent);
    }

    private void showLb() {
        Intent intent = new Intent(MenuActivity.this, LeaderBoard_Bkup.class);
        startActivity(intent);
    }

    private void showCopyRight() {
        Intent intent = new Intent(MenuActivity.this, CopyRight.class);
        startActivity(intent);
    }

    private void startTest() {
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void startFlashCards() {
        Intent intent = new Intent(MenuActivity.this, FlashCards.class);
        startActivity(intent);
    }

    private void showSettings() {
        Intent intent = new Intent(MenuActivity.this, Settings.class);
        startActivity(intent);
    }
}