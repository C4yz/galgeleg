package com.example.galgeleg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.galgeleg.GalgeController;
import com.example.galgeleg.MyObj;
import com.example.galgeleg.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Player_has_won extends AppCompatActivity implements View.OnClickListener {

    GalgeController galgeController;
    Button playAgain;
    Button menu;
    Button highList;
    int choice;
    int numberOfTries;
    String playerName;

    MyObj myObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_player_has_won );

        Intent i = getIntent();

        myObj = (MyObj) i.getSerializableExtra( "Controller" );
        numberOfTries = i.getIntExtra("numberOfTires",0);
        playerName = i.getStringExtra("PlayerName");

        playAgain = findViewById(R.id.playerWonPlayAgain);
        menu = findViewById(R.id.playerWonMenu);
        highList = findViewById(R.id.highScoreButton);

        playAgain.setOnClickListener(this);
        menu.setOnClickListener(this);
        highList.setOnClickListener(this);
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