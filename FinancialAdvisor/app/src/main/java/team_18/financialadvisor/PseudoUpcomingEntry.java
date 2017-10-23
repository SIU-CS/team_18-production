package team_18.financialadvisor;

public class PseudoUpcomingEntry{
    private final String entryMonth;
    private final int entryDate;
    private final int entryYear;
    private final String transactionType;
    private final float transactionAmount;
    private final String transactionComment;
    private final int transactionID;

    PseudoUpcomingEntry previousEntry;
    PseudoUpcomingEntry nextEntry;

    public PseudoUpcomingEntry(String type, float amount, String comment, int ID, String month, int date, int year) {
        entryDate=date;
        entryMonth=month;
        entryYear=year;
        transactionType = type;
        transactionAmount = amount;
        transactionComment = comment;
        transactionID = ID;
    }

}