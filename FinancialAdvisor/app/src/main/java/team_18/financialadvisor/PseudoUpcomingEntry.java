package team_18.financialadvisor;

public class PseudoUpcomingEntry extends PseudoDatabaseEntry{
private final String entryMonth;
private final int entryDate;
private final int entryYear;

    public PseudoUpcomingEntry(String transactionType, float transactionAmount, String transactionComment, int transactionID, String month, int date, int year) {
        super(transactionType, transactionAmount, transactionComment, transactionID);
        entryDate=date;
        entryMonth=month;
        entryYear=year;
    }




}