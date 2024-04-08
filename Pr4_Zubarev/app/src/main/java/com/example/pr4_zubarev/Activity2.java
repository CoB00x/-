package com.example.pr4_zubarev;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Student data = (Student)getIntent().getSerializableExtra("data");
        TextView surname = (TextView)findViewById(R.id.surnameText);
        surname.setText(data.getSurname());
        TextView name = findViewById(R.id.nameText);
        name.setText(data.getName());

        Button Enter = findViewById(R.id.enterButton);
        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextActivity(Enter);
            }
        });
    }

    static final String DATE_MESSAGE = "00.00.00";
    static final String TIME_MESSAGE = "00:00";
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            TextView dateText = findViewById(R.id.dateText);
                            TextView timeText = findViewById(R.id.timeText);
                            if (result.getResultCode() == Activity.RESULT_OK){
                                Intent intent = result.getData();
                                String date = intent.getStringExtra(DATE_MESSAGE);
                                String time = intent.getStringExtra(TIME_MESSAGE);
                                dateText.setText(date);
                                timeText.setText(time);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

    public void onNextActivity(View view){
        Intent intent = new Intent(this, Activity3.class);
        mStartForResult.launch(intent);
    }
}