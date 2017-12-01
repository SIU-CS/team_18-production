package team_18.financialadvisor.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

import java.text.DecimalFormat;

import team_18.financialadvisor.AddIncome;
import team_18.financialadvisor.R;
import team_18.financialadvisor.data.DatabaseManager;
import team_18.financialadvisor.data.model.NewTransaction;

/**
 * Created by Avtar on 11/9/17.
 */

public class RecurringIncomeRepo {

    private NewTransaction income;
    public RecurringIncomeRepo(){

        income = new NewTransaction();

    }
    public static String createTable(){

        //this returns a string that creates the blank table with give column names
        return "CREATE TABLE " + NewTransaction.TABLE_RECURRING_INCOME  + "("
                + NewTransaction.KEY_TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NewTransaction.KEY_AMOUNT + " REAL,"
                + NewTransaction.KYE_TRANSACTION_EVERY + " TEXT,"
                + NewTransaction.KEY_TYPE + " TEXT,"
                + NewTransaction.KEY_COMMENT + " TEXT,"
                + NewTransaction.KYE_TRANSACTION_DATE + " DEFAULT CURRENT_TIMESTAMP"  +")";
    }

    public void insert(NewTransaction income) {

        DecimalFormat precision = new DecimalFormat("0.00");

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        String incomeEvery = income.getTransactionRecurring();
        values.put(NewTransaction.KEY_AMOUNT, precision.format(getIncome(income, incomeEvery)));
        values.put(NewTransaction.KYE_TRANSACTION_EVERY, income.getTransactionRecurring());
        values.put(NewTransaction.KEY_TYPE, income.getTransactionType());
        values.put(NewTransaction.KEY_COMMENT, income.getTransactionComment());
        values.put(NewTransaction.KYE_TRANSACTION_DATE, income.getDate());

        // Inserting Row
        db.insert(NewTransaction.TABLE_RECURRING_INCOME, null, values);

        DatabaseManager.getInstance().closeDatabase();
    }

    //get cursor object from the Budget Database and use it to get values
    public Cursor getDB(){

        Cursor cursor = BudgetDataRepo.getAllData();
        if(cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    public double getIncome(NewTransaction income, String incomeEvery){
        double  incomePerMo = 0.00;

        if (incomeEvery.compareToIgnoreCase("Weekly") == 0)
        {
                incomePerMo = income.getTransactionAmount() * 4;

        }
        else if(incomeEvery.compareToIgnoreCase("Bi-Weekly") == 0)
        {
            incomePerMo = income.getTransactionAmount() * 2;
        }
        else
        {
            incomePerMo = income.getTransactionAmount();
        }


        return incomePerMo;

    }

    //todo set code for Deleting a transaction by ID
    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(NewTransaction.TABLE_RECURRING_INCOME, null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    //get bill takes in an id and seaches and return that entry

    public Cursor getIncomeByID(int id) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery( "SELECT * FROM " + NewTransaction.TABLE_RECURRING_INCOME + " WHERE " +
                NewTransaction.TABLE_RECURRING_INCOME + "=1", new String[] { Integer.toString(id) } );
        return cursor;
    }
    public static Cursor getIncomes() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery( "SELECT * FROM " + NewTransaction.TABLE_RECURRING_INCOME, null );

        return cursor;
    }



}
