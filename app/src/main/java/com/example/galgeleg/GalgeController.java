package com.example.galgeleg;

import com.example.galgeleg.activities.GalgelegGame;
import com.example.galgeleg.activities.Player_has_lost;
import com.example.galgeleg.activities.Player_has_won;
import com.example.galgeleg.game_state.IGameState;
import com.example.galgeleg.game_state.Initial;
import com.example.galgeleg.game_state.PlayerLost;
import com.example.galgeleg.game_state.PlayerWon;
import com.example.galgeleg.game_state.Running;

public class GalgeController {

    private String theWordToGuess;
    private String usedCorrectLetters;
    private String visibleWord;
    private String hiddenWord;
    private int numberOfTries;
    private int numberOfWrongLetters;
    private boolean lastLetterWasCorrect;
    private boolean playerHasWon;
    private boolean playerHasLost;

    IGameState iGameState;
    GalgelegGame game;

    public GalgeController(GalgelegGame galgelegGame){
        this.iGameState = new Initial(this);
        this.game = galgelegGame;
        visibleWord = "";
        theWordToGuess = "";
        numberOfTries = 0;
        usedCorrectLetters = "";
        hiddenWord = "";
        playerHasLost = false;
        playerHasWon = false;
        lastLetterWasCorrect = false;
    }

    public void startNewGame(int choice) throws Exception {
        this.iGameState = new Running(this);
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

    public void setNumberOfTries(int numberOfTries) {
        this.numberOfTries = numberOfTries;
    }

    public int getNumberOfTries(){
        return numberOfTries;
    }

    public void setNumberOfWrongLetters(int numberOfWrongLetters) {
        this.numberOfWrongLetters = numberOfWrongLetters;
    }

    public int getNumberOfWrongLetters(){
        return numberOfWrongLetters;
    }

    public void setPlayerHasWon(boolean playerHasWon) {
        this.playerHasWon = playerHasWon;
    }

    public boolean getPlayerHasWon(){
        return playerHasWon;
    }

    public void setPlayerHasLost(boolean playerHasLost) {
        this.playerHasLost = playerHasLost;
    }

    public boolean getPlayerHasLost(){
        return playerHasLost;
    }

    public void setLastLetterWasCorrect(boolean lastLetterWasCorrect) {
        this.lastLetterWasCorrect = lastLetterWasCorrect;
    }

    public boolean getLastLetterWasCorrect(){
        return lastLetterWasCorrect;
    }
}
