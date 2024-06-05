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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends AppCompatActivity {
    String profile;

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

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addToCart(dvd);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

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

    public void addToCart(DVD dvd) throws FileNotFoundException {
        String fileName = "profile_file";
        File directory = getFilesDir();
        File file = new File(directory, fileName);

        if (file.exists()) {
            try {
                FileInputStream fis = openFileInput(fileName);
                byte[] bytes = new byte[fis.available()];
                fis.read(bytes);
                fis.close();
                String fileContent = new String(bytes);
                profile = fileContent;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        Gson gson = new Gson();
        file = new File(getFilesDir(), profile+".json");
        List<DVD> dvds;
        if (file.length() == 0 || !file.exists()) {
            dvds = new ArrayList<>();
            dvds.add(dvd);
        } else {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            Type listType = new TypeToken<ArrayList<DVD>>() {
            }.getType();
            dvds = new Gson().fromJson(isr, listType);
            dvds.add(dvd);
        }
        String json = gson.toJson(dvds);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(json.getBytes());
            fos.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}