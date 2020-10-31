package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class GalgelegGame extends AppCompatActivity{

    private String theWordToGuess;
    private int choice;
    private String hiddenWord;

    GalgeController controller = new GalgeController();
    GalgeController galgeController = new GalgeController();
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galgeleg_game);

        createButton();

        Intent intent = getIntent();
        choice = intent.getIntExtra("choices",0);

        theWordToGuess = galgeController.getTheWordToGuess();

        try {
            galgeController.startNewGame(choice);
        } catch (Exception e) {
            e.printStackTrace();
        }

        hiddenWord = controller.displayTheWord(theWordToGuess);

        textView = (TextView) findViewById(R.id.wordDisplay);
        textView.setText(hiddenWord);
    }

    /*public void add(ILetters observers){
        letters.add(observers);
    }

    public void notifyAllObservers(char buttonLetter){
        String buttonStringLetter = String.valueOf(buttonLetter);
        for(ILetters letter: letters){
            letter.update(buttonStringLetter,theWordToGuess,usedLetters);
        }
    }*/

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
                    galgeController.guessedLetter(buttonLetter);
                    updateGameActivity();
                    button.setEnabled(false);
                }
            });
            grid.addView(button);
        }
    }

    public void updateGameActivity(){
        String visibleWord = galgeController.getVisibleWord();

        textView.setText(visibleWord);
    }
}