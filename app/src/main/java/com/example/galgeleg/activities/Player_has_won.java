package com.example.galgeleg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.galgeleg.GalgeController;
import com.example.galgeleg.R;
import com.example.galgeleg.game_state.Initial;
import com.example.galgeleg.game_state.Running;

public class Player_has_won extends AppCompatActivity implements View.OnClickListener {

    GalgeController galgeController;
    Button playAgain;
    Button menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_player_has_won );

        Intent i = getIntent();

        galgeController = i.getParcelableExtra( "Controller" );

        playAgain = findViewById(R.id.playerWonPlayAgain);
        menu = findViewById(R.id.playerWonMenu);

        playAgain.setOnClickListener(this);
        menu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v == playAgain){
            this.galgeController.changeState(new Running(galgeController));
            Intent intent = new Intent(this,GalgelegGame.class);
            startActivity(intent);
        }else if(v == menu){
            this.galgeController.changeState(new Initial(galgeController));
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}