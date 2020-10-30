package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button submitButton;
    Spinner dropdown;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = (Button)findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

        String[] choices = {"Standart Ord","Ord Fra DR", "Lette ord Fra regneark", "Svære ord fra regneark", "Bogstavsord"};
        dropdown = findViewById(R.id.dropDown);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,choices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        setContentView(R.layout.activity_galgeleg_game);

        intent = new Intent(getApplicationContext(), GalgelegGame.class);
        try{
            intent.putExtra("choices",(dropdown.getSelectedItemPosition()));
            startActivity(intent);
        } catch (ActivityNotFoundException e){
            e.printStackTrace();
        }
    }
}