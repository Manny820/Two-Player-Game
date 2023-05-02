package com.example.twoplayergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.VideoView;

public class MenuActivity extends AppCompatActivity {
    VideoView videoView;
    Button startButton;
    ImageButton settingsButton, scoreboardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // initializing buttons
        startButton = findViewById(R.id.start);
        settingsButton = findViewById(R.id.settings);
        scoreboardButton = findViewById(R.id.score);


        // this section of code initializes the background video
        // to loop infinitely
        //-----------------------------------------------------------------------
        videoView = findViewById(R.id.background);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.background_menu_2);
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
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchGameActivity(view);
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { /*To be added */ }
        });
        scoreboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { launchScoreBoardActivity(view); }
        });
    }


    // methods that send user to specified activity
    //-----------------------------------------------------------------------
    public void launchGameActivity(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
    public void launchScoreBoardActivity(View view) {
        Intent intent = new Intent(this, ScoreboardActivity.class);
        startActivity(intent);
    }
}