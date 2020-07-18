package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InputName extends AppCompatActivity {

    EditText agent;
    String agentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputname);
        agent = findViewById(R.id.et_agentName);
        Button btn = findViewById(R.id.btn_leaderBoard);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                agentName = agent.getText().toString();
                Log.d("skip", " agent name is :  " + agent.getText().toString());
                gotoMenu();
            }
        });
    }

    private void gotoMenu(){
        Intent intent = new Intent(InputName.this, MenuActivity.class);
        startActivity(intent);
    }

}
