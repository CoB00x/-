package com.example.pr8_zubarev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread();
                task1();
                task2();
                task3();
                thread.start();
                nextActivity();
            }
        });
    }

    public void task1(){
        Log.i("task1", "start");
        try {
            Thread.sleep(1000);
        }  catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("task1", "end");
    }

    public void task2(){
        Log.i("task2", "start");
        try {
            Thread.sleep(2000);
        }  catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("task2", "end");
    }

    public void task3(){
        Log.i("task3", "start");
        try {
            Thread.sleep(3000);
        }  catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("task3", "end");
    }

    public void nextActivity(){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}