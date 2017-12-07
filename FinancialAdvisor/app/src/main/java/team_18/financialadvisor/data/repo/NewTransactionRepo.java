package team_18.financialadvisor.data.repo;

/**
 * Created by Avtar on 11/8/17.
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import team_18.financialadvisor.data.DatabaseManager;
import team_18.financialadvisor.data.model.BudgetData;
import team_18.financialadvisor.data.model.NewTransaction;
import java.text.DecimalFormat;

public class NewTransactionRepo {

    @SuppressWarnings("unused")
    private NewTransaction transaction;

    public NewTransactionRepo(){

        transaction = new NewTransaction();

    }

    public static String createTable(){

        //this returns a string that creates the blank table with give column names
        return "CREATE TABLE " + NewTransaction.TABLE_TRANSACTIONS  + "("
                + NewTransaction.KEY_TRANSACTION_ID + " INTEGER PRIMARY KEY,"
                + NewTransaction.KEY_AMOUNT + " REAL,"
                + NewTransaction.KEY_TYPE + " TEXT,"
                + NewTransaction.KEY_COMMENT + " TEXT,"
                + NewTransaction.KYE_TRANSACTION_DATE + " DEFAULT CURRENT_TIMESTAMP" + ")";
    }

    public void insert(NewTransaction transaction) {

        DecimalFormat precision = new DecimalFormat("0.00");

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(NewTransaction.KEY_TRANSACTION_ID, transaction.getTransactionID());
        values.put(NewTransaction.KEY_AMOUNT, transaction.getTransactionAmount());
        values.put(NewTransaction.KEY_TYPE, transaction.getTransactionType());
        values.put(NewTransaction.KEY_COMMENT, transaction.getTransactionComment());
        values.put(NewTransaction.KYE_TRANSACTION_DATE, transaction.getDate());
        // Inserting Row
        db.insert(NewTransaction.TABLE_TRANSACTIONS, null, values);

        //this entry updates the budget data by adding the new transaction amount
        db.execSQL("UPDATE " + BudgetData.TABLE_BUDGET_STATS + " SET "
                + BudgetData.CURRENT_BALANCE+"='" +
                ( precision.format(transaction.getTransactionAmount()
                        + updateDB().getDouble(1))) +
                        "' WHERE id=1 ");

        db.execSQL("UPDATE " + BudgetData.TABLE_BUDGET_STATS + " SET "
                + BudgetData.NUM_OF_ENTRIES+"='" +
                (updateDB().getInt(7)+ 1) +
                "' WHERE id=1 ");

        DatabaseManager.getInstance().closeDatabase();
    }

    //get cursor object from the Budget Database and use it to get values
    public Cursor updateDB(){

        Cursor cursor = BudgetDataRepo.getAllData();
        if(cursor != null)
            cursor.moveToFirst();

        return cursor;
    }


    //todo set code for Deleting a transaction by ID
    @SuppressWarnings("unused")
    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(NewTransaction.TABLE_TRANSACTIONS, null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    //get bill takes in an id and searches and return that entry
    @SuppressWarnings("unused")
    public Cursor getBill(int id) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery( "SELECT * FROM " + NewTransaction.TABLE_TRANSACTIONS + " WHERE " +
                NewTransaction.KEY_TRANSACTION_ID + "=1", new String[] { Integer.toString(id) } );
        return cursor;
    }
    public static Cursor getAllBills() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery( "SELECT * FROM " + NewTransaction.TABLE_TRANSACTIONS, null );

        return cursor;
    }

}
