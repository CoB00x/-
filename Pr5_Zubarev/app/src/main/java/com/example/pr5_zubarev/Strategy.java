package com.example.pr5_zubarev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Strategy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy);

        Spinner spinner = findViewById(R.id.spinner);
        String [] games = {"Dota 2", "RimWorld", "Stellaris", "Crusader Kings 3", "Hearts of Iron 4"};

        ArrayAdapter <String> adapter = new ArrayAdapter
                (this, android.R.layout.simple_spinner_item, games);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
    }
}