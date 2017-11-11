package team_18.financialadvisor.data.model;

/**
 * Created by Avtar on 11/8/17.
 * this calss creates new transactions based on input
 *
 */

public class NewTransaction {
    public static final String TABLE_TRANSACTIONS = "Transactions";
    public static final String TABLE_RECURRING_EXPENSES = "RecurringExpenses";
    public static final String TABLE_RECURRING_INCOME = "RecurringIncome";




    //Transactions table column names
    public static final String KEY_TRANSACTION_ID = "transaction_id";
    public static final String KEY_TYPE = "type";
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_COMMENT = "comment";
    public static final String KYE_TRANSACTION_EVERY = "transaction_recurring";
    public static final String KYE_TRANSACTION_DATE= "paid_on";


    //variables
    private String transactionType;
    private double transactionAmount;
    private String transactionComment;
    private int transactionID;
    private String transactionRecurring;
    private String date;



    //getter and setter
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionComment() {
        return transactionComment;
    }

    public void setTransactionComment(String transactionComment) {
        this.transactionComment = transactionComment;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionRecurring() {
        return transactionRecurring;
    }

    public void setTransactionRecurring(String transactionRecurring) {
        this.transactionRecurring = transactionRecurring;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString(){
        String temp;
        String price = Double.toString(getTransactionAmount());
        temp = (getTransactionComment() + ": (" + price + ")");
        return temp;
    }

}

