package team_18.financialadvisor;
import java.lang.String;

/**
 *Class is used to test functionality of app prior to developing the database by acting as a surrogate for the database
 */
public class PseudoDatabaseEntry {

    private final String transactionType;
    private final double transactionAmount;
    private final String transactionComment;
    private final int transactionID;

    //Node Specific things
    PseudoDatabaseEntry previousEntry;
    PseudoDatabaseEntry nextEntry;

    //Getters
    public String getTransactionType() {
        return transactionType;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public String getTransactionComment() {
        return transactionComment;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public PseudoDatabaseEntry(String type, double amount, String comment, int ID) {
        transactionType = type;
        transactionAmount = amount;
        transactionComment = comment;
        transactionID = ID;
    }

    public String toString(){
            String temp;
            String price = Double.toString(getTransactionAmount());
            temp = (getTransactionComment() + " - " + price);
        return temp;
    }

}