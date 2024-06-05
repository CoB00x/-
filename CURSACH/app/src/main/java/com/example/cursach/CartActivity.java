package com.example.cursach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
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

public class CartActivity extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawer;
    public ActionBarDrawerToggle toggle;
    int total = 0;
    List<DVD> DVDs;
    String profile;
    TextView totalTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Корзина");
        }

        ////////////////////////////////////////////NavigationBar////////////////////////////////////////////
        drawer = findViewById(R.id.drawer_layout_cart);
        toggle = new ActionBarDrawerToggle(
                CartActivity.this, drawer, R.string.drawer_open, R.string.drawer_close);
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_about) {
                    startActivity(new Intent(CartActivity.this, AboutActivity.class));
                } else if (id == R.id.nav_author) {
                    startActivity(new Intent(CartActivity.this,DeveloperActivity.class));
                }  else if (id == R.id.nav_main) {
                    startActivity(new Intent(CartActivity.this, MainActivity.class));
                }  else if (id == R.id.nav_change_profile) {
                    startActivity(new Intent(CartActivity.this, ProfileActivity.class));
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        try {
            DVDs = getCart();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (DVDs != null) {
            RecyclerView recyclerView = findViewById(R.id.recycler_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            CartAdapter MyAdapter = new CartAdapter(DVDs, CartActivity.this);
            recyclerView.setAdapter(MyAdapter);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public List<DVD> getCart() throws FileNotFoundException {
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
        totalTV = findViewById(R.id.total);

        Gson gson = new Gson();
        file = new File(getFilesDir(), profile+".json");
        if (file.length() == 0 || !file.exists()) {

            totalTV.setText("Корзина пуста");
        }else {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            Type listType = new TypeToken<ArrayList<DVD>>() {
            }.getType();
            DVDs = new Gson().fromJson(isr, listType);

            for (int i=0; i<DVDs.size(); i++){
                total += DVDs.get(i).getPrice();
            }

            totalTV.setText("Итого: " + total + "Р");
            return DVDs;
        }
        return null;
    }

    /*public void delete() throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File(getFilesDir(), profile+".json");
        List<DVD> dvds;

        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        Type listType = new TypeToken<ArrayList<DVD>>() {}.getType();
        dvds = new Gson().fromJson(isr, listType);



        String json = gson.toJson(dvds);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(json.getBytes());
            fos.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }*/
}