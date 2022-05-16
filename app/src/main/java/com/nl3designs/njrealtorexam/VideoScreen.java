package com.nl3designs.njrealtorexam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoScreen extends AppCompatActivity {

    private Button btnBack2Menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoscreen);
        btnBack2Menu = findViewById(R.id.btn_back2Menu);
        TextView tv_title = findViewById(R.id.tv_video_title);
        btnBack2Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back2Menu();
            }
        });

        Bundle bundle = getIntent().getExtras();
        String video = bundle.getString("video");
        String title = bundle.getString("title");
        tv_title.setText(title);
        int resourceId = this.getResources().getIdentifier(video, "raw", this.getPackageName());
        VideoView videoview = findViewById(R.id.video_player);
        videoview.setVideoPath("android.resource://"+getPackageName()+"/"+resourceId);
        MediaController mediaController = new MediaController( this);
        mediaController.setAnchorView(videoview);
        videoview.setMediaController(mediaController);
        // new code mon. 5-9-22
        videoview.start();
        //  ??  mediaController.show();
    }



    private void back2Menu() {
        finish();
    }
}

