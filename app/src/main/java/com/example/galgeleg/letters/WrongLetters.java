package com.example.galgeleg.letters;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.galgeleg.GalgelegGame;
import com.example.galgeleg.R;

import java.util.ArrayList;

public class WrongLetters extends AppCompatActivity implements ILetters {

    GalgelegGame galgelegGame;
    Context context;

    public WrongLetters (GalgelegGame galgelegGame, Context context){
        this.galgelegGame = galgelegGame;
        galgelegGame.add(this);
        this.context = context;
    }


    @Override
    public void update(String letter, String theWordToGuess, ArrayList<String> usedLetters) {
        String visableLetter = "";
        for(int i = 0; i < theWordToGuess.length(); i++){
            letter = theWordToGuess.substring(i, i+1);
            if(usedLetters.contains(letter)){
                visableLetter = visableLetter + letter;
            }else{
                visableLetter += "*";
            }
        }
        TextView textView = (TextView) ((Activity)context).findViewById(R.id.wordDisplay);
        textView.setText(visableLetter);
    }
}