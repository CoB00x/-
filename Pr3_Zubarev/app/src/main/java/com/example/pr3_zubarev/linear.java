package com.example.pr3_zubarev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class linear extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);

        data d = (data)getIntent().getSerializableExtra("message");
        TextInputLayout t = findViewById(R.id.textInputLayout);
        if (!Objects.equals(d.getMessage(), "")) {
            t.setHint(d.getMessage());
        }

        Button constrainButton = findViewById(R.id.LtoConstrain);
        constrainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toConstrain(constrainButton);
            }
        });

        Button frameButton = findViewById(R.id.LtoFrame);
        frameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toFrame(frameButton);
            }
        });

        Button relativeButton = findViewById(R.id.LtoRelative);
        relativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRelative(relativeButton);
            }
        });

        Button backButton = findViewById(R.id.Lback);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {onBackActivity(backButton);}
        });
    }

    public void toFrame(View view){
        Intent intent = new Intent(this, frame.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        TextInputLayout t = findViewById(R.id.textInputLayout);
        data d = new data(t.getEditText().getText().toString());
        intent.putExtra("message", d);
        startActivity(intent);
    }
    public void toConstrain(View view){
        Intent intent = new Intent(this, activity_main.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        TextInputLayout t = findViewById(R.id.textInputLayout);
        data d = new data(t.getEditText().getText().toString());
        intent.putExtra("message", d);
        startActivity(intent);
    }
    public void toRelative(View view){
        Intent intent = new Intent(this, relative.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        TextInputLayout t = findViewById(R.id.textInputLayout);
        data d = new data(t.getEditText().getText().toString());
        intent.putExtra("message", d);
        startActivity(intent);
    }
    public void onBackActivity(View view){
        finish();
    }
}