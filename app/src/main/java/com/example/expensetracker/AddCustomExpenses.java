package com.example.expensetracker;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AddCustomExpenses extends Activity {
    EditText newCustomName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenses_add_floaing_button);

        AutoCompleteTextView inputTag = findViewById(R.id.inputTag);
        AutoCompleteTextView transactionType = findViewById(R.id.transactionType);
        TextInputLayout inputTagLayer = findViewById(R.id.inputTaglayer);
        TextInputLayout inputType = findViewById(R.id.inputTypeLayer);

        newCustomName = findViewById(R.id.newCustomName);

        // Dynamically + Statically Creating And Creating the Tags
        ArrayList<String> defaultTags = new ArrayList<>();
        defaultTags.add("Entertainment");
        defaultTags.add("Food");

        ArrayList<String> defaultTypes = new ArrayList<>();
        defaultTypes.add("Income");
        defaultTypes.add("Expense");

//        CustomDropDown expenseTag = new CustomDropDown(this, defaultTags);
//        CustomDropDown expenseType = new CustomDropDown(this, defaultTypes);
        ArrayAdapter<String> expenseTag = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                defaultTypes
        );
        Log.d("AddCustomExpenses", "AdapterWorking");
        // Set adapters after populating ArrayLists
        inputTag.setAdapter(expenseTag);
        // transactionType.setAdapter(expenseType);
    }
}
