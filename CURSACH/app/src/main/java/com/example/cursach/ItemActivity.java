package com.example.cursach;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        DVD dvd = (DVD) getIntent().getSerializableExtra("DVD");
        TextView name = findViewById(R.id.item_name);
        TextView about = findViewById(R.id.item_about);
        ImageView imageView = findViewById(R.id.item_img);
        Button btn = findViewById(R.id.btn_to_cart);
        btn.setText("Добавить в корзину                                   " + dvd.getPrice() + "Р");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(dvd.getName());
        }

        name.setText(dvd.getName());
        about.setText(dvd.getInfo());
        imageView.setImageResource(dvd.getImg());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}