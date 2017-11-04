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
    // table names
    private static final String TABLE_BUDGET_STATS = "budget_stats";
    private static final String TABLE_UPCOMING_BILLS = "upcoming_bills";
    private static final String TABLE_TRANSACTIONS = "transactions";

    //Budget Stats table Column names
    private static final String KEY_ID = "id";
    private static final String CURRENT_BALANCE = "current_balance";
    private static final String EXPENSES_RMAINING = "expenses_remaining";
    private static final String WEEKS_USED = "weeks_used";
    private static final String WEEKS_DELINQUENT = "weeks_delinquent";
    private static final String WEEKS_CLOSE = "weeks_close";
    private static final String TOTAL_SAVINGS = "total_savings";
    private static final String CURRENT_INDEX = "current_index";
    private static final String NUM_OF_ENTRIES = "num_off_entries" ;

    //Upcoming bills table column names
    private static final String UPCOMING_ID  = "upcoming_id";
    private static final String UPCOMING_DUE_DATE = "due_date";
    private static final String UPCOMING_BILL_TYPE = "upcoming_type";
    private static final String UPCOMING_BILL_AMOUNT = "upcoming_AMOUNT";
    private static final String UPCOMING_BILL_COMMENT = "upcoming_comment";

    //Transactions table column names
    private static final String TRANSACTION_ID  = "transaction_id";
    private static final String TRANSACTION_TYPE = "transaction_type";
    private static final String TRANSACTION_AMOUNT = "transaction_amount";
    private static final String TRANSACTION_COMMENT = "transaction_comment";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating the initial table
        String CREATE_BUDGET_TABLE = "CREATE TABLE " + TABLE_BUDGET_STATS + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + CURRENT_BALANCE + " TEXT,"
        + EXPENSES_RMAINING + " TEXT" + WEEKS_DELINQUENT + " TEXT" +  WEEKS_CLOSE + " TEXT"
        + TOTAL_SAVINGS + " TEXT" + CURRENT_INDEX + " TEXT" + NUM_OF_ENTRIES+")";
        db.execSQL(CREATE_BUDGET_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUDGET_STATS);
    // Creating tables again
        onCreate(db);
    }

    // Adding new budget
    public void addShop(BudgetData budgetData) {

    }
}