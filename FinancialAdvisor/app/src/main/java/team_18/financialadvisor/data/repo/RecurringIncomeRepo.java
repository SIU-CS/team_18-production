package team_18.financialadvisor.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DecimalFormat;

import team_18.financialadvisor.data.DatabaseManager;
import team_18.financialadvisor.data.model.BudgetData;
import team_18.financialadvisor.data.model.NewTransaction;
import team_18.financialadvisor.data.model.RecurringIncome;

/**
 * Created by Avtar on 11/9/17.
 */

public class RecurringIncomeRepo {


    public static String createTable(){

        //this returns a string that creates the blank table with give column names
        return "CREATE TABLE " + RecurringIncome.TABLE_RECURRING_INCOME  + "("
                + RecurringIncome.KEY_INCOME_ID + " INTEGER PRIMARY KEY,"
                + RecurringIncome.KEY_AMOUNT + " REAL,"
                + RecurringIncome.KYE_TRANSACTION_EVERY + " TEXT,"
                + RecurringIncome.KEY_TYPE + " TEXT,"
                + RecurringIncome.KEY_COMMENT + " TEXT,"
                + RecurringIncome.KYE_DUE_ON + " DEFAULT CURRENT_TIMESTAMP"  +")";
    }



    public void insertRecurringIncome(NewTransaction transaction) {

        DecimalFormat precision = new DecimalFormat("0.00");

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();


        values.put(RecurringIncome.TABLE_RECURRING_INCOME, transaction.getTransactionID());
        values.put(RecurringIncome.KEY_AMOUNT, transaction.getTransactionAmount());
        values.put(RecurringIncome.KYE_TRANSACTION_EVERY, transaction.getTransactionRecurring());
        values.put(RecurringIncome.KEY_TYPE, transaction.getTransactionType());
        values.put(RecurringIncome.KEY_COMMENT, transaction.getTransactionComment());

        // Inserting Row
        db.insert(RecurringIncome.TABLE_RECURRING_INCOME, null, values);

        DatabaseManager.getInstance().closeDatabase();
    }

    //get cursor object from the Budget Database and use it to get values
    public Cursor getDB(){

        Cursor cursor = BudgetDataRepo.getAllData();
        if(cursor != null)
            cursor.moveToFirst();

        return cursor;
    }


    //todo set code for Deleting a transaction by ID
    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(RecurringIncome.TABLE_RECURRING_INCOME, null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    //get bill takes in an id and seaches and return that entry

    public Cursor getIncomeByID(int id) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery( "SELECT * FROM " + RecurringIncome.TABLE_RECURRING_INCOME + " WHERE " +
                RecurringIncome.TABLE_RECURRING_INCOME + "=1", new String[] { Integer.toString(id) } );
        return cursor;
    }
    public static Cursor getIncomes() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery( "SELECT * FROM " + RecurringIncome.TABLE_RECURRING_INCOME, null );

        return cursor;
    }



}
