package com.example.galgeleg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.galgeleg.GalgeController;
import com.example.galgeleg.R;

import java.lang.reflect.Field;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GalgelegGame extends AppCompatActivity {

    private int choice;
    private int numberOfTries;
    private String hiddenWord;
    private String playerName;

    GalgeController controller = GalgeController.getInstance();
    TextView textView;
    ImageView imageView;
    Intent intent;
    Executor bgThread = Executors.newSingleThreadExecutor();
    Handler uiHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_galgeleg_game);

        createButton();
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        choice = intent.getIntExtra("choices",0);
        textView = findViewById(R.id.wordDisplay);
        playerName = intent.getStringExtra("PlayerName");

        bgThread.execute(()->{
            try {
                controller.startNewGame(choice);
                uiHandler.post(()->{
                    hiddenWord = controller.getHiddenWord();
                    textView.setText(hiddenWord);
                });
            } catch (Exception e) {
                e.printStackTrace();
                uiHandler.post(()->{
                   textView.setText("Der skete en fejl ved hentning");
                });
            }
        });

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

        int numberOfTires = controller.getNumberOfFailedTries();
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
        numberOfTries = controller.getNumberOfFailedTries();

        if(state){
            intent = new Intent(this,Player_has_won.class);
            intent.putExtra("numberOfTires", numberOfTries);
            intent.putExtra("PlayerName",playerName);
        }else{
            intent = new Intent(this, Player_has_lost.class);
        }
        startActivity(intent);
    }
}