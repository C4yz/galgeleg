package com.example.galgeleg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.galgeleg.GalgeController;
import com.example.galgeleg.R;
import com.example.galgeleg.game_state.Initial;
import com.example.galgeleg.game_state.Running;

public class Player_has_lost extends AppCompatActivity implements View.OnClickListener {

    GalgeController galgeController;
    String theWordToGuess;
    TextView textView;
    Button playAgain;
    Button menu;
    int choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_player_has_lost );

        Intent i = getIntent();

        galgeController = i.getParcelableExtra( "Controller" );

        theWordToGuess = this.galgeController.getTheWordToGuess();
        textView = (TextView) findViewById(R.id.playerLostTheWord);
        textView.setText("Du tabte dit ordet der skulle gættes var:"+ theWordToGuess);

        playAgain = findViewById( R.id.playerLostPlayAgain );
        menu = findViewById(R.id.playerLostMenu);

        playAgain.setOnClickListener(this);
        menu.setOnClickListener(this);
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
        }

    }
}