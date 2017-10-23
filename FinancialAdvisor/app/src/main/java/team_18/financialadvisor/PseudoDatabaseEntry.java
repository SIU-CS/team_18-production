package team_18.financialadvisor;

/**
 *Class is used to test functionality of app prior to developing the database by acting as a surrogate for the database
 */
public class PseudoDatabaseEntry {
    private final String transactionType;
    private final float transactionAmount;
    private final String transactionComment;
    private final int transactionID;

    //Node Specific things
    PseudoDatabaseEntry previousEntry;
    PseudoDatabaseEntry nextEntry;

    public PseudoDatabaseEntry(String type, float amount, String comment, int ID) {
        transactionType = type;
        transactionAmount = amount;
        transactionComment = comment;
        transactionID = ID;
    }

}