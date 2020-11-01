package com.example.galgeleg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.galgeleg.GalgeController;
import com.example.galgeleg.R;
import com.example.galgeleg.game_state.IGameState;

import java.io.Serializable;
import java.lang.reflect.Field;

public class GalgelegGame extends AppCompatActivity{

    private int choice;
    private String hiddenWord;

    GalgeController controller = new GalgeController(this);
    TextView textView;
    ImageView imageView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_galgeleg_game);

        createButton();
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        choice = intent.getIntExtra("choices",0);

        try {
            controller.startNewGame(choice);
        } catch (Exception e) {
            e.printStackTrace();
        }

        hiddenWord = controller.getHiddenWord();
        textView = (TextView) findViewById(R.id.wordDisplay);
        textView.setText(hiddenWord);
    }

    public void createButton(){
        GridLayout grid = (GridLayout) findViewById(R.id.letterGrid);
        grid.setColumnCount(5);
        grid.setRowCount(6);
        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Æ', 'Ø', 'Å'};
        for(char letter : alphabet){
            final Button button = new Button( getApplicationContext());
            button.setMinimumWidth(120);
            button.setMinimumHeight(120);
            button.setWidth(50);
            button.setHeight(40);
            button.setText(letter + "");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String buttonLetter = button.getText().toString().toLowerCase();
                    button.setText("");
                    controller.guessedLetter(buttonLetter);
                    updateGameActivity();
                    button.setEnabled(false);
                }
            });
            grid.addView(button);
        }
    }

    public void updateGameActivity() {
        String visibleWord = controller.getVisibleWord();

        textView.setText(visibleWord);

        int numberOfTires = controller.getNumberOfTries();
        if(numberOfTires != 0){
            try {
                Field field = R.drawable.class.getDeclaredField("forkert"+Integer.toString(numberOfTires));
                imageView.setImageResource(field.getInt(field));
            }catch (Exception e){
                imageView.setImageResource(R.drawable.galge);
            }
        }
    }

    public void gameOver(boolean state){
        if(state){
            intent = new Intent(this,Player_has_won.class);
            intent.putExtra("numberOfTires", controller.getNumberOfTries());
        }else{
            intent = new Intent(this, Player_has_lost.class);
        }

        intent.putExtra( "Controller", controller );
        startActivity(intent);
    }
}