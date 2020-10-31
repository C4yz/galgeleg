package com.example.galgeleg;

import com.example.galgeleg.game_state.IGameState;
import com.example.galgeleg.game_state.Running;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

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

    Library library = new Library();
    IGameState iGameState;

    public GalgeController(){
        this.iGameState = new Running(this);
        visibleWord = "";
        theWordToGuess = "";
        numberOfTries = 0;
        usedCorrectLetters = "";
        hiddenWord = "";
    }

    public void startNewGame(int choice) throws Exception {
        this.iGameState.startNewGame(choice);
        /*getTheWords(choice);
        numberOfWrongLetters = 0;
        playerHasWon = false;
        playerHasLost = false;
        if (Library.posibleWords.isEmpty()) throw new IllegalStateException("Listen over mulige ord er tom!");
        theWordToGuess = Library.posibleWords.get(new Random().nextInt(Library.posibleWords.size()));
        theWordToGuess = "hej";
        System.out.println("Nyt spil - det skjulte ord er: "+ theWordToGuess);*/
    }

    public void getTheWords(int choice) throws Exception {

        this.iGameState.getTheWords(choice);

        /*switch (choice){
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
        }*/
    }

    public void displayTheWord(String wordToHide){
        this.iGameState.displayTheWord(wordToHide);
        /*String hiddenWord = "";

        for (int i = 0; i < wordToHide.length(); i++){
            hiddenWord += "*";
        }*/
    }

    public void updateWord(){
        this.iGameState.updateWord();
        /*visibleWord = "";

        for (char letter : theWordToGuess.toCharArray()) {
            if(usedCorrectLetters.indexOf(letter) != -1){
                visibleWord += letter;
            }else{
                visibleWord += "*";
            }
        }*/
    }

    public void guessedLetter(String guessedLetter){
        this.iGameState.guessedLetter(guessedLetter);
        /*if(theWordToGuess.contains(guessedLetter)){
            usedCorrectLetters += guessedLetter;
        }else{
            numberOfTries++;
        }
        updateWord();*/
    }

    public void changeState(IGameState gameState){
        this.iGameState = gameState;
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
