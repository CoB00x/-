package com.example.cursach;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.Objects;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<DVD> items;
    private Context context;

    public CartAdapter(List<DVD> items, Context context){
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.list_item, parent, false);
        return new CartAdapter.ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder
                                         holder, int position) {
        DVD item = items.get(position);
        holder.name.setText(item.getName());
        holder.about.setText(item.getInfo());
        holder.imageView.setImageResource(item.getImg());
        holder.dvd = item;
        holder.trash.setVisibility(View.VISIBLE);
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView about;
        ImageView imageView;
        DVD dvd;
        String profile;
        ImageView trash;
        ViewHolder(View view, Context context) {
            super(view);

            name = view.findViewById(R.id.item_name);
            about = view.findViewById(R.id.item_about);
            imageView = view.findViewById(R.id.item_image);
            trash = view.findViewById(R.id.ic_trash);
            view.findViewById(R.id.banner).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ItemActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("DVD", dvd);
                    context.startActivity(intent);
                }
            });

            trash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String fileName = "profile_file";
                    File directory = context.getFilesDir();
                    File file = new File(directory, fileName);

                    if (file.exists()) {
                        try {
                            FileInputStream fis = context.openFileInput(fileName);
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
                    file = new File(context.getFilesDir(), profile+".json");
                    List<DVD> dvds;

                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream(file);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    InputStreamReader isr = new InputStreamReader(fis);
                    Type listType = new TypeToken<ArrayList<DVD>>() {}.getType();
                    dvds = new Gson().fromJson(isr, listType);

                    for (int i = 0; i < dvds.size(); i++){
                        if (Objects.equals(dvds.get(i).getName(), dvd.getName())){
                            dvds.remove(i);
                        }
                    }

                    String json = gson.toJson(dvds);
                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        fos.write(json.getBytes());
                        fos.close();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    context.startActivity(new Intent(context, CartActivity.class));
                }
            });
        }
    }
}
