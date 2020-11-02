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
import com.example.galgeleg.R;

import java.util.ArrayList;

public class Player_has_won extends AppCompatActivity implements View.OnClickListener {

    GalgeController galgeController;
    Button playAgain;
    Button menu;
    Button highList;
    int choice;
    int numberOfTries;
    TextView highScoreCounter;
    TextView yourScoreCounter;
    String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_player_has_won );

        highScoreCounter = findViewById(R.id.highScoreCounter);
        yourScoreCounter = findViewById(R.id.yourScoreCounter);

        Intent i = getIntent();

        galgeController = i.getParcelableExtra( "Controller" );
        numberOfTries = i.getIntExtra("numberOfTires",0);
        playerName = i.getStringExtra("PLayerName");

        playAgain = findViewById(R.id.playerWonPlayAgain);
        menu = findViewById(R.id.playerWonMenu);
        highList = findViewById(R.id.highScoreButton);

        playAgain.setOnClickListener(this);
        menu.setOnClickListener(this);
        highList.setOnClickListener(this);

        loadHighScore();
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

    public void loadHighScore(){
        SharedPreferences load = this.getSharedPreferences("highScore", Context.MODE_PRIVATE);
        numberOfTries = load.getInt("score", 0);
        highScoreCounter.setText("High score : " + numberOfTries );
    }
}