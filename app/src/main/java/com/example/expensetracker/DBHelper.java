package com.example.expensetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public ArrayList<String> expenseAmount;
    public ArrayList<String> expenseDate;
    public ArrayList<String> expenseType;
    public ArrayList<String> expenseCustomName;
    public ArrayList<String> expenseNote;
    public ArrayList<String> expenseTag;
    private static final String DATABASE_NAME = "expenseTracker";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        expenseAmount = new ArrayList<>();
        expenseDate = new ArrayList<>();
        expenseType = new ArrayList<>();
        expenseCustomName = new ArrayList<>();
        expenseNote = new ArrayList<>();
        expenseTag = new ArrayList<>();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS transactions (sno INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, amount TEXT, type TEXT, tag TEXT, date TEXT, note TEXT)";
        db.execSQL(createTableQuery);
        Log.d("DBHelper", "Creation of transactions table is successful");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade your database here if needed
        // This method is called when the DATABASE_VERSION is incremented
    }

    public long addTransaction(Transaction transaction){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",transaction.getName());
        values.put("amount",transaction.getAmount());
        values.put("type",transaction.getType());
        values.put("tag",transaction.getTag());
        values.put("date",transaction.getDate());
        values.put("note",transaction.getNote());
        long newid = db.insert("transactions",null,values);
        db.close();
        Log.d("DBHelper", "addTransaction: {" + transaction.getName() + transaction.getAmount() + transaction.getType() + transaction.getTag() + transaction.getNote() + transaction.getDate() + "}" );
        return newid;
    }
    public void getFullData() {
        ArrayList<String> dataList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {"name","amount","type","tag","date","note"};

        Cursor cursor = db.query(
                "transactions",
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String amount = cursor.getString(cursor.getColumnIndexOrThrow("amount"));
            String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
            String tag = cursor.getString(cursor.getColumnIndexOrThrow("tag"));
            String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
            String note = cursor.getString(cursor.getColumnIndexOrThrow("note"));
            expenseCustomName.add(name);
            expenseNote.add(note);
            expenseDate.add(date);
            expenseAmount.add(amount);
            expenseType.add(type);
            expenseTag.add(tag);
        }

        cursor.close();
        db.close();
    }

    public void setExpenseAmount(ArrayList<String> expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

       public ArrayList<String> getExpenseAmount() {
        return this.expenseAmount;
    }

       public ArrayList<String> getExpenseTag() {
        return this.expenseTag;
    }

       public ArrayList<String> getExpenseCustomName() {
        return this.expenseCustomName;
    }

       public ArrayList<String> getExpenseType() {
        return this.expenseType;
    }

    public ArrayList<String> getExpenseDate() {
        return this.expenseDate;
    }
    public ArrayList<String> getExpenseNote() {
        return this.expenseNote;
    }

    public void setExpenseDate(ArrayList<String> expenseDate) {
        this.expenseDate = expenseDate;
    }

    public void setExpenseType(ArrayList<String> expenseType) {
        this.expenseType = expenseType;
    }

    public void setExpenseNote(ArrayList<String> expenseNote) {
        this.expenseNote = expenseNote;
    }

    public void setExpenseCustomName(ArrayList<String> expenseCustomName) {
        this.expenseCustomName = expenseCustomName;
    }

    public void setExpenseTag(ArrayList<String> expenseTag) {
        this.expenseTag = expenseTag;
    }
}
