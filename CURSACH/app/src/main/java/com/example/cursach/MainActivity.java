package com.example.cursach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawer;
    public ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Главная");
        }

        ////////////////////////////////////////////NavigationBar////////////////////////////////////////////
        drawer = findViewById(R.id.drawer_layout_main);
        toggle = new ActionBarDrawerToggle(
                MainActivity.this, drawer, R.string.drawer_open, R.string.drawer_close);
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
                if (id == R.id.nav_change_profile) {
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                } else if (id == R.id.nav_about) {
                    startActivity(new Intent(MainActivity.this, AboutActivity.class));
                } else if (id == R.id.nav_author) {
                    startActivity(new Intent(MainActivity.this, DeveloperActivity.class));
                }else if (id == R.id.nav_main) {
                    //startActivity(new Intent(MainActivity.this, SearchActivity.class));
                } else if (id == R.id.nav_search) {
                    //startActivity(new Intent(MainActivity.this, SearchActivity.class));
                } else if (id == R.id.nav_cart) {
                    //startActivity(new Intent(MainActivity.this, MainActivity.class));
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        List<DVD> DVDs = setDVDs();
        RecyclerView recyclerView = findViewById(R.id.recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter adapter = new RecyclerAdapter(DVDs, MainActivity.this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public List<DVD> setDVDs(){
        List<DVD> list = new ArrayList<>();
        list.add(new DVD("Аладин", R.drawable.aladin, getResources().getString(R.string.aladin), 300));
        list.add(new DVD("Ледниковый период", R.drawable.iceage, getResources().getString(R.string.iceage), 350));
        list.add(new DVD("Сезон охоты", R.drawable.season, getResources().getString(R.string.season), 299));
        list.add(new DVD("Тачки", R.drawable.cars, getResources().getString(R.string.cars), 279));
        list.add(new DVD("Хлодное сердце", R.drawable.frozen, getResources().getString(R.string.frozen), 321));
        list.add(new DVD("Шрек", R.drawable.shrek, getResources().getString(R.string.shrek), 333));
        return list;
    }
}