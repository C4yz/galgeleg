package com.example.galgeleg.game_state;

import com.example.galgeleg.GalgeController;
import com.example.galgeleg.Library;
import com.example.galgeleg.activities.GalgelegGame;

import java.io.IOException;

public class PlayerWon extends Adapter{

    GalgeController galgeController;

    public PlayerWon(GalgeController galgeController){
        this.galgeController = galgeController;
    }

    @Override
    public void startNewGame(int choice) throws Exception {
        if (choice == 1) {
            galgeController.changeState(new Running(this.galgeController));
        }else if(choice == 2){
            galgeController.changeState(new Initial(this.galgeController));
        }

        Library.posibleWords.clear();
    }
}