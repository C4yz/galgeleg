package com.example.galgeleg;

import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class GalgeController {

    private String ordet;
    private ArrayList<String> brugteBogstaver = new ArrayList<String>();
    private String synligtOrd;
    private int antalForkerteBogstaver;
    private boolean sidsteBogstavVarKorrekt;
    private boolean spilletErVundet;
    private boolean spilletErTabt;

    Library library = new Library();

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

}
