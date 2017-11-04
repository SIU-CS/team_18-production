package team_18.financialadvisor;

/*
 * get and set all entries for the database
 *
 *
*/
public class BudgetData {
    private int id;
    private double currentBalance;
    private double expensesRemaining;
    private int weeksUsed;
    private int weeksDelinquent;
    private int weeksClose;
    private double totalSavings;
    private int currentIndex;
    public static PseudoDatabaseEntry root;
    public static PseudoDatabaseEntry temp;
    public static PseudoDatabaseEntry current;
    private static int numOfEntries;


//setting a blank budget
    public BudgetData() {
        id = 1;
        currentBalance = 0.00;
        expensesRemaining = 0.00;
        weeksUsed = 0;
        weeksDelinquent = 0;
        weeksClose = 0;
        totalSavings = 0.00;
        currentIndex = 0;
        numOfEntries =0;
    }
//setting a custom initial budget for testing!!
    public BudgetData(int id, double currentBalance, double expensesRemaining, int weeksUsed,
        int weeksDelinquent, int weeksClose, double totalSavings, int currentIndex, int numOfEntries)
    {
        this.id = id;
        this.currentBalance = currentBalance;
        this.expensesRemaining = expensesRemaining;
        this.weeksUsed = weeksUsed;
        this.weeksDelinquent = weeksDelinquent;
        this.weeksClose = weeksClose;
        this.totalSavings = totalSavings;
        this.currentIndex = currentIndex;
        this.numOfEntries = numOfEntries;
    }



    //Getters and Setters


    public int getId() { return id; }
    public void setId(int id){ this.id  = id; }

    public int getNumOfEntries(){
        return numOfEntries;
    }

    public void setNumOfEntries(int numOfEntries) {
        this.numOfEntries = numOfEntries;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public double getExpensesRemaining() {
        return expensesRemaining;
    }

    public void setExpensesRemaining(double expensesRemaining) {
        this.expensesRemaining = expensesRemaining;
    }

    public int getWeeksUsed() {
        return weeksUsed;
    }

    public void setWeeksUsed(int weeksUsed) {
        this.weeksUsed = weeksUsed;
    }

    public int getWeeksDelinquent() {
        return weeksDelinquent;
    }

    public void setWeeksDelinquent(int weeksDelinquent) {
        this.weeksDelinquent = weeksDelinquent;
    }

    public int getWeeksClose() {
        return weeksClose;
    }

    public void setWeeksClose(int weeksClose) {
        this.weeksClose = weeksClose;
    }

    public double getTotalSavings() {
        return totalSavings;
    }

    public void setTotalSavings(double totalSavings) {
        this.totalSavings = totalSavings;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }


    //toString methods
    public String budgetToString(){
        return Double.toString(currentBalance);
    }

}