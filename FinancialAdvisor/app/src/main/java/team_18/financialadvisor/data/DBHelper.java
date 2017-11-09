package team_18.financialadvisor.data;

/**
 * Created by Avtar on 11/8/17.
 *
 * This Class handles database Create, Read.
 *
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;

import java.util.Date;

import team_18.financialadvisor.App;
import team_18.financialadvisor.data.model.BudgetData;
import team_18.financialadvisor.data.model.NewTransaction;
import team_18.financialadvisor.data.repo.BudgetDataRepo;
import team_18.financialadvisor.data.repo.NewTransactionRepo;


public class DBHelper extends SQLiteOpenHelper {

    // set the format to sql date time
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();

    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "myBudget.db";
    private static final String TAG = DBHelper.class.getSimpleName().toString();




    public DBHelper() {

        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables

        db.execSQL(NewTransactionRepo.createTable());
        db.execSQL(BudgetDataRepo.createTable());

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed

        db.execSQL("DROP TABLE IF EXISTS " + NewTransaction.TABLE_TRANSACTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + BudgetData.TABLE_BUDGET_STATS);

        // Creating tables again
        onCreate(db);
    }



}
