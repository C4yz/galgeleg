package com.example.galgeleg;

import com.example.galgeleg.activities.GalgelegGame;

import java.io.Serializable;

public class MyObj implements Serializable {

    String name;
    int number;
    String something;

    GalgelegGame galgelegGame;

    public MyObj(GalgelegGame galgelegGame) {
        name = "Thomas";
        number = 22;
        something = "Something";
        this.galgelegGame = galgelegGame;
    }

}
