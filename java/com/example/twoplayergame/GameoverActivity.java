package com.example.twoplayergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class GameoverActivity extends AppCompatActivity {

    VideoView videoView;
    Button rematchBtn, menuBtn, scoresBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        // initializing buttons
        rematchBtn = findViewById(R.id.rematch);
        menuBtn = findViewById(R.id.menu);
        scoresBtn = findViewById(R.id.scores);


        // this section of code initializes the background video
        // to loop infinitely
        //-----------------------------------------------------------------------
        videoView = findViewById(R.id.background);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.arena_4);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });


        // OnClickListeners for each button to launch specified
        // activity and send user there
        //-----------------------------------------------------------------------
        rematchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchGameActivity(view);
            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMenuActivity(view);
            }
        });
        scoresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchScoreBoardActivity(view);
            }
        });
    }


    // methods that send user to specified activity
    //-----------------------------------------------------------------------
    public void launchMenuActivity(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
    public void launchGameActivity(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
    public void launchScoreBoardActivity(View view) {
        Intent intent = new Intent(this, ScoreboardActivity.class);
        startActivity(intent);
    }
}