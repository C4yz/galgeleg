package com.example.galgeleg;

import com.example.galgeleg.activities.GalgelegGame;

import java.io.Serializable;

public class MyObj implements Serializable {

    String name;
    int number;
    String something;
    GalgelegGame galgelegGame = new GalgelegGame();

    public MyObj() {
        name = "Thomas";
        number = 22;
        something = "Something";
    }
}
