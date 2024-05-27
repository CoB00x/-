package com.example.pr12_zubarev;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        User user = new User(111, "John", "john@example.com");

        Gson gson = new Gson();
        String json = gson.toJson(user);

        File file = new File(getFilesDir(), "user.json");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(json.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);

            User userFromJson = gson.fromJson(isr, User.class);

            TextView textViewId = findViewById(R.id.textViewId);
            TextView textViewName = findViewById(R.id.textViewName);
            TextView textViewEmail = findViewById(R.id.textViewEmail);

            textViewId.setText(String.valueOf(userFromJson.getId()));
            textViewName.setText(userFromJson.getName());
            textViewEmail.setText(userFromJson.getEmail());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
