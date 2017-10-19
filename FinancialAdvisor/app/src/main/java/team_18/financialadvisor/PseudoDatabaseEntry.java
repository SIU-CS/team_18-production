package team_18.financialadvisor;

/**
 *Class is used to test functionality of app prior to developing the database by acting as a surrogate for the database
 */
public class PseudoDatabaseEntry {
    private final String transactionType;
    private final float transactionAmount;
    private final String transactionComment;
    private final int transactionID;


    public PseudoDatabaseEntry(String transactionType, float transactionAmount, String transactionComment, int transactionID) {
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionComment = transactionComment;
        this.transactionID = transactionID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public String getTransactionComment() {
        return transactionComment;
    }

    public int getTransactionID() {
        return transactionID;
    }
}