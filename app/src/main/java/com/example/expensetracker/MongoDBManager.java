package com.example.expensetracker;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
public class MongoDBManager {
    private MongoClient mongoClient;
    private MongoDatabase database;
    public  MongoDBManager(){
        String connectionString = "mongodb+srv://464venkatsai:<password>@datascience464.0fxwztl.mongodb.net/?retryWrites=true&w=majority";
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase("ExpenseTracker");
    }
    protected void addExpensesTransaction(String collectionName,String expenseTitle, double amount, String date, String note){
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document transaction = new Document();

        transaction.append("expenseTitle",expenseTitle);
        transaction.append("Amount",amount);
        transaction.append("Date",date);
        transaction.append("Note",note);

        collection.insertOne(transaction);
    }

//    protected void closeConnection(){
//
//    }
}
