package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
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

    //TODO : new class for real estate resources 1/10/21

    // TODO - note visability is set to gone  **********
    // TODO - note visability is set to gone  **********

    public class Resources extends AppCompatActivity {  //  ?????????????????????

        private Button btnAreaMaps;
        private Button btnBoardOffices;
        private Button btnTestingSites;
        private Button btnTrend;
        private Button btnSchoolReports;
        private Button btnMortgages;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_resources);

            btnAreaMaps = findViewById(R.id.btn_areaMaps);
            btnAreaMaps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAreaMaps();  //  ?????????????????????
                }
            });

            btnBoardOffices = findViewById(R.id.btn_boardOffices);
            btnBoardOffices.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showtBoardOffices();
                }
            });

            btnTestingSites = findViewById(R.id.btn_testingSites);
            btnTestingSites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showTestingSites();
                }
            });

            btnTrend = findViewById(R.id.btn_trend);
            btnTrend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showTrend();
                }
            });

            btnSchoolReports = findViewById(R.id.btn_schoolReports);
            btnSchoolReports.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSchoolReports();
                }
            });

            btnMortgages = findViewById(R.id.btn_mortgages);
            btnMortgages.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showMortgages();
                }
            });

        }

        private void showAreaMaps() {
            Intent intent = new Intent( Resources.this, AreaMaps.class);
            startActivity(intent);  //  ?????????????????????
        }

        private void showtBoardOffices() {
            Intent intent = new Intent( Resources.this, BoardOffices.class);
            startActivity(intent);
        }

        private void showTestingSites() {
            Intent intent = new Intent( Resources.this, TestingSites.class);
            startActivity(intent);
        }

        private void showTrend() {
            Intent intent = new Intent( Resources.this, Trend.class);
            startActivity(intent);
        }

        private void showSchoolReports() {
            Intent intent = new Intent( Resources.this, SchoolReports.class);
            startActivity(intent);
        }

        private void showMortgages() {
            Intent intent = new Intent( Resources.this, Mortgages.class);
            startActivity(intent);
        }
    }
