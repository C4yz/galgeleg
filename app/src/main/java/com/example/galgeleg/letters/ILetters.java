package com.example.galgeleg.letters;

import java.util.ArrayList;

public interface ILetters {

    void update(String letter, String theWordToGuess, ArrayList<String> usedLetters);
}
