package com.example.pr2_zubarev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView tv = findViewById(R.id.textView);
        tv.setTextSize(26);

        Bundle args = getIntent().getExtras();
        if(args!=null){
            String msg = args.get("msg").toString();
            tv.setText(msg);
        }
    }
}