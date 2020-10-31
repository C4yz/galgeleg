package com.example.galgeleg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class GalgeController {

    private String theWordToGuess;
    private String usedCorrectLetters;
    private String visibleWord;
    private int numberOfTries;
    private int numberOfWrongLetters;
    private boolean lastLetterWasCorrect;
    private boolean playerHasWon;
    private boolean playerHasLost;

    Library library = new Library();

    public GalgeController(){
        visibleWord = "";
        theWordToGuess = "";
        numberOfTries = 0;
        usedCorrectLetters = "";
    }

    public void startNewGame(int choice) throws Exception {
        getTheWords(choice);
        numberOfWrongLetters = 0;
        playerHasWon = false;
        playerHasLost = false;
        if (Library.posibleWords.isEmpty()) throw new IllegalStateException("Listen over mulige ord er tom!");
        //theWordToGuess = Library.posibleWords.get(new Random().nextInt(Library.posibleWords.size()));
        theWordToGuess = "hej";
        System.out.println("Nyt spil - det skjulte ord er: "+ theWordToGuess);
    }


    public static String hentUrl(String url) throws IOException {
        System.out.println("Henter data fra " + url);
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        StringBuilder sb = new StringBuilder();
        String linje = br.readLine();
        while (linje != null) {
            sb.append(linje + "\n");
            linje = br.readLine();
        }
        return sb.toString();
    }

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

    public String displayTheWord(String wordToHide){
        String hiddenWord = "";

        for (int i = 0; i < wordToHide.length(); i++){
            hiddenWord += "*";
        }
        return hiddenWord;
    }

    public void updateWord(){

        visibleWord = "";

        for (char letter : theWordToGuess.toCharArray()) {
            if(usedCorrectLetters.indexOf(letter) != -1){
                visibleWord += letter;
            }else{
                visibleWord += "*";
            }
        }
    }

    public void guessedLetter(String guessedLetter){
        if(theWordToGuess.contains(guessedLetter)){
            usedCorrectLetters += guessedLetter;
        }else{
            numberOfTries++;
        }
        updateWord();
    }

    public String getVisibleWord() {
        return visibleWord;
    }

    public String getTheWordToGuess(){
        return theWordToGuess;
    }
}
