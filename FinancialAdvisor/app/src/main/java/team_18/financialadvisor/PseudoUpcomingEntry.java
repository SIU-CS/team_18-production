package team_18.financialadvisor;

public class PseudoUpcomingEntry extends PseudoDatabaseEntry{
private final String month = "test";
private final int date = 0;
private final int year = 0;

    public PseudoUpcomingEntry(String transactionType, float transactionAmount, String transactionComment, int transactionID) {
        super(transactionType, transactionAmount, transactionComment, transactionID);
    }

    public String getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    public int getYear() {
        return year;
    }
}