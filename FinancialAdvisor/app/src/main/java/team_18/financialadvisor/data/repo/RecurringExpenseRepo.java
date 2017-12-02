package team_18.financialadvisor.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DecimalFormat;

import team_18.financialadvisor.data.DatabaseManager;
import team_18.financialadvisor.data.model.BudgetData;
import team_18.financialadvisor.data.model.NewTransaction;

/**
 * Created by Avtar on 11/9/17.
 */

public class RecurringExpenseRepo {

    private NewTransaction expense;

    public RecurringExpenseRepo(){

        expense = new NewTransaction();

    }
    public static String createTable(){

        //this returns a string that creates the blank table with give column names
        return "CREATE TABLE " + NewTransaction.TABLE_RECURRING_EXPENSES  + "("
                + NewTransaction.KEY_TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NewTransaction.KEY_AMOUNT + " REAL,"
                + NewTransaction.KYE_TRANSACTION_EVERY + " TEXT,"
                + NewTransaction.KEY_TYPE + " TEXT,"
                + NewTransaction.KEY_COMMENT + " TEXT,"
                + NewTransaction.KYE_TRANSACTION_DATE + " DEFAULT CURRENT_TIMESTAMP"  +")";
    }

    public void insert(NewTransaction expense) {
        double amt = expense.getTransactionAmount();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(NewTransaction.KEY_AMOUNT, String.format("%.2f", amt));
        values.put(NewTransaction.KYE_TRANSACTION_EVERY, expense.getTransactionRecurring());
        values.put(NewTransaction.KEY_TYPE, expense.getTransactionType());
        values.put(NewTransaction.KEY_COMMENT, expense.getTransactionComment());
        values.put(NewTransaction.KYE_TRANSACTION_DATE, expense.getDate());

        // Inserting Row
        db.insert(NewTransaction.TABLE_RECURRING_EXPENSES, null, values);

        //Update expenses in the budget stats DB depending if its weekly/bi-weekly/monthly
        if (expense.getTransactionRecurring().compareToIgnoreCase("Weekly") == 0)
        {
            amt = (expense.getTransactionAmount()*4) + updateDB().getDouble(2);

            db.execSQL("UPDATE " + BudgetData.TABLE_BUDGET_STATS + " SET "
                    + BudgetData.EXPENSES_REMAINING+"='" +
                    String.format("%.2f",amt) + "' WHERE id=1 ");
        }
        else if(expense.getTransactionRecurring().compareToIgnoreCase("Bi-Weekly") == 0)
        {
            amt = (expense.getTransactionAmount()*2) + updateDB().getDouble(2);
            db.execSQL("UPDATE " + BudgetData.TABLE_BUDGET_STATS + " SET "
                    + BudgetData.EXPENSES_REMAINING+"='"
                    + String.format("%.2f",amt) + "' WHERE id=1 ");
        }
        else
        {
            amt = expense.getTransactionAmount() + updateDB().getDouble(2);
            db.execSQL("UPDATE " + BudgetData.TABLE_BUDGET_STATS + " SET "
                    + BudgetData.EXPENSES_REMAINING+"='" +
                    String.format("%.2f", amt) + "' WHERE id=1 ");

        }

        //todo  updates the budget data by adding the new expenses to the table

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
    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(NewTransaction.TABLE_TRANSACTIONS, null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    //get bill takes in an id and seaches and return that entry

    public static Cursor getBill(int id) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        Cursor cursor = db.rawQuery( "SELECT * FROM "
                + NewTransaction.TABLE_RECURRING_EXPENSES + " WHERE " +
                NewTransaction.KEY_TRANSACTION_ID + "='" + id + "'" , null );
        return cursor;
    }
    public static Cursor getAllBills() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery( "SELECT * FROM "
                + NewTransaction.TABLE_RECURRING_EXPENSES, null );

        return cursor;
    }


}
