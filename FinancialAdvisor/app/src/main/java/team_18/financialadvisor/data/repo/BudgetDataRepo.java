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


public class BudgetDataRepo {



    public static String createTable(){

        //the budget table
        return  "CREATE TABLE " + BudgetData.TABLE_BUDGET_STATS + "("
                + BudgetData.KEY_ID + " INTEGER PRIMARY KEY," + BudgetData.CURRENT_BALANCE + " REAL,"
                + BudgetData.EXPENSES_REMAINING + " REAL," + BudgetData.TOTAL_SAVINGS + " REAL,"
                + BudgetData.WEEKS_DELINQUENT + " INT," + BudgetData.CURRENT_INDEX + " INT,"
                + BudgetData.WEEKS_USED + " INT," + BudgetData.NUM_OF_ENTRIES + " INT" +")";
    }


    //todo add one blank initial entry
    public static void setBudget() {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(BudgetData.KEY_ID, 1);
        values.put(BudgetData.CURRENT_BALANCE, 0.00);
        values.put(BudgetData.EXPENSES_REMAINING, 0.00);
        values.put(BudgetData.TOTAL_SAVINGS, 0.00);
        values.put(BudgetData.WEEKS_DELINQUENT, 8);
        values.put(BudgetData.CURRENT_INDEX, 0);
        values.put(BudgetData.WEEKS_USED, 30);
        values.put(BudgetData.NUM_OF_ENTRIES, 0);


        // Inserting Row
        db.insert(BudgetData.TABLE_BUDGET_STATS, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }

    public static Cursor getAllData() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery( "SELECT * FROM " + BudgetData.TABLE_BUDGET_STATS, null );

        return cursor;
    }


    //todo add code to update data


}

