package com.example.pr5_zubarev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String [] LGenres = {"Экшен","Ролевые", "Стратегия"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView VGenres = findViewById(R.id.mainList);
        ArrayAdapter<String> genresAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, LGenres);
        VGenres.setAdapter(genresAdapter);

        VGenres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (LGenres[position]) {
                    case "Экшен":
                        nextActivity(Action.class);
                        break;
                    case "Ролевые":
                        nextActivity(Roleplay.class);
                        break;
                    case "Стратегия":
                        nextActivity(Strategy.class);
                        break;
                }
            }
        });
    }

    public void nextActivity(Class cl){
        Intent intent = new Intent(this, cl);
        startActivity(intent);
    }
}