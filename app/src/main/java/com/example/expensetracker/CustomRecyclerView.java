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

import java.util.ArrayList;

public class CustomRecyclerView extends RecyclerView.Adapter<CustomRecyclerView.ViewHolder> {
    private  Object [] images;
    Context context;
    private ArrayList<String> expenseAmount;
    private ArrayList<String> expenseDate;
    private ArrayList<String> expenseType;
    private ArrayList<String> expenseCustomName;
    private static OnItemClickListener onItemClickListener;

    public  CustomRecyclerView( ArrayList<String> expenseAmount, ArrayList<String> expenseType, ArrayList<String> expenseDate, ArrayList<String> expenseCustomName, Context context){
        this.context = context;
//        this.images = images;
        this.expenseAmount = expenseAmount;
        this.expenseType = expenseType;
        this.expenseCustomName = expenseCustomName;
        this.expenseDate = expenseDate;
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
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
            itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expenses_recycler_view,parent,false);
        return new ViewHolder(view);
    }
    public void updateData(ArrayList<String> updatedExpenseAmount, ArrayList<String> updatedExpenseType, ArrayList<String> updatedExpenseDate, ArrayList<String> updatedExpenseCustomName) {
        this.expenseAmount = updatedExpenseAmount;
        this.expenseType = updatedExpenseType;
        this.expenseDate = updatedExpenseDate;
        this.expenseCustomName = updatedExpenseCustomName;
    }
    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerView.ViewHolder holder, int position) {
        String name = expenseCustomName.get(position);
        String amount = expenseAmount.get(position);
        String date = expenseDate.get(position);
        String type = expenseType.get(position);

        holder.name.setText(name);
        holder.amount.setText(amount);
        holder.date.setText(date);
        holder.date.setText(date);
        holder.type.setText(type);
        Log.d("CustomRecyclerView", "Name: " + name + ", Amount: " + amount + ", Date: " + date + ", Type: " + type);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }
    @Override
    public int getItemCount() {
        return expenseCustomName.size();
//        setOnItemClickListener();
    }

    public String getCustomNameAtPosition(int position) {
        if (position >= 0 && position < expenseCustomName.size()) {
            return expenseCustomName.get(position);
        }
        return null;
    }
    public String getDateAtPosition(int position) {
        if (position >= 0 && position < expenseCustomName.size()) {
            return expenseCustomName.get(position);
        }
        return null;
    }
    public String getAtPosition(int position) {
        if (position >= 0 && position < expenseCustomName.size()) {
            return expenseCustomName.get(position);
        }
        return null;
    }
}