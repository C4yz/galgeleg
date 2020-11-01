package com.example.galgeleg.game_state;

import com.example.galgeleg.GalgeController;
import com.example.galgeleg.Library;

import java.util.Random;

public class Running implements IGameState{

    GalgeController galgeController;
    Library library = new Library();

    public Running(GalgeController galgeController){
        this.galgeController = galgeController;
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
        String theWordToGuess = this.galgeController.getTheWordToGuess();
        String usedCorrectLetters = this.galgeController.getUsedCorrectLetters();
        String visibleWord = "";

        for (char letter : theWordToGuess.toCharArray()) {
            if(usedCorrectLetters.indexOf(letter) != -1){
                visibleWord += letter;
            }else{
                visibleWord += "*";
            }
        }
        this.galgeController.setVisibleWord(visibleWord);
    }

    @Override
    public void guessedLetter(String guessedLetter) {
        String usedCorrectLetters = this.galgeController.getUsedCorrectLetters();
        int numberOfTries = this.galgeController.getNumberOfTries();

        if(this.galgeController.getTheWordToGuess().contains(guessedLetter)){
            usedCorrectLetters += guessedLetter;
            this.galgeController.setUsedCorrectLetters(usedCorrectLetters);
            this.galgeController.setLastLetterWasCorrect(true);
            this.galgeController.updateWord();
        }else{
            numberOfTries++;
            this.galgeController.setNumberOfTries(numberOfTries);
            this.galgeController.setLastLetterWasCorrect(false);
        }

        String visibleWord = this.galgeController.getVisibleWord();

        if(this.galgeController.getNumberOfTries() == 6 && !this.galgeController.getLastLetterWasCorrect()){
            this.galgeController.changeState(new PlayerLost(galgeController));
        }else if(this.galgeController.getNumberOfTries() < 6 && !visibleWord.contains("*")){
            this.galgeController.changeState(new PlayerWon(galgeController));
        }
    }
}