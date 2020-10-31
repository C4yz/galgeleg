package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.galgeleg.letters.CorrectLetters;
import com.example.galgeleg.letters.ILetters;
import com.example.galgeleg.letters.WrongLetters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GalgelegGame extends AppCompatActivity{

    private String theWordToGuess;
    private ArrayList<String> usedLetters = new ArrayList<String>();
    private int numberOfWrongLetters;
    private boolean hasPlayerWon;
    private boolean hasPlayerLost;
    private int choice;
    private String hiddenWord;
    private List<ILetters> letters = new ArrayList<>();
    private String updatedHiddenWord = "";

    GalgeController controller = new GalgeController();

    CorrectLetters correctLetters = new CorrectLetters(this,this);
    WrongLetters wrongLetters = new WrongLetters(this,this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galgeleg_game);

        createButton();

        Intent intent = getIntent();
        choice = intent.getIntExtra("choices",0);

        try {
            startNewGame();
        } catch (Exception e) {
            e.printStackTrace();
        }

        hiddenWord = controller.displayTheWord(theWordToGuess);

        TextView textView = (TextView) findViewById(R.id.wordDisplay);
        textView.setText(hiddenWord);
    }

    public void startNewGame() throws Exception {
        controller.getTheWords(choice);
        usedLetters.clear();
        numberOfWrongLetters = 0;
        hasPlayerWon = false;
        hasPlayerLost = false;
        if (Library.posibleWords.isEmpty()) throw new IllegalStateException("Listen over mulige ord er tom!");
        theWordToGuess = Library.posibleWords.get(new Random().nextInt(Library.posibleWords.size()));
        System.out.println("Nyt spil - det skjulte ord er: "+ theWordToGuess);
    }

    public void add(ILetters observers){
        letters.add(observers);
    }

    public void notifyAllObservers(char buttonLetter){
        String buttonStringLetter = String.valueOf(buttonLetter);
        for(ILetters letter: letters){
            letter.update(buttonStringLetter,theWordToGuess,usedLetters);
        }
    }

    public void createButton(){
        GridLayout grid = (GridLayout) findViewById(R.id.letterGrid);
        grid.setColumnCount(5);
        grid.setRowCount(6);
        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Æ', 'Ø', 'Å'};
        for(char letter : alphabet){
            final Button button = new Button(getApplicationContext());
            button.setMinimumWidth(120);
            button.setMinimumHeight(120);
            button.setWidth(50);
            button.setHeight(40);
            button.setText(letter + "");
            final char buttonLetter = letter;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button.setText("");
                    button.setEnabled(false);
                    notifyAllObservers(buttonLetter);
                }
            });
            grid.addView(button);
        }
    }
}