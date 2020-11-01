package com.example.galgeleg.game_state;

import com.example.galgeleg.GalgeController;
import com.example.galgeleg.activities.Player_has_lost;

import java.io.IOException;

public class PlayerLost implements IGameState{

    GalgeController galgeController;

    public PlayerLost(GalgeController galgeController){
        this.galgeController=galgeController;
    }

    @Override
    public void startNewGame(int choice) throws Exception {

    }

    @Override
    public void getTheWords(int choice) throws Exception {

    }

    @Override
    public void displayTheWord(String wordToHide) {

    }

    @Override
    public void updateWord() {

    }

    @Override
    public void guessedLetter(String guessedLetter) {

    }
}
