package com.example.pr5_zubarev;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Action extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        RecyclerView recyclerView = findViewById(R.id.actionList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Game> actions = new ArrayList<>();
        actions.add(new Game("DayZ", R.drawable.dayz));
        actions.add(new Game("Deep rock galactic", R.drawable.drg));
        actions.add(new Game("Dota 2", R.drawable.dota2));
        actions.add(new Game("PUBG: BATTLEGROUNDS", R.drawable.pubg));
        actions.add(new Game("RUST", R.drawable.rust));
        actions.add(new Game("Cuphead", R.drawable.cuphead));
        actions.add(new Game("Lies of P", R.drawable.lop));
        actions.add(new Game("Noita", R.drawable.noita));
        actions.add(new Game("Path of Exile", R.drawable.poe));
        actions.add(new Game("Squad", R.drawable.squad));
        actions.add(new Game("Team fortress 2", R.drawable.tf2));
        actions.add(new Game("Terraria", R.drawable.terraria));
        actions.add(new Game("Deep rock galactic", R.drawable.drg));
        actions.add(new Game("Dota 2", R.drawable.dota2));
        actions.add(new Game("PUBG: BATTLEGROUNDS", R.drawable.pubg));
        actions.add(new Game("RUST", R.drawable.rust));
        actions.add(new Game("Cuphead", R.drawable.cuphead));
        actions.add(new Game("Lies of P", R.drawable.lop));
        actions.add(new Game("Noita", R.drawable.noita));
        actions.add(new Game("Path of Exile", R.drawable.poe));
        actions.add(new Game("Squad", R.drawable.squad));
        actions.add(new Game("Team fortress 2", R.drawable.tf2));
        actions.add(new Game("Terraria", R.drawable.terraria));
        actions.add(new Game("Deep rock galactic", R.drawable.drg));
        actions.add(new Game("Dota 2", R.drawable.dota2));
        actions.add(new Game("PUBG: BATTLEGROUNDS", R.drawable.pubg));
        actions.add(new Game("RUST", R.drawable.rust));
        actions.add(new Game("Cuphead", R.drawable.cuphead));
        actions.add(new Game("Lies of P", R.drawable.lop));
        actions.add(new Game("Noita", R.drawable.noita));
        actions.add(new Game("Path of Exile", R.drawable.poe));
        actions.add(new Game("Squad", R.drawable.squad));
        actions.add(new Game("Team fortress 2", R.drawable.tf2));
        actions.add(new Game("Terraria", R.drawable.terraria));
        actions.add(new Game("Deep rock galactic", R.drawable.drg));
        actions.add(new Game("Dota 2", R.drawable.dota2));
        actions.add(new Game("PUBG: BATTLEGROUNDS", R.drawable.pubg));
        actions.add(new Game("RUST", R.drawable.rust));
        actions.add(new Game("Cuphead", R.drawable.cuphead));
        actions.add(new Game("Lies of P", R.drawable.lop));
        actions.add(new Game("Noita", R.drawable.noita));
        actions.add(new Game("Path of Exile", R.drawable.poe));
        actions.add(new Game("Squad", R.drawable.squad));
        actions.add(new Game("Team fortress 2", R.drawable.tf2));
        actions.add(new Game("Terraria", R.drawable.terraria));
        actions.add(new Game("Deep rock galactic", R.drawable.drg));
        actions.add(new Game("Dota 2", R.drawable.dota2));
        actions.add(new Game("PUBG: BATTLEGROUNDS", R.drawable.pubg));
        actions.add(new Game("RUST", R.drawable.rust));
        actions.add(new Game("Cuphead", R.drawable.cuphead));
        actions.add(new Game("Lies of P", R.drawable.lop));
        actions.add(new Game("Noita", R.drawable.noita));
        actions.add(new Game("Path of Exile", R.drawable.poe));
        actions.add(new Game("Squad", R.drawable.squad));
        actions.add(new Game("Team fortress 2", R.drawable.tf2));
        actions.add(new Game("Terraria", R.drawable.terraria));

        SimpleAdapter adapter = new SimpleAdapter(actions);
        recyclerView.setAdapter(adapter);


    }
}