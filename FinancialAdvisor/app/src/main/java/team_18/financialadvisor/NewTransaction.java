package team_18.financialadvisor;

/**
 * Created by Avtar on 11/3/17.
 * this calss creates new transactions based on input
 *
 */

public class NewTransaction {

    private String transactionType;
    private double transactionAmount;
    private String transactionComment;
    private int transactionID;
    private int transactionRecurring;

    // constructors
    public NewTransaction() {
    }

    public NewTransaction(double transactionAmount,int transactionRecurring, String transactionType, String transactionComment) {

        this.transactionAmount = transactionAmount;
        this.transactionRecurring = transactionRecurring;
        this.transactionType = transactionType;
        this.transactionComment = transactionComment;
    }

    public NewTransaction(int transactionID, double transactionAmount,int transactionRecurring,
                          String transactionType, String transactionComment) {

        this.transactionID = transactionID;
        this.transactionAmount = transactionAmount;
        this.transactionRecurring = transactionRecurring;
        this.transactionType = transactionType;
        this.transactionComment = transactionComment;
    }

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

    public int getTransactionRecurring() {
        return transactionRecurring;
    }

    public void setTransactionRecurring(int transactionRecurring) {
        this.transactionRecurring = transactionRecurring;
    }


    public String toString(){
        String temp;
        String price = Double.toString(getTransactionAmount());
        temp = (getTransactionComment() + ": (" + price + ")");
        return temp;
    }

}
