package com.example.cursach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class DeveloperActivity extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawer;
    public ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("О разработчике");
        }

        ////////////////////////////////////////////NavigationBar////////////////////////////////////////////
        drawer = findViewById(R.id.drawer_layout_developer);
        toggle = new ActionBarDrawerToggle(
                DeveloperActivity.this, drawer, R.string.drawer_open, R.string.drawer_close);
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
                    startActivity(new Intent(DeveloperActivity.this, ProfileActivity.class));
                } else if (id == R.id.nav_about) {
                    startActivity(new Intent(DeveloperActivity.this, AboutActivity.class));
                }  else if (id == R.id.nav_main) {
                    startActivity(new Intent(DeveloperActivity.this, MainActivity.class));
                }  else if (id == R.id.nav_cart) {
                    startActivity(new Intent(DeveloperActivity.this, CartActivity.class));
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}