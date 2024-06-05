package com.example.cursach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawer;
    public ActionBarDrawerToggle toggle;
    List<DVD> DVDs;
    List<String> names;


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
                } else if (id == R.id.nav_cart) {
                    startActivity(new Intent(MainActivity.this, CartActivity.class));
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        DVDs = setDVDs();
        RecyclerView recyclerView = findViewById(R.id.recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter MyAdapter = new RecyclerAdapter(DVDs, MainActivity.this);
        recyclerView.setAdapter(MyAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        ListView listView = findViewById(R.id.search_list);
        listView.setAdapter(adapter);

        MenuItem searchViewItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected_name = listView.getItemAtPosition(position).toString();
                for (int i = 0; i < names.size(); i++){
                    Log.i("qwe", DVDs.get(0).getName());
                    if (DVDs.get(i).getName().equals(selected_name)){
                        Log.i("qwe","qwe");
                        Intent intent = new Intent(MainActivity.this, ItemActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("DVD", DVDs.get(i));
                        startActivity(intent);
                    }
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (names.contains(query)) {
                    adapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listView.setVisibility(View.VISIBLE);
                RecyclerView rv = findViewById(R.id.recycler_list);
                rv.setVisibility(View.GONE);
                adapter.getFilter().filter(newText);
                if (newText.equals("")){
                    listView.setVisibility(View.GONE);
                    rv.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
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
        names = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            names.add(list.get(i).getName());
        }
        return list;
    }
}