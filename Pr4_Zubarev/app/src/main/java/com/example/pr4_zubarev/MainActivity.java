package com.example.pr4_zubarev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Next = findViewById(R.id.nextButton);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnterInf(Next);
            }
        });
    }

    public void EnterInf(View view){
        Student data = new Student();
        TextInputLayout name = findViewById(R.id.name);
        TextInputLayout surname = findViewById(R.id.surname);
        data.setName(name.getEditText().getText().toString());
        data.setSurname(surname.getEditText().getText().toString());
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("data", data);
        startActivity(intent);
    }
}