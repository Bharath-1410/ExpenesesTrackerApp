package com.example.expensetracker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomRecylcerView extends RecyclerView.Adapter<CustomRecylcerView.ViewHolder> {
    private  Object [] images;
    Context context;
    private String [] expenseAmount,expenseType,expenseDate,expenseCustomName;
    public  CustomRecylcerView( String [] expenseAmount, String [] expenseType, String [] expenseDate, String [] expenseCustomName, Context context){
        this.context = context;
//        this.images = images;
        this.expenseAmount = expenseAmount;
        this.expenseType = expenseType;
        this.expenseCustomName = expenseCustomName;
        this.expenseDate = expenseDate;
    }
    public static  class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView name;
        public TextView type;
        public TextView amount;
        public TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.expenseImageType);
            name = itemView.findViewById(R.id.expenseCustomName);
            type = itemView.findViewById(R.id.expenseType);
            date = itemView.findViewById(R.id.expenseDate);
            amount = itemView.findViewById(R.id.expenseAmount);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expenses_recycler_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecylcerView.ViewHolder holder, int position) {
        String name = expenseCustomName[position];
        String amount = expenseAmount[position];
        String date = expenseDate[position];
        String type = expenseType[position];
        holder.name.setText(name);
        holder.amount.setText(amount);
        holder.date.setText(date);
        holder.date.setText(date);
        holder.type.setText(type);
        Log.d("CustomRecyclerView", "Name: " + name + ", Amount: " + amount + ", Date: " + date + ", Type: " + type);
    }

    @Override
    public int getItemCount() {
        return expenseCustomName.length;
    }
}
