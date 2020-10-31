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
        /*int numberOfWrongLetters = this.galgeController.getNumberOfWrongLetters();
        boolean playerHasWon = this.galgeController.getPlayerHasWon();
        boolean playerHasLost = this.galgeController.getPlayerHasLost();*/
        String theWordToGuess = this.galgeController.getTheWordToGuess();

        this.galgeController.getTheWords(choice);
        this.galgeController.setNumberOfWrongLetters(0);
        this.galgeController.setPlayerHasLost(false);
        this.galgeController.setPlayerHasLost(false);
        if (Library.posibleWords.isEmpty()) throw new IllegalStateException("Listen over mulige ord er tom!");
        this.galgeController.setTheWordToGuess(theWordToGuess = Library.posibleWords.get(new Random().nextInt(Library.posibleWords.size())));
        //theWordToGuess = "hej";
        System.out.println("Nyt spil - det skjulte ord er: "+ theWordToGuess);
    }


    @Override
    public void getTheWords(int choice) throws Exception {

        switch (choice){
            case 0:
                library.standartOrd();
                break;
            case 1:
                library.hentOrdFraDr();
                break;
            case 2:
                library.hentOrdFraRegneark("1");
                break;
            case 3:
                library.hentOrdFraRegneark("2");
                break;
            case 4:
                library.hentOrdFraRegneark("3");
            default:
                break;
        }
    }

    @Override
    public void displayTheWord(String wordToHide) {
        String hiddenWord = this.galgeController.getHiddenWord();

        for (int i = 0; i < wordToHide.length(); i++){
            this.galgeController.setHiddenWord(hiddenWord += "*");
        }
    }

    @Override
    public void updateWord() {
        String theWordToGuess = this.galgeController.getTheWordToGuess();
        String usedCorrectLetters = this.galgeController.getUsedCorrectLetters();
        String visibleWord = "";

        for (char letter : theWordToGuess.toCharArray()) {
            if(usedCorrectLetters.indexOf(letter) != -1){
                this.galgeController.setVisibleWord(visibleWord += letter);
            }else{
                this.galgeController.setVisibleWord(visibleWord += "*");
            }
        }
    }

    @Override
    public void guessedLetter(String guessedLetter) {
        //String theWordToGuess = this.galgeController.getTheWordToGuess();
        String usedCorrectLetters = this.galgeController.getUsedCorrectLetters();
        //int numberOfTries = this.galgeController.getNumberOfTries();

        if(this.galgeController.getTheWordToGuess().contains(guessedLetter)){
            usedCorrectLetters += guessedLetter;
            this.galgeController.setUsedCorrectLetters(usedCorrectLetters);
        }else{
            this.galgeController.setNumberOfTries(+1);
        }
        this.galgeController.updateWord();
    }
}
