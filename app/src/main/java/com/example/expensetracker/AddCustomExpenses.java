package com.example.expensetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class AddCustomExpenses extends Activity {
    EditText newCustomName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenses_add_floaing_button);

        newCustomName = findViewById(R.id.newCustomName);
// Inside AddCustomExpenses activity
//        String yourData = "found";
//        Intent resultIntent = new Intent();
//        resultIntent.putExtra("expenseKey", newCustomName.getText().toString() + yourData);
//        setResult(Activity.RESULT_OK, resultIntent);
//        finish();


    }
}
