package team_18.financialadvisor;

/**
 * Created by Avtar on 11/3/17.
 *
 * This Class handles database Create, Read, Update and Delete entries and data.
 *
 *
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DBHandler extends SQLiteOpenHelper {

    // set the format to sql date time
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "budget_Info";
    // table names
    private static final String TABLE_BUDGET_STATS = "budget_stats";
    private static final String TABLE_UPCOMING_BILLS = "upcoming_bills";
    private static final String TABLE_TRANSACTIONS = "transactions";
    private static final String TABLE_TRANSACTION_TAGS = "transaction_tags";

    //common Column names
    private static final String KEY_ID = "id";
    private static final String TYPE = "type";
    private static final String AMOUNT = "amount";
    private static final String COMMENT = "comment";

    //Budget Stats table Column names
    private static final String CURRENT_BALANCE = "current_balance";
    private static final String EXPENSES_REMAINING = "expenses_remaining";
    private static final String WEEKS_USED = "weeks_used";
    private static final String WEEKS_DELINQUENT = "weeks_delinquent";
    private static final String WEEKS_CLOSE = "weeks_close";
    private static final String TOTAL_SAVINGS = "total_savings";
    private static final String CURRENT_INDEX = "current_index";
    private static final String NUM_OF_ENTRIES = "num_off_entries" ;

    //Upcoming bills table column names
    private static final String UPCOMING_BILL_ID = "bill_id";
    private static final String UPCOMING_BILL_DUE_DATE = "due_date";


    //Transactions table column names
    private static final String TRANSACTION_RECURRING_EVERY = "transaction_recurring_every";

    // NOTE_TAGS Table - column names
    private static final String KEY_TRANSACTION_ID = "transaction_id";
    private static final String KEY_TAG_ID = "tag_id";
    private static final String KEY_CREATED_AT = "created_at";

    //TODO create a tag table so the transactions that are recurring can be tagge with
    //upcoming bills

    // Table Create Statements
    //the budget table
    String CREATE_BUDGET_TABLE = "CREATE TABLE " + TABLE_BUDGET_STATS + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + CURRENT_BALANCE + " REAL,"
            + EXPENSES_REMAINING + " REAL," + WEEKS_DELINQUENT + " INTEGER," +  WEEKS_CLOSE + " INTEGER,"
            + TOTAL_SAVINGS + " REAL," + CURRENT_INDEX + " INTEGER," + WEEKS_USED + " INTEGER," + NUM_OF_ENTRIES + " INTEGER" +")";

    //the upcoming bills table statement
    String CREATE_UPCOMING_BILLS_TABLE = "CREATE TABLE " + TABLE_UPCOMING_BILLS + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + UPCOMING_BILL_ID + " INTEGER," + AMOUNT + " REAL,"
            + UPCOMING_BILL_DUE_DATE + " DATE," + TYPE + " TEXT," +  COMMENT + " TEXT" + ")";

    //the transactions table statement
    String CREATE_TRANSACTIONS_TABLE = "CREATE TABLE " + TABLE_TRANSACTIONS + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + AMOUNT + " REAL,"
            + TRANSACTION_RECURRING_EVERY + " INT," + TYPE + " TEXT," +  COMMENT + " TEXT" + ")";

    //tag table statement
    private static final String CREATE_TABLE_TRANSACTION_TAGS = "CREATE TABLE "
            + TABLE_TRANSACTION_TAGS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TRANSACTION_ID + " INTEGER," + KEY_TAG_ID + " INTEGER,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_BUDGET_TABLE);
        db.execSQL(CREATE_UPCOMING_BILLS_TABLE);
        db.execSQL(CREATE_TRANSACTIONS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUDGET_STATS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UPCOMING_BILLS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);

        // Creating tables again
        onCreate(db);
    }

    /*
     * Creating a transaction
     */
    public long createTransaction(NewTransaction transaction, long[] tag_ids) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, transaction.getTransactionID());
        values.put(AMOUNT, transaction.getTransactionAmount());
        values.put(TRANSACTION_RECURRING_EVERY, transaction.getTransactionRecurring());
        values.put(TYPE, transaction.getTransactionType());
        values.put(COMMENT, transaction.getTransactionComment());


        // insert row
        long transaction_id = db.insert(TABLE_TRANSACTIONS, null, values);
        // assigning tags to todo
        for (long tag_id : tag_ids) {
            createTransactionTag(transaction_id, tag_id);
        }

       return transaction_id;

    }

    /*
     * Creating transaction_tag
     */
    public long createTransactionTag(long transaction_id, long tag_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TRANSACTION_ID, transaction_id);
        values.put(KEY_TAG_ID, tag_id);
        values.put(KEY_CREATED_AT, dateFormat.format(date));

        long id = db.insert(TABLE_TRANSACTION_TAGS, null, values);
        return id;
    }

}