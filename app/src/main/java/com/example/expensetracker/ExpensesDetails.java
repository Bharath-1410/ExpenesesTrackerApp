package com.example.expensetracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExpensesDetails extends AppCompatActivity {
    EditText expenseAmount,expenseType,expenseDate,expenseCustomName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenses_detail_layout);
        String customName = getIntent().getStringExtra("selectedItem");
        expenseCustomName = findViewById(R.id.expensesName);
        expenseCustomName.setText(customName);
    }
}
