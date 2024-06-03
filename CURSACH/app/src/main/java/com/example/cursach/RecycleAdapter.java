package com.example.cursach;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {
    private List<DVD> items;
    public SimpleAdapter(List<DVD> items){
        this.items = items;
    }

    @NonNull
    @Override
    public SimpleAdapter.ViewHolder onCreateViewHolder(@NonNull
                                                       ViewGroup parent, int viewType) {
        Log.i("qwe","qweqweq");
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleAdapter.ViewHolder
                                         holder, int position) {
        Game item = items.get(position);
        holder.textView.setText(item.getName());
        holder.imageView.setImageResource(item.getIcon());
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.item_text);
            imageView = view.findViewById(R.id.item_icon);
        }
    }
}
