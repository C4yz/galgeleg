package com.example.galgeleg.game_state;

public interface IGameState {

    void startNewGame(int choice) throws Exception;

    void getTheWords(int choice) throws Exception;

    void displayTheWord(String wordToHide);

    void updateWord();

    void guessedLetter(String guessedLetter);

    
}
