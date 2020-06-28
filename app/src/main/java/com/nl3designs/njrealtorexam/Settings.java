package com.nl3designs.njrealtorexam;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Settings extends AppCompatActivity {

        private Button BtnTakeTest;
        private ImageButton IbtnCopyRight;
        private TextView TvMontra;
        private Button BtnTestInstuctions;
        private Button BtnFlashCards;
        private Button BtnMenu;
        private Button BntLeaderboard;
        private ImageView IvDirections;
        private ImageView IvImage;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.settings);  //  ??  ******************************************

            BtnFlashCards = findViewById(R.id.btn_flashcards);
            BtnFlashCards.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startFlashCards();
                }
            });

            BtnTakeTest = findViewById(R.id.btn_takeTest);
            BtnTakeTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startTest();
                }
            });

            BtnMenu = findViewById(R.id.btn_menu);
            BtnMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startMenu();
                }
            });

            BntLeaderboard = findViewById(R.id.btn_leaderBoard);
            BntLeaderboard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showLeaderboard();
                }
            });

            // IvImage = findViewById(R.id.iv_testImage);
            //  IvDirections = findViewById(R.id.iv_directions);
            BtnTestInstuctions = findViewById(R.id.btn_testInstructions);
            BtnTestInstuctions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showInstructions();
                }
            });

            IbtnCopyRight = findViewById(R.id.ibtn_logo);
            TvMontra = findViewById(R.id.tv_montra);
            IbtnCopyRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCopyRight();
                }
            });

        }

        private void showInstructions() {

            Intent intent = new Intent(com.nl3designs.njrealtorexam.Settings.this, Instructions.class);
            startActivity(intent);

         /*
        IvImage.setVisibility(View.GONE);
        IvDirections.setVisibility(View.VISIBLE);
        BtnTestInstuctions.setVisibility(View.GONE);
        TvMontra.setVisibility(View.GONE);
        IbtnCopyRight.setVisibility(View.GONE);
         */

        }

        @Override
        public void onBackPressed() {

            startActivity(new Intent(this, com.nl3designs.njrealtorexam.Settings.class));
            finish();
        }

        private void startMenu() {

            Intent intent = new Intent(com.nl3designs.njrealtorexam.Settings.this, MenuActivity.class);
            startActivity(intent);
        }

        private void showLeaderboard() {

            Intent intent = new Intent(com.nl3designs.njrealtorexam.Settings.this, LeaderBoard.class);
            startActivity(intent);
        }

        private void startTest() {

            Intent intent = new Intent(com.nl3designs.njrealtorexam.Settings.this, MainActivity.class);
            startActivity(intent);
        }

        private void startFlashCards() {

            Intent intent = new Intent(com.nl3designs.njrealtorexam.Settings.this, FlashCards.class);
            startActivity(intent);
        }

        private void showCopyRight() {

            Intent intent = new Intent(com.nl3designs.njrealtorexam.Settings.this, CopyRight.class);
            startActivity(intent);
        }

}
