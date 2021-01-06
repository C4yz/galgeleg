package com.example.galgeleg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.galgeleg.GalgeController;
import com.example.galgeleg.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Player_has_won extends AppCompatActivity implements View.OnClickListener {

    GalgeController galgeController;
    Button playAgain;
    Button menu;
    Button highList;
    TextView text_animation;
    int choice;
    int numberOfTries;
    String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_player_has_won );

        Intent i = getIntent();

        galgeController = i.getParcelableExtra( "Controller" );
        numberOfTries = i.getIntExtra("numberOfTires",0);
        playerName = i.getStringExtra("PlayerName");

        playAgain = findViewById(R.id.playerWonPlayAgain);
        menu = findViewById(R.id.playerWonMenu);
        highList = findViewById(R.id.highScoreButton);
        text_animation = findViewById(R.id.textWinnerID);

        playAgain.setOnClickListener(this);
        menu.setOnClickListener(this);
        highList.setOnClickListener(this);

        MediaPlayer player = MediaPlayer.create(this,R.raw.victorysoundeffect);
        player.start();

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim);
        text_animation.startAnimation(animation);
    }

    @Override
    public void onClick(View v) {

        if(v == playAgain){
            choice = 1;
            try {
                this.galgeController.startNewGame(choice);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(this,GalgelegGame.class);
            intent.putExtra("PlayerName",playerName);
            startActivity(intent);

        }else if(v == menu){
            choice = 2;
            try {
                this.galgeController.startNewGame(choice);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else if(v == highList){
            Intent intent = new Intent(this, HighScore.class);
            intent.putExtra("PlayerName",playerName);
            intent.putExtra("NumberOfTries",numberOfTries);
            startActivity(intent);
        }
    }
}