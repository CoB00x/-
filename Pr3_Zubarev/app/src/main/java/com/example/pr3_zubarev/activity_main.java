package com.example.pr3_zubarev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class activity_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constrain);

        if (getIntent().getExtras() != null) {
            data d = (data) getIntent().getSerializableExtra("message");
            TextInputLayout t = findViewById(R.id.textInputLayout);
            if (!Objects.equals(d.getMessage(), "")) {
                t.setHint(d.getMessage());
            }
        }

        Button linearButton = findViewById(R.id.toLinear);
        linearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLinear(linearButton);
            }
        });

        Button frameButton = findViewById(R.id.toFrame);
        frameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toFrame(frameButton);
            }
        });

        Button relativeButton = findViewById(R.id.toRelative);
        relativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRelative(relativeButton);
            }
        });

        Button backButton = findViewById(R.id.Cback);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {onBackActivity(backButton);}
        });
    }

    public void toLinear(View view){
        Intent intent = new Intent(this, linear.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        TextInputLayout t = findViewById(R.id.textInputLayout);
        data d = new data(t.getEditText().getText().toString());
        intent.putExtra("message", d);
        startActivity(intent);
    }
    public void toFrame(View view){
        Intent intent = new Intent(this, frame.class);
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