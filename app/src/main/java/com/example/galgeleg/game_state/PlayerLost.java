package com.example.galgeleg.game_state;

import com.example.galgeleg.GalgeController;
import com.example.galgeleg.Library;

public class PlayerLost implements IGameState{

    GalgeController galgeController = GalgeController.getInstance();

    public PlayerLost(){
    }

    @Override
    public void startNewGame(int choice) throws Exception {
        if (choice == 1) {
            galgeController.changeState(new Running());
        }else if(choice == 2){
            galgeController.changeState(new Initial());
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
