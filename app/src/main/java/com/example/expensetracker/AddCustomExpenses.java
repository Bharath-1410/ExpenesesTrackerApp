package com.example.expensetracker;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AddCustomExpenses extends Activity {
    EditText newCustomName,newAmount,newDate,newNote;
    TextView addTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenses_add_floaing_button);

        newCustomName = findViewById(R.id.newCustomName);
        newAmount = findViewById(R.id.newAmount);
        newDate = findViewById(R.id.newDate);
        newNote= findViewById(R.id.newNote);
        addTransaction = findViewById(R.id.addNewTransaction);

        // Dynamically + Statically Creating And Creating the Tags
        ArrayList<String> defaultTypes = new ArrayList<>();
        defaultTypes.add("Income");
        defaultTypes.add("Expense");

        ArrayList<String> defaultTags = new ArrayList<>();
        defaultTags.add("Entertainment");
        defaultTags.add("Food");
        defaultTags.add("Traveling");

        Spinner transactionType = findViewById(R.id.transactionType);
        CustomDropDown type = new CustomDropDown(this, defaultTypes);
        type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        transactionType.setAdapter(type);

        Spinner inputTag = findViewById(R.id.inputTag);
        CustomDropDown tag = new CustomDropDown(this, defaultTags);
        tag.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputTag.setAdapter(tag);

        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
