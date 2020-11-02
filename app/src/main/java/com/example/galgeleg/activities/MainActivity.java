package com.example.galgeleg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.galgeleg.R;
import com.example.galgeleg.activities.GalgelegGame;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button submitButton;
    Spinner dropdown;
    Intent intent;
    String playerName;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        submitButton = (Button)findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

        editText = findViewById(R.id.personName);


        String[] choices = {"Standart Ord","Ord Fra DR", "Lette ord Fra regneark", "Svære ord fra regneark", "Bogstavsord"};
        dropdown = findViewById(R.id.dropDown);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,choices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {

        playerName = editText.getText().toString();

        intent = new Intent(getApplicationContext(), GalgelegGame.class);
        try{
            intent.putExtra("choices",(dropdown.getSelectedItemPosition()));
            intent.putExtra("PlayerName",playerName);
            startActivity(intent);
        } catch (ActivityNotFoundException e){
            e.printStackTrace();
        }
    }
}