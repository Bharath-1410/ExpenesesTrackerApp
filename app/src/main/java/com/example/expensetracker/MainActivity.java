package com.example.expensetracker;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView expenseRecyclerView;
    private final ActivityResultLauncher<Intent> startForResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null) {
                                String expenseData = data.getStringExtra("expenseKey");
                                Log.d("MainActivity", "Expense Data Received: " + expenseData);
                                Toast.makeText(MainActivity.this, "Data Received " + expenseData, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

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
                Intent intent = new Intent(MainActivity.this, ExpensesDetails.class);
                String customName = customRecyclerView.getCustomNameAtPosition(position);
                intent.putExtra("customName", customName);
                startActivity(intent);
            }
        });

        // Creating New Fragment For Adding New Expenses
        FloatingActionButton fab = findViewById(R.id.addExpenses);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddCustomExpenses.class);
            Log.d("MainActivity", "FloatingActionButton clicked");
            startActivity(intent);
        });
    }

    // Receiving Data Of New Expenses
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 464 && resultCode == RESULT_OK && data != null) {
            String expenseData = data.getStringExtra("expenseKey");
            Toast.makeText(this, "Data Received " + expenseData, Toast.LENGTH_SHORT).show();
        }

    }

    // Method To Handle Dashboard Updation
    private void updateOptions(AutoCompleteTextView autoCompleteTextView){
        ArrayList<String> options= new ArrayList<>();
        options.add("Dashboard");
        options.add("Savings");
        options.add("Expenses");
        options.remove(autoCompleteTextView.getText().toString());
        CustomDropDown customAdapter = new CustomDropDown(this, options);
        autoCompleteTextView.setAdapter(customAdapter);
    }
}