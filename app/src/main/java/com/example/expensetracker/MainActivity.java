package com.example.expensetracker;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> dashboardOptions= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // DashBoard Updation
        Spinner dashboard = findViewById(R.id.dashBoard);
        dashboardOptions.add("Dashboard");
        dashboardOptions.add("Savings");
        dashboardOptions.add("Expenses");

        CustomDropDown dashBoardDropDown = new CustomDropDown(this, dashboardOptions);
        dashBoardDropDown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dashboard.setAdapter(dashBoardDropDown);
        Log.d("ExpenseTracker", "Default Dashboard Fragment is Show Successfully");
        FragmentManager fragmentManager = getSupportFragmentManager();
        try {
            dashboard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItem = dashboardOptions.get(position);
                    if (selectedItem.equals("Dashboard")) {
                        fragmentManager.beginTransaction().
                                replace(R.id.fragmentContainerView,Dashboard.class,null)
                                .setReorderingAllowed(true)
                                .addToBackStack("Dashboard")
                                .commit();
                    } else if (selectedItem.equals("Savings")) {
                        fragmentManager.beginTransaction().
                                replace(R.id.fragmentContainerView, Savings.class,null)
                                .setReorderingAllowed(true)
                                .addToBackStack("Expenses")
                                .commit();

                    } else {
                        fragmentManager.beginTransaction().
                                replace(R.id.fragmentContainerView, Expenses.class,null)
                                .setReorderingAllowed(true)
                                .addToBackStack("Expenses")
                                .commit();
                    }
                    Log.d("ExpenseTracker", "onItemSelected() called with: parent = [" + parent + "], view = [" + view + "], position = [" + position + "], id = [" + id + "]"+"CurrentOption = ["+selectedItem+"]");
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Log.d("ExpenseTracker","onNothingSelected is Triggered");
                }
            });
        } catch (Exception e) {
            Log.e("CurrentFragment", e.toString());
            Log.e("ExpenseTracker","Error Occurred In DashBoard.SetItemClickListener : "+e.toString());
        }
        FloatingActionButton fab = findViewById(R.id.addExpenses);
            fab.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this, AddCustomExpenses.class);
                Log.d("MainActivity", "FloatingActionButton clicked");
                startActivity(intent);
                Log.i("ExpenseTracker", "Starting Activity To Add a New Transaction");
        });
    }
}