package com.example.twoplayergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity implements View.OnTouchListener {
    public VideoView videoView;
    private FrameLayout bluePlatform, redPlatform;
    public Player red, blue;
    private ImageButton blueBombBtn, redBombBtn;
    public ImageView redBombLeft, redBombMid, redBombRight,
                     blueBombLeft, blueBombMid, blueBombRight,
                     redExplodeLeft, redExplodeMid, redExplodeRight,
                     blueExplodeLeft, blueExplodeMid, blueExplodeRight,
                     timerBackground;
    public TextView  timerTitle;
    public ArrayList<Statistics> arrayList;
    private Timer timer = new Timer();
    private Handler handler = new Handler();
    private Handler handler2 = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // arrayList to hold statistic values
        arrayList = new ArrayList<>();


        // initialization of animations
        //-----------------------------------------------------------------------
        Animation bounce = AnimationUtils.loadAnimation(this,R.anim.bounce);
        Animation fade = AnimationUtils.loadAnimation(this,R.anim.fade);
        Animation pulse = AnimationUtils.loadAnimation(this,R.anim.pulse);
        Animation shake = AnimationUtils.loadAnimation(this,R.anim.shake);
        Animation fadeOut = AnimationUtils.loadAnimation(this,R.anim.fade_out);
        Animation timerFade = AnimationUtils.loadAnimation(this,R.anim.timer_fade);


        // this section of code initializes every characteristic
        // that is related to player RED such as model, bombs,
        // platform, buttons, and health
        //-----------------------------------------------------------------------
        redPlatform = findViewById(R.id.red_frame);
        red = new Player(findViewById(R.id.red_player),(findViewById(R.id.red_health)),
                         getResources().getDrawable(R.drawable.red_left),
                         getResources().getDrawable(R.drawable.red_right),
                         getResources().getDrawable(R.drawable.red_front));
        redBombBtn = findViewById(R.id.red_bomb_btn);
        findViewById(R.id.red_left_btn).setOnTouchListener(this);
        findViewById(R.id.red_right_btn).setOnTouchListener(this);
        redBombLeft = findViewById(R.id.red_bomb_left);
        redBombMid = findViewById(R.id.red_bomb_mid);
        redBombRight = findViewById(R.id.red_bomb_right);
        redExplodeLeft = findViewById(R.id.red_explode_left);
        redExplodeMid = findViewById(R.id.red_explode_mid);
        redExplodeRight = findViewById(R.id.red_explode_right);
        redBombLeft.startAnimation(pulse);
        redBombMid.startAnimation(pulse);
        redBombRight.startAnimation(pulse);


        // this section of code initializes every characteristic
        // that is related to player BLUE such as model, bombs,
        // platform, buttons, and health
        //-----------------------------------------------------------------------
        bluePlatform = findViewById(R.id.blue_frame);
        blue = new Player(findViewById(R.id.blue_player),(findViewById(R.id.blue_health)),
                          getResources().getDrawable(R.drawable.blue_left),
                          getResources().getDrawable(R.drawable.blue_right),
                          getResources().getDrawable(R.drawable.blue_front));
        blueBombBtn = findViewById(R.id.blue_bomb_btn);
        findViewById(R.id.blue_left_btn).setOnTouchListener(this);
        findViewById(R.id.blue_right_btn).setOnTouchListener(this);
        blueBombLeft = findViewById(R.id.blue_bomb_left);
        blueBombMid = findViewById(R.id.blue_bomb_mid);
        blueBombRight = findViewById(R.id.blue_bomb_right);
        blueExplodeLeft = findViewById(R.id.blue_explode_left);
        blueExplodeMid = findViewById(R.id.blue_explode_mid);
        blueExplodeRight = findViewById(R.id.blue_explode_right);
        blueBombLeft.startAnimation(pulse);
        blueBombMid.startAnimation(pulse);
        blueBombRight.startAnimation(pulse);



        // this section of code initializes the background video
        // to loop infinitely
        //-----------------------------------------------------------------------
        videoView = findViewById(R.id.background);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.arena_3);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });


        // this code creates transition between last
        // activity and this activity
        //-----------------------------------------------------------------------
        timerBackground = findViewById(R.id.timer_background);
        timerTitle = findViewById(R.id.timer_title);
        timerBackground.startAnimation(fadeOut);
        timerTitle.startAnimation(timerFade);


        // this is a timer that keeps track of
        // user button inputs and player movement
        //-----------------------------------------------------------------------
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changeBluePos();
                        changeRedPos();
                    }
                });
            }
        }, 0, 20);


        // this section of code is an OnClickListener
        // for BLUE bomb button which keeps track of
        // BLUE location in relation to RED when being
        // clicked. If conditions are met, bomb will hit
        // RED and reduce health until game is over.
        // Once game is over, scores are written into
        // arrayList and then used for scoreboard
        // (animations are played for each action made)
        //-----------------------------------------------------------------------
        blueBombBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (blue.getPlayerX() > 0 && blue.getPlayerX() < 150) {
                    if (red.getPlayerX() > 0 && red.getPlayerX() < 400) {
                        redExplodeRight.startAnimation(bounce);
                        redExplodeRight.setVisibility(view.INVISIBLE);
                        red.model.startAnimation(fade);
                        red.setHits(red.getHits()+1);
                    }
                } else if (blue.getPlayerX() > 600 && blue.getPlayerX() < 750) {
                       if (red.getPlayerX() > 400 && red.getPlayerX() < 950) {
                           redExplodeMid.startAnimation(bounce);
                           redExplodeMid.setVisibility(view.INVISIBLE);
                           red.model.startAnimation(fade);
                           red.setHits(red.getHits()+1);
                       }
                } else if (blue.getPlayerX() > 1200 && blue.getPlayerX() < 1350) {
                    if (red.getPlayerX() > 950 && red.getPlayerX() < 1500) {
                        redExplodeLeft.startAnimation(bounce);
                        redExplodeLeft.setVisibility(view.INVISIBLE);
                        red.model.startAnimation(fade);
                        red.setHits(red.getHits()+1);
                    }
                }
                if (red.getHits() == 1) {
                    red.health.startAnimation(shake);
                    red.health.setImageDrawable(getResources().getDrawable(R.drawable.red_health_2_3));
                }
                else if (red.getHits() == 2) {
                    red.health.startAnimation(shake);
                    red.health.setImageDrawable(getResources().getDrawable(R.drawable.red_health_1_3));
                }
                else if (red.getHits() == 3) {
                    red.health.startAnimation(shake);
                    red.health.setImageDrawable(getResources().getDrawable(R.drawable.red_health_0_3));
                    blue.setScore(blue.getScore()+1);

                    int games = red.getScore() + blue.getScore();
                    int redScore = red.getScore();
                    int blueScore = blue.getScore();

                    String tempA = Integer.toString(games);
                    String tempB = Integer.toString(redScore);
                    String tempC = Integer.toString(blueScore);

                    arrayList.add(new Statistics(tempA,tempB,tempC));
                    PrefConfig.write(getApplicationContext(),arrayList);
                    delay(view);
                }
            }
        });


        // this section of code is an OnClickListener
        // for RED bomb button which keeps track of
        // RED location in relation to BLUE when being
        // clicked. If conditions are met, bomb will hit
        // BLUE and reduce health until game is over.
        // Once game is over, scores are written into
        // arrayList and then used for scoreboard
        // (animations are played for each action made)
        //-----------------------------------------------------------------------
        redBombBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (red.getPlayerX() > 0 && red.getPlayerX() < 150) {
                    if (blue.getPlayerX() > 0 && blue.getPlayerX() < 400) {
                        blueExplodeLeft.startAnimation(bounce);
                        blueExplodeLeft.setVisibility(view.INVISIBLE);
                        blue.model.startAnimation(fade);
                        blue.setHits(blue.getHits() + 1);
                    }
                } else if (red.getPlayerX() > 600 && red.getPlayerX() < 750) {
                    if (blue.getPlayerX() > 400 && blue.getPlayerX() < 950) {
                        blueExplodeMid.startAnimation(bounce);
                        blueExplodeMid.setVisibility(view.INVISIBLE);
                        blue.model.startAnimation(fade);
                        blue.setHits(blue.getHits() + 1);
                    }
                } else if (red.getPlayerX() > 1200 && red.getPlayerX() < 1350) {
                    if (blue.getPlayerX() > 950 && blue.getPlayerX() < 1500) {
                        blueExplodeRight.startAnimation(bounce);
                        blueExplodeRight.setVisibility(view.INVISIBLE);
                        blue.model.startAnimation(fade);
                        blue.setHits(blue.getHits() + 1);
                    }
                }
                if (blue.getHits() == 1) {
                    blue.health.startAnimation(shake);
                    blue.health.setImageDrawable(getResources().getDrawable(R.drawable.blue_health_2_3));
                }
                else if (blue.getHits() == 2) {
                    blue.health.startAnimation(shake);
                    blue.health.setImageDrawable(getResources().getDrawable(R.drawable.blue_health_1_3));
                }
                else if (blue.getHits() == 3) {
                    blue.health.startAnimation(shake);
                    blue.health.setImageDrawable(getResources().getDrawable(R.drawable.blue_health_0_3));
                    red.setScore(red.getScore()+1);

                    int games = red.getScore() + blue.getScore();
                    int redScore = red.getScore();
                    int blueScore = blue.getScore();

                    String tempA = Integer.toString(games);
                    String tempB = Integer.toString(redScore);
                    String tempC = Integer.toString(blueScore);

                    arrayList.add(new Statistics(tempA,tempB,tempC));
                    PrefConfig.write(getApplicationContext(),arrayList);
                    delay(view);
                }
            }
        });
    }


    // method to create delay once a player dies and
    // sends user to next activity
    //-----------------------------------------------------------------------
    public void delay(View view){
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                launchGameoverActivity(view);
            }
        }, 1500);
    }


    // method to keep track of player BLUE's direction
    // and setting model to match direction desired
    //-----------------------------------------------------------------------
    public void changeBluePos(){
        float bluePlayerX = blue.getModel().getX();
        float bluePlayerY = blue.getModel().getY();
        blue.setPlayerY(bluePlayerY);
        if (blue.isActionLeft()){
            bluePlayerX -= 20;
            blue.setPlayerX(bluePlayerX);
            blue.model.setImageDrawable(blue.getLeft());
        }
        if (blue.isActionRight()){
            bluePlayerX += 20;
            blue.setPlayerX(bluePlayerX);
            blue.model.setImageDrawable(blue.getRight());
        }
        if (blue.getPlayerX() < 0){
            bluePlayerX = 0;
            blue.setPlayerX(bluePlayerX);
            blue.model.setImageDrawable(blue.getFront());
        }
        if (blue.getPlayerX() > bluePlatform.getWidth() - blue.model.getWidth()){
            bluePlayerX = bluePlatform.getWidth() - blue.model.getWidth();
            blue.setPlayerX(bluePlayerX);
            blue.model.setImageDrawable(blue.getFront());
        }
        blue.getModel().setX(bluePlayerX);
    }



    // method to keep track of player RED's direction
    // and setting model to match direction desired
    //-----------------------------------------------------------------------
    public void changeRedPos(){
        float redPlayerX = red.getModel().getX();
        float redPlayerY = red.getModel().getY();
        red.setPlayerY(redPlayerY);
        if (red.isActionLeft()){
            redPlayerX += 20;
            red.setPlayerX(redPlayerX);
            red.model.setImageDrawable(red.getLeft());
        }
        if (red.isActionRight()){
            redPlayerX -= 20;
            red.setPlayerX(redPlayerX);
            red.model.setImageDrawable(red.getRight());
        }
        if (red.getPlayerX() < 0){
            redPlayerX = 0;
            red.setPlayerX(redPlayerX);
            red.model.setImageDrawable(red.getFront());
        }
        if (red.getPlayerX() > redPlatform.getWidth() - red.model.getWidth()){
            redPlayerX = redPlatform.getWidth() - red.model.getWidth();
            red.setPlayerX(redPlayerX);
            red.model.setImageDrawable(red.getFront());
        }
        red.getModel().setX(redPlayerX);
    }


    // method to keep track of when user presses
    // a direction button for player and setting
    // that action to true or false if no touch
    // is detected
    //-----------------------------------------------------------------------
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent){
        if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {
            switch (view.getId()) {
                case R.id.blue_left_btn:
                    blue.setActionLeft(true);
                    break;
                case R.id.blue_right_btn:
                    blue.setActionRight(true);
                    break;
                case R.id.red_left_btn:
                    red.setActionLeft(true);
                    break;
                case R.id.red_right_btn:
                    red.setActionRight(true);
                    break;
            }
        } else {
            blue.setActionLeft(false);
            blue.setActionRight(false);
            red.setActionLeft(false);
            red.setActionRight(false);
        }
        return true;
    }


    // methods that send user to specified activity
    //-----------------------------------------------------------------------
    public void launchGameoverActivity(View view) {
        Intent intent = new Intent(this, GameoverActivity.class);
        startActivity(intent);
    }
}