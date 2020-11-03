package com.example.galgeleg.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.galgeleg.GalgeController;
import com.example.galgeleg.MyHighScoreAdapter;
import com.example.galgeleg.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class HighScore extends AppCompatActivity implements View.OnClickListener {

    int numberOfTries;
    String playerName;
    ArrayList<String> highScoreList = new ArrayList<>();
    Button back;
    int theActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_high_score );

        loadList();

        Intent intent = getIntent();

        numberOfTries = intent.getIntExtra("NumberOfTries",0);
        playerName = intent.getStringExtra("PlayerName");

        theActivity = intent.getIntExtra("mainActivity",1);

        insertIntoList(playerName,numberOfTries);

        Collections.sort(highScoreList);

        saveList();

        back = findViewById(R.id.backButton);

        back.setOnClickListener(this);

    }

    public void saveList(){
        SharedPreferences sharedPref = getSharedPreferences("Shared Pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(highScoreList);
        editor.putString("MyList",json);
        editor.apply();
    }

    public void loadList(){
        SharedPreferences sharedPref = getSharedPreferences("Shared Pref",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString("MyList",null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        highScoreList = gson.fromJson(json,type);

        if(highScoreList == null){
            highScoreList = new ArrayList<>();
        }
    }

    public void insertIntoList(String playerName, int playerScore){
        String data = playerScore + ", " + playerName;

        highScoreList.add(data);

        initRecyclerView();
    }

    public void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        MyHighScoreAdapter adapter = new MyHighScoreAdapter(highScoreList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {

        if(theActivity == 0){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this,Player_has_won.class);
            startActivity(intent);
        }
    }
}