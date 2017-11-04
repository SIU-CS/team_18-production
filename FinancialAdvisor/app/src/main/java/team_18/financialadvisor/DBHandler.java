package team_18.financialadvisor;

/**
 * Created by Avtar on 11/3/17.
 *
 * This Class handles database Create, Read, Update and Delete.
 *
 *
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "budgetInfo";
    // Contacts table name
    private static final String TABLE_BUDGET = "budgetMain";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String CURRENT_BALANCE = "current_balance";
    private static final String EXPENSES_RMAINING = "expenses_remaining";
    private static final String WEEKS_USED = "weeks_used";
    private static final String WEEKS_DELINQUENT = "weeks_delinquent";
    private static final String WEEKS_CLOSE = "weeks_close";
    private static final String TOTAL_SAVINGS = "total_savings";
    private static final String CURRENT_INDEX = "current_index";
    private static final String NUM_OF_ENTRIES = "num_off_entries" ;


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating the initial table
        String CREATE_BUDGET_TABLE = "CREATE TABLE " + TABLE_BUDGET + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + CURRENT_BALANCE + " TEXT,"
        + EXPENSES_RMAINING + " TEXT" + WEEKS_DELINQUENT + " TEXT" +  WEEKS_CLOSE + " TEXT"
        + TOTAL_SAVINGS + " TEXT" + CURRENT_INDEX + " TEXT" + NUM_OF_ENTRIES+")";
        db.execSQL(CREATE_BUDGET_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUDGET);
    // Creating tables again
        onCreate(db);
    }

    // Adding new budget
    public void addShop(BudgetData budgetData) {

    }
}