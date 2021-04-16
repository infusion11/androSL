package com.example.shoplist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {
    private Context context;
    private ArrayList item_id, item_name, item_amount, item_price;
    ItemsAdapter(Context context, ArrayList item_id, ArrayList item_name, ArrayList item_amount, ArrayList item_price){
        this.context=context;
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_amount = item_amount;
        this.item_price = item_price;
    }
    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycleviewrow, parent, false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        holder.item_id.setText(String.valueOf(item_id.get(position)));
        holder.item_name.setText(String.valueOf(item_name.get(position)));
        holder.item_amount.setText(String.valueOf(item_amount.get(position)));
        holder.item_price.setText(String.valueOf(item_price.get(position)));

    }

    @Override
    public int getItemCount() {
        return item_id.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder{
        TextView item_id, item_name, item_amount, item_price;
        LinearLayout mainLayout;
        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            item_id = itemView.findViewById(R.id.item_id);
            item_name = itemView.findViewById(R.id.item_name);
            item_amount = itemView.findViewById(R.id.item_amount);
            item_price = itemView.findViewById(R.id.item_price);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
