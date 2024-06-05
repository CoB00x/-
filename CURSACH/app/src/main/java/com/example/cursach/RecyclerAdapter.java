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

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<DVD> items;
    private Context context;

    public RecyclerAdapter(List<DVD> items, Context context){
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull
                                                       ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.list_item, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder
                                         holder, int position) {
        DVD item = items.get(position);
        holder.name.setText(item.getName());
        holder.about.setText(item.getInfo());
        holder.imageView.setImageResource(item.getImg());
        holder.dvd = item;
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
        ViewHolder(View view, Context context) {
            super(view);

            name = view.findViewById(R.id.item_name);
            about = view.findViewById(R.id.item_about);
            imageView = view.findViewById(R.id.item_image);
            view.findViewById(R.id.banner).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ItemActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("DVD", dvd);
                    context.startActivity(intent);
                }
            });

        }
    }
}
