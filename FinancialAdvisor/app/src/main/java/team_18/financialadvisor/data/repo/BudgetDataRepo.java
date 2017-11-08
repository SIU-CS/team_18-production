package team_18.financialadvisor.data.repo;


/**
 * Created by Avtar on 11/4/17.
 */
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import team_18.financialadvisor.data.DatabaseManager;
import team_18.financialadvisor.data.model.BudgetData;
import team_18.financialadvisor.data.model.NewTransaction;

/**
 * Created by Tan on 1/26/2016.
 */
public class BudgetDataRepo {

    private BudgetData budget;

    public BudgetDataRepo(){

        budget = new BudgetData();

    }


    public static String createTable(){


        //the budget table
        return  "CREATE TABLE " + BudgetData.TABLE_BUDGET_STATS + "(" + BudgetData.KEY_ID
                + " INTEGER PRIMARY KEY," + BudgetData.CURRENT_BALANCE + " REAL,"
                + BudgetData.EXPENSES_REMAINING + " REAL," + BudgetData.WEEKS_DELINQUENT + " INTEGER,"
                + BudgetData.WEEKS_CLOSE + " INTEGER," + BudgetData.TOTAL_SAVINGS + " REAL,"
                + BudgetData.CURRENT_INDEX + " INTEGER," + BudgetData.WEEKS_USED + " INTEGER,"
                + BudgetData.NUM_OF_ENTRIES + " INTEGER" +")";
    }


    //todo add one blank initial entry
    public void insert(BudgetData budget) {

        // Inserting Row

        DatabaseManager.getInstance().closeDatabase();
    }

    //todo add code to update data



}

