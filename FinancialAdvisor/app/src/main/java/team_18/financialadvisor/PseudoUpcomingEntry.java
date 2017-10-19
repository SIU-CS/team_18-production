public class PseudoUpcomingEntry extends PseudoDatabaseEntry{
private final String month;
private final int date;
private final int year;


    public PseudoUpcomingEntry(String month, int date, int year) {
        this.month = month;
        this.date = date;
        this.year = year;
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