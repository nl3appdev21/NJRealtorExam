package com.nl3designs.njrealtorexam;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

public class TestComplete extends AppCompatActivity {

    int tries;
    int correct;
    int numQuestions;
    int testScore;
    double numOfQuestions;
    private Button btnlb;
    ImageView iv_passFail;
    TextView tv_testResults1;
    TextView tv_testResults2;
    EditText agent;
    String agentName = "Doc Dex";
    StorageManager store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcomplete);
        store = new StorageManager(this);
        agent = findViewById(R.id.et_agentName);
        btnlb = findViewById(R.id.btn_lb);
        iv_passFail = findViewById(R.id.iv_passFail);
        tv_testResults1 = findViewById(R.id.tv_testResults1);
        tv_testResults2 = findViewById(R.id.tv_testResults2);
        Intent intent = getIntent();
        tries = getIntent().getIntExtra("tries",0);
        correct = getIntent().getIntExtra("correct",0);
        numQuestions = getIntent().getIntExtra("numQuestions",0);
        numOfQuestions = numQuestions;

        getScore();
        testComplete();

        Button btnm = findViewById(R.id.btn_menu);
        btnm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMenu();
            }
        });

        //setContentView(R.layout.inputname);
        agent = findViewById(R.id.et_agentName);

        Button btnlb = findViewById(R.id.btn_lb);
        btnlb.setOnClickListener(new View.OnClickListener() {  //  ?????????? move all eot code to lb onclick //
            @Override
            public void onClick(View v) {
                saveScore();
                //  showLb();
            }
        });
    }

    private void getScore() {

        testScore = (int)(((correct/numOfQuestions)*100));  // cast testscore to int
    }

    private void testComplete(){

        if(testScore < 70) {
            btnlb.setVisibility(View.INVISIBLE);
            agent.setVisibility(View.GONE);
            iv_passFail.setImageResource(R.mipmap.redquitbtn);
            tv_testResults1.setText(" sorry you got " + correct + " of " + numQuestions + " correct ");
            tv_testResults2.setText(" your score is: " + testScore + "%" + " , retake test");
            tv_testResults1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Red_08));
            tv_testResults2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Red_08));
        }else{
            agent.setVisibility(View.VISIBLE);
            iv_passFail.setImageResource(R.mipmap.bluecert);
            tv_testResults1.setText(" Congrats you got " + correct + " of " + numQuestions + " correct ");
            tv_testResults2.setText(" Your passing score is: " + testScore + "%");
            tv_testResults1.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.Black));
            tv_testResults2.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.Black));
            tv_testResults1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Yellow_08));
            tv_testResults2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Yellow_08));
        }
    }

    private void saveScore(){  // TODO : savescore and reload lb

        store.load("leaderboard", new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String leaderBoardData = "";
                if(task.isSuccessful()) {
                    DocumentSnapshot snapshot = task.getResult();
                    Object obj = snapshot.get("scores");
                    if(obj != null) {
                        leaderBoardData = snapshot.get("scores").toString();
                    }
                }
                agentName = agent.getText().toString();

                //   String score = "\n" + agentName + " got " + correct + " of " + tries + " correct for " + testScore + " %" + "\n";
                String score = "\n" + agentName + " got " + correct + " of " + tries + " correct for " + testScore + " %" + "\n";
                score = ";" + agentName + ","  + correct + "," + tries + "," + testScore + ",";

                leaderBoardData += score;
                store.save(leaderBoardData, "leaderboard", new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        showLb();
                    }
                });

                // add code for oncomplete
            }
        });

    }


    private void saveScoreOldCode(){

        String leaderBoardData = store.load("leaderboard");
        agentName = agent.getText().toString();

        //   String score = "\n" + agentName + " got " + correct + " of " + tries + " correct for " + testScore + " %" + "\n";
        String score = "\n" + agentName + " got " + correct + " of " + tries + " correct for " + testScore + " %" + "\n";
        score = ";" + agentName + ","  + correct + "," + tries + "," + testScore + ",";

        leaderBoardData+= score;
        store.save(leaderBoardData,"leaderboard");

    }

    private void takeTest(){  //  ????  deleted button  ????

        Intent intent = new Intent(TestComplete.this, MainActivity.class);
        startActivity(intent);
    }

    private void showLb(){

        Intent intent = new Intent(TestComplete.this, LeaderBoard_Bkup.class);
        //  ????????  Intent intent = new Intent(TestComplete.this, LeaderBoard.class);
        startActivity(intent);
    }

    private void gotoMenu(){
        Intent intent = new Intent(TestComplete.this, MenuActivity.class);
        startActivity(intent);
    }
}