package com.example.Pr6_Zubarev;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.capibara) {
            Intent intent = new Intent(this, Animal.class);
            intent.putExtra(Animal.EXTRA_ANIMAL, "Капибара");
            intent.putExtra(Animal.EXTRA_IMAGE, R.drawable.capibara);
            startActivity(intent);
        } else if (id == R.id.enot) {
            Intent intent = new Intent(this, Animal.class);
            intent.putExtra(Animal.EXTRA_ANIMAL, "Енот");
            intent.putExtra(Animal.EXTRA_IMAGE, R.drawable.enot);
            startActivity(intent);
        } else if (id == R.id.ezhik) {
            Intent intent = new Intent(this, Animal.class);
            intent.putExtra(Animal.EXTRA_ANIMAL, "Ёжик");
            intent.putExtra(Animal.EXTRA_IMAGE, R.drawable.ezhik);
            startActivity(intent);
        } else if (id == R.id.nav_second_activity) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
