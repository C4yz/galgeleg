package com.example.galgeleg.game_state;

import com.example.galgeleg.GalgeController;
import com.example.galgeleg.Library;
import com.example.galgeleg.activities.Player_has_lost;

import java.io.IOException;
import java.util.Random;

public class PlayerLost implements IGameState{

    GalgeController galgeController;

    public PlayerLost(GalgeController galgeController){
        this.galgeController=galgeController;
    }

    @Override
    public void startNewGame(int choice) throws Exception {
        if (choice == 1) {
            galgeController.changeState(new Running(this.galgeController));
        }else if(choice == 2){
            galgeController.changeState(new Initial(this.galgeController));
        }

        Library.posibleWords.clear();
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
