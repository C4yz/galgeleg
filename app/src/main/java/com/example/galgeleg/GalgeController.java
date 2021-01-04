package com.example.galgeleg;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.galgeleg.activities.GalgelegGame;
import com.example.galgeleg.game_state.IGameState;
import com.example.galgeleg.game_state.Initial;
import com.example.galgeleg.game_state.PlayerLost;
import com.example.galgeleg.game_state.PlayerWon;
import com.google.gson.internal.$Gson$Preconditions;

public class GalgeController{

    private static volatile GalgeController controller;
    private static final Object object = new Object();

    String theWordToGuess = "";
    String usedCorrectLetters = "";
    String visibleWord = "";
    String hiddenWord = "";
    int numberOfFailedTries = 0;
    int numberOfWrongLetters = 0;
    boolean lastLetterWasCorrect = false;

    private static IGameState iGameState;
    private static GalgelegGame game;

    private GalgeController(){

        /*
        GalgelegGame galgelegGame
        game = galgelegGame;
        visibleWord = "";
        theWordToGuess = "";
        numberOfFailedTries = 0;
        usedCorrectLetters = "";
        hiddenWord = "";
        lastLetterWasCorrect = false;*/
    }

    private static void init(){
        iGameState = new Initial();
        game = new GalgelegGame();
    }

    public static GalgeController getInstance(){

        if(controller == null){
            controller = new GalgeController();
            init();
        }

        return controller;
    }

    public void startNewGame(int choice) throws Exception {
        this.iGameState.startNewGame(choice);
    }

    public void getTheWords(int choice) throws Exception {
        this.iGameState.getTheWords(choice);
    }

    public void displayTheWord(String wordToHide){
        this.iGameState.displayTheWord(wordToHide);
    }

    public void updateWord(){
        this.iGameState.updateWord();
    }

    public void guessedLetter(String guessedLetter){
        this.iGameState.guessedLetter(guessedLetter);
    }

    public void changeState(IGameState gameState){
        this.iGameState = gameState;

        if(this.iGameState instanceof PlayerWon){
            this.game.gameOver(true);
        }

        if(this.iGameState instanceof PlayerLost){
            this.game.gameOver(false);
        }
    }

    public void setVisibleWord(String visibleWord) {
        this.visibleWord = visibleWord;
    }

    public String getVisibleWord() {
        return visibleWord;
    }

    public void setTheWordToGuess(String theWordToGuess) {
        this.theWordToGuess = theWordToGuess;
    }

    public String getTheWordToGuess(){
        return theWordToGuess;
    }

    public void setUsedCorrectLetters(String usedCorrectLetters) {
        this.usedCorrectLetters = usedCorrectLetters;
    }

    public String getUsedCorrectLetters(){
        return usedCorrectLetters;
    }

    public void setHiddenWord(String hiddenWord) {
        this.hiddenWord = hiddenWord;
    }

    public String getHiddenWord(){
        return hiddenWord;
    }

    public void setNumberOfFailedTries(int numberOfFailedTries) {
        this.numberOfFailedTries = numberOfFailedTries;
    }

    public int getNumberOfFailedTries(){
        return numberOfFailedTries;
    }

    public void setNumberOfWrongLetters(int numberOfWrongLetters) {
        this.numberOfWrongLetters = numberOfWrongLetters;
    }

    public int getNumberOfWrongLetters(){
        return numberOfWrongLetters;
    }


    public void setLastLetterWasCorrect(boolean lastLetterWasCorrect) {
        this.lastLetterWasCorrect = lastLetterWasCorrect;
    }

    public boolean getLastLetterWasCorrect(){
        return lastLetterWasCorrect;
    }

}
