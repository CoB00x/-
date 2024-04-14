package com.example.pr2_zubarev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("OnCreate", "Created");

        Button button = findViewById(R.id.LNext);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextActivity(button);
            }
        });
    }

    public void NextActivity(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        TextInputLayout TIL = findViewById(R.id.input);
        intent.putExtra("msg", TIL.getEditText().getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("OnStart", "Started");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("OnStop", "Stopped");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("OnResume", "Resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("OnPause", "Paused");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("OnDestroy", "Destroyed");
    }
}