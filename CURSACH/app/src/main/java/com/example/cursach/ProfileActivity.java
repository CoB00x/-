package com.example.cursach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawer;
    public ActionBarDrawerToggle toggle;
    Button out;
    Button btn;
    ImageView img;
    private String profile = "Нет";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setProfile();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Профиль");
        }

        ////////////////////////////////////////////NavigationBar////////////////////////////////////////////
        drawer = findViewById(R.id.drawer_layout_profile);
        toggle = new ActionBarDrawerToggle(
                ProfileActivity.this, drawer, R.string.drawer_open, R.string.drawer_close);
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
                    startActivity(new Intent(ProfileActivity.this, AboutActivity.class));
                } else if (id == R.id.nav_author) {
                    startActivity(new Intent(ProfileActivity.this,DeveloperActivity.class));
                }  else if (id == R.id.nav_main) {
                    startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                } else if (id == R.id.nav_search) {
                    //startActivity(new Intent(MainActivity.this, SearchActivity.class));
                } else if (id == R.id.nav_cart) {
                    //startActivity(new Intent(MainActivity.this, MainActivity.class));
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        btn = findViewById(R.id.btn_profile);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent,1);
            }
        });

        out = findViewById(R.id.btn_out);
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setTitle("Выход");
                builder.setMessage("Вы действительно хотите выйти из аккаунта?");
                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String fileName = "profile_file";
                        File directory = getFilesDir();
                        File file = new File(directory, fileName);
                        try {
                            new FileOutputStream(file).close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        setProfile();
                    }
                });
                builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        profile = data.getStringExtra("profile");


        String fileName = "profile_file";
        File directory = getFilesDir();
        File file = new File(directory, fileName);
        try {
            if (file.exists()) {
                new FileOutputStream(file).close();
                FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                fos.write(profile.getBytes());
            } else {
                FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                fos.write(profile.getBytes());
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        setProfile();

        TextView name = findViewById(R.id.name_profile);
        name.setText("Профиль: " + profile);
        btn.setText("Сменить профиль");
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setProfile(){
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

        out = findViewById(R.id.btn_out);
        btn = findViewById(R.id.btn_profile);
        img = findViewById(R.id.img_profile);
        TextView name = findViewById(R.id.name_profile);
        if (profile.equals("admin")){
            img.setImageResource(R.drawable.admin);
            btn.setText("Сменить профиль");
            out.setVisibility(View.VISIBLE);
        } else if (profile.equals("user")){
            img.setImageResource(R.drawable.user);
            btn.setText("Сменить профиль");
            out.setVisibility(View.VISIBLE);
        } else {
            img.setImageResource(R.drawable.emptyuser);
            btn.setText("Войти");
            out.setVisibility(View.GONE);
            profile = "Нет";
        }
        name.setText("Профиль: " + profile);
    }
}