package com.example.twoplayergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.VideoView;

import java.util.ArrayList;

public class ScoreboardActivity extends AppCompatActivity {

    VideoView videoView;
    Button resetBtn, menuBtn;
    ArrayList<Statistics> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        // initializing buttons
        resetBtn = findViewById(R.id.reset);
        menuBtn = findViewById(R.id.menu);


        // this section of code initializes the background video
        // to loop infinitely
        //-----------------------------------------------------------------------
        videoView = findViewById(R.id.background);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.arena_1);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });


        // this section of code initializes listview so that
        // the statistics stored within arrayList are shown
        // (hardcoded members added for testing purposes)
        //-----------------------------------------------------------------------
        ListView listView = findViewById(R.id.listView);
        arrayList = PrefConfig.read(this);
        if (arrayList == null)
            arrayList = new ArrayList<>();
        arrayList.add(new Statistics("2","1","1"));
        arrayList.add(new Statistics("3","2","1"));
        arrayList.add(new Statistics("4","2","2"));
        arrayList.add(new Statistics("5","3","2"));


        // this code converts all elements within arrayList
        // into items to be shown within the listview
        ScoreboardListAdapter scoreboardListAdapter = new ScoreboardListAdapter(this,R.layout.list_statistics,arrayList);
        listView.setAdapter(scoreboardListAdapter);



        // OnClickListeners for each button to launch specified
        // activity and send user there
        //-----------------------------------------------------------------------
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.removeAll(arrayList);
                PrefConfig.write(getApplicationContext(),arrayList);
                launchScoreBoardActivity(view);
            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { launchMenuActivity(view); }
        });
    }


    // methods that send user to specified activity
    //-----------------------------------------------------------------------
    public void launchMenuActivity(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
    public void launchScoreBoardActivity(View view) {
        Intent intent = new Intent(this, ScoreboardActivity.class);
        startActivity(intent);
    }
}