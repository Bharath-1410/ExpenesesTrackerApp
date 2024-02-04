package com.example.expensetracker;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView expenseRecyclerView;
    CustomRecyclerView customRecyclerView;
    DBHelper dbHelper;
    private ArrayList<String> expenseAmount;
    private ArrayList<String> expenseDate;
    private ArrayList<String> expenseType;
    private ArrayList<String> expenseCustomName;
    ArrayList<String> dashboardOptions= new ArrayList<>();
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

        dbHelper = new DBHelper(getApplicationContext());

        // DashBoard Updation
        Spinner dashboard = findViewById(R.id.dashBoard);
        dashboardOptions.add("Dashboard");
        dashboardOptions.add("Savings");
        dashboardOptions.add("Expenses");
        CustomDropDown dashBoardDropDown = new CustomDropDown(this, dashboardOptions);
        dashBoardDropDown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dashboard.setAdapter(dashBoardDropDown);


        // Creating New Fragment For Adding New Expenses
        FloatingActionButton fab = findViewById(R.id.addExpenses);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddCustomExpenses.class);
            Log.d("MainActivity", "FloatingActionButton clicked");
            startActivity(intent);
        });
//        expenseAmount.add("6000");
//        expenseType.add("Entertainment");
//        expenseDate.add("june 4");
//        expenseCustomName.add("Cash Back");
        try {
//            expenseRecyclerView = findViewById(R.id.expenseRecyclerView);
//            customRecyclerView = new CustomRecyclerView(expenseAmount,expenseType,expenseDate,expenseCustomName,getApplicationContext());
//            expenseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//            expenseRecyclerView.setAdapter(customRecyclerView);
        }catch (Exception e){
            Log.e("MainActivity", "onCreate: "+e.toString() );
        }
//        updateRecyclerViewData();
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
    private void updateRecyclerViewData() {
        // Fetch records from the database
        try {

        ArrayList<String> updatedExpenseCustomName = new ArrayList<>();
        ArrayList<String> updatedExpenseDate = new ArrayList<>();
        ArrayList<String> updatedExpenseAmount = new ArrayList<>();
        ArrayList<String> updatedExpenseTag = new ArrayList<>();

        DBHelper dbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String[] projection = {"name","amount","date","tag"};

        Cursor cursor = database.query(
                "transactions",
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            String customName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String amount = cursor.getString(cursor.getColumnIndexOrThrow("amount"));
            String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
            String tag = cursor.getString(cursor.getColumnIndexOrThrow("tag"));
            updatedExpenseCustomName.add(customName);
            updatedExpenseAmount.add(amount);
            updatedExpenseDate.add(date);
            updatedExpenseTag.add(tag);
        }

        cursor.close();
        dbHelper.close();
        customRecyclerView = new CustomRecyclerView(expenseAmount,expenseType,expenseDate,expenseCustomName,getApplicationContext());
//        customRecyclerView.updateData(updatedExpenseAmount, updatedExpenseTag, updatedExpenseDate, updatedExpenseCustomName);
//        customRecyclerView.notifyDataSetChanged();
        expenseRecyclerView.setAdapter(customRecyclerView);
        Log.d("MainActivity", "updateRecyclerViewData: CustomRecyclerView is Getting updated");
        }catch (Exception e){
            Log.e("MainActivity", "updateRecyclerViewData: "+e.toString());
        }
    }

}