package com.example.galgeleg.game_state;

import com.example.galgeleg.GalgeController;

public class Initial implements IGameState {

    GalgeController galgeController;

    public Initial(GalgeController galgeController) {
        this.galgeController = galgeController;
    }

    @Override
    public void startNewGame(int choice) throws Exception {
        this.galgeController.changeState(new Running(galgeController));
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
