


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
        import team_18.financialadvisor.data.model.NewTransaction;


public class NewTransactionRepo {

    private NewTransaction transaction;

    public NewTransactionRepo(){

        transaction = new NewTransaction();

    }


    public static String createTable(){


        return "CREATE TABLE " + NewTransaction.TABLE_TRANSACTIONS  + "("
                + NewTransaction.KEY_TRANSACTION_ID + " INTEGER PRIMARY KEY,"
                + NewTransaction.KEY_AMOUNT + " REAL,"
                + NewTransaction.KYE_TRANSACTION_EVERY + " INT,"
                + NewTransaction.KEY_TYPE + " TEXT,"
                +  NewTransaction.KEY_COMMENT + " TEXT" + ")";
    }



    public void insert(NewTransaction transaction) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(NewTransaction.KEY_TRANSACTION_ID, transaction.getTransactionID());
        values.put(NewTransaction.KEY_AMOUNT, transaction.getTransactionAmount());
        values.put(NewTransaction.KYE_TRANSACTION_EVERY, transaction.getTransactionRecurring());
        values.put(NewTransaction.KEY_TYPE, transaction.getTransactionType());
        values.put(NewTransaction.KEY_COMMENT, transaction.getTransactionComment());

        // Inserting Row
        db.insert(NewTransaction.TABLE_TRANSACTIONS, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }


    //todo set code for Deleting a transaction by ID
    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(NewTransaction.TABLE_TRANSACTIONS, null,null);
        DatabaseManager.getInstance().closeDatabase();
    }


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
