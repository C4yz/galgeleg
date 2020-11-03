package com.example.galgeleg.game_state;

import com.example.galgeleg.GalgeController;
import com.example.galgeleg.Library;

public class Running extends Adapter{

    GalgeController galgeController;

    public Running(GalgeController galgeController){
        this.galgeController = galgeController;
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
        int numberOfTries = this.galgeController.getNumberOfFailedTries();

        if(this.galgeController.getTheWordToGuess().contains(guessedLetter)){
            usedCorrectLetters += guessedLetter;
            this.galgeController.setUsedCorrectLetters(usedCorrectLetters);
            this.galgeController.setLastLetterWasCorrect(true);
            this.galgeController.updateWord();
        }else{
            numberOfTries++;
            this.galgeController.setNumberOfFailedTries(numberOfTries);
            this.galgeController.setLastLetterWasCorrect(false);
        }

        String visibleWord = this.galgeController.getVisibleWord();

        if(this.galgeController.getNumberOfFailedTries() == 6 && !this.galgeController.getLastLetterWasCorrect()){
            this.galgeController.changeState(new PlayerLost(galgeController));
        }else if(this.galgeController.getNumberOfFailedTries() < 6 && !visibleWord.contains("*")){
            this.galgeController.changeState(new PlayerWon(galgeController));
        }
    }
}