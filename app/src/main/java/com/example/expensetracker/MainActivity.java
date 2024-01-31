package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

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
        CustomRecylcerView r = new CustomRecylcerView(expenseAmount,expenseType,expenseDate,expenseCustomName,getApplicationContext());
        expenseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        expenseRecyclerView.setAdapter(r);

    }

    private void updateOptions(AutoCompleteTextView autoCompleteTextView){
        //  Method To Handle Dashboard Updation
        ArrayList<String> options= new ArrayList<>();
        options.add("Dashboard");
        options.add("Savings");
        options.add("Expenses");
        options.remove(autoCompleteTextView.getText().toString());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, options);
        autoCompleteTextView.setAdapter(adapter);
    }
}