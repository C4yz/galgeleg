package com.example.galgeleg.game_state;

import com.example.galgeleg.GalgeController;
import com.example.galgeleg.Library;

import java.io.Serializable;
import java.util.Random;

public class Initial extends Adapter implements Serializable {

    GalgeController galgeController;
    Library library = new Library();

    public Initial(GalgeController galgeController) {
        this.galgeController = galgeController;
    }

    @Override
    public void startNewGame(int choice) throws Exception {
        String theWordToGuess;

        this.galgeController.getTheWords(choice);
        this.galgeController.setNumberOfWrongLetters(0);
        if (Library.posibleWords.isEmpty()) throw new IllegalStateException("Listen over mulige ord er tom!");
        theWordToGuess = Library.posibleWords.get(new Random().nextInt(Library.posibleWords.size()));
        this.galgeController.setTheWordToGuess(theWordToGuess);
        this.galgeController.displayTheWord(theWordToGuess);

        library.printPossibleWords(Library.posibleWords);

        this.galgeController.changeState(new Running(galgeController));
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
            hiddenWord += "*";
        }

        this.galgeController.setHiddenWord(hiddenWord);
        this.galgeController.setVisibleWord(hiddenWord);
    }

}
