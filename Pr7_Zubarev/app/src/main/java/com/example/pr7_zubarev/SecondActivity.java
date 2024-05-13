package com.example.pr7_zubarev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle arguments = getIntent().getExtras();
        TextView time = findViewById(R.id.time);
        TextView date = findViewById(R.id.date);
        time.setText(arguments.get("time").toString());
        date.setText(arguments.get("date").toString());
    }
}