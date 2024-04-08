package com.example.pr4_zubarev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        Button OKbutton = findViewById(R.id.button);
        OKbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OK(OKbutton);
            }
        });
    }

    public void OK(View view){
        Intent intent = new Intent();
        EditText date = findViewById(R.id.editTextDate);
        EditText time = findViewById(R.id.editTextTime);
        intent.putExtra(Activity2.DATE_MESSAGE, date.getText().toString());
        intent.putExtra(Activity2.TIME_MESSAGE, time.getText().toString());
        setResult(RESULT_OK, intent);
        Toast.makeText(getApplicationContext(), "Information sent successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}