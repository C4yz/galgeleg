package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class GalgelegGame extends AppCompatActivity {

    private String ordet;
    private ArrayList<String> brugteBogstaver = new ArrayList<String>();
    private int antalForkerteBogstaver;
    private boolean spilletErVundet;
    private boolean spilletErTabt;
    private int choice;
    private String hiddenWord;

    GalgeController controller = new GalgeController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galgeleg_game);

        Intent intent = getIntent();
        choice = intent.getIntExtra("choices",0);

        try {
            startNytSpil();
        } catch (Exception e) {
            e.printStackTrace();
        }

        hiddenWord = controller.displayTheWord(ordet);

        TextView textView = (TextView) findViewById(R.id.wordDisplay);
        textView.setText(hiddenWord);
    }

    public void startNytSpil() throws Exception {
        controller.getTheWords(choice);
        brugteBogstaver.clear();
        antalForkerteBogstaver = 0;
        spilletErVundet = false;
        spilletErTabt = false;
        if (Library.posibleWords.isEmpty()) throw new IllegalStateException("Listen over mulige ord er tom!");
        ordet = Library.posibleWords.get(new Random().nextInt(Library.posibleWords.size()));
        System.out.println("Nyt spil - det skjulte ord er: "+ordet);
    }
}