package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView expenseRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        TextInputLayout textInputLayout = findViewById(R.id.textinputlayer);
        String [] expenseType = new String[20];
        String [] expenseAmount = new String[20];
        String [] expenseDate = new String[20];
        String [] expenseCustomName = new String[20];
        for (int i = 0; i < 20; i++) {
            expenseType[i] = "Type " + (i+1);
            expenseAmount[i] = "+"+(i+6.34);
            expenseDate[i] = "May 30, 2024";
            expenseCustomName[i] = "CustomName "+(i+1);
        }


        // Updating DashBoard
        updateOptions(autoCompleteTextView);
        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            updateOptions(autoCompleteTextView);
            String selectedOption = (String) parent.getItemAtPosition(position);
            textInputLayout.setError(null);
        });

        // Setting The Recycler View
        expenseRecyclerView = findViewById(R.id.expenseRecyclerView);
        CustomRecyclerView customRecyclerView = new CustomRecyclerView(expenseAmount,expenseType,expenseDate,expenseCustomName,getApplicationContext());
        expenseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        expenseRecyclerView.setAdapter(customRecyclerView);

        customRecyclerView.setOnItemClickListener(new CustomRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this,ExpensesDetails.class);
                String currentItem = customRecyclerView.getItemAtPosition(position);
            }
        });
        customRecyclerView.setOnItemClickListener(new CustomRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, ExpensesDetails.class);
                String currentItem = customRecyclerView.getItemAtPosition(position);
                // Add any additional data you need to pass to the next activity
                intent.putExtra("selectedItem", currentItem);
                startActivity(intent);
            }
        });





    }

    private void updateOptions(AutoCompleteTextView autoCompleteTextView){
        //  Method To Handle Dashboard Updation
        ArrayList<String> options= new ArrayList<>();
        options.add("Dashboard");
        options.add("Savings");
        options.add("Expenses");
        options.remove(autoCompleteTextView.getText().toString());
        CustomDropDown customAdapter = new CustomDropDown(this, options);
        autoCompleteTextView.setAdapter(customAdapter);
    }
}