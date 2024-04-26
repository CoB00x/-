package com.example.mobile6;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Animal extends AppCompatActivity {

    public static final String EXTRA_ANIMAL = "extra_animal";
    public static final String EXTRA_IMAGE = "extra_image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        Intent intent = getIntent();
        String animal = intent.getStringExtra(EXTRA_ANIMAL);
        int imageResource = intent.getIntExtra(EXTRA_IMAGE, 0);

        TextView textView = findViewById(R.id.text_view_animal);
        ImageView imageView = findViewById(R.id.image_view_animal);

        textView.setText(animal);
        imageView.setImageResource(imageResource);
    }
}
