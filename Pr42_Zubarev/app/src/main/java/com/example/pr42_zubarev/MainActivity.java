package com.example.pr42_zubarev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*        FragmentManager fragmentManager = getSupportFragmentManager();
        DynamicFragment fragment1 = new DynamicFragment();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_static, fragment1, "fragment1");
        fragmentTransaction.commit();*/
        Button dButton = findViewById(R.id.toDynamic);
        dButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDynamic(dButton);
            }
        });

        Button fcvButton = findViewById(R.id.toFCV);
        fcvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toFCV(fcvButton);
            }
        });
    }

    public void toDynamic(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        DynamicFragment fragment1 = new DynamicFragment();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_static, fragment1, "fragment1");
        fragmentTransaction.commit();
    }

    public void toFCV(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FCVFragment fragment1 = new FCVFragment();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_static, fragment1, "fragment1");
        fragmentTransaction.commit();
    }
}