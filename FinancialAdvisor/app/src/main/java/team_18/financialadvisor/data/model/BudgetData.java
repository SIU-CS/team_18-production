package team_18.financialadvisor.data.model;

/**
 * Created by Avtar on 11/8/17.
 */

@SuppressWarnings("unused")
public class BudgetData {
    public static final String TABLE_BUDGET_STATS = "BudgetStats";

    //Budget Stats table Column names
    public static final String KEY_ID = "id";
    public static final String CURRENT_BALANCE = "current_balance";
    public static final String EXPENSES_REMAINING = "expenses_remaining";
    public static final String WEEKS_USED = "weeks_used";
    public static final String WEEKS_DELINQUENT = "weeks_delinquent";
    public static final String TOTAL_SAVINGS = "total_savings";
    public static final String CURRENT_INDEX = "current_index";
    public static final String NUM_OF_ENTRIES = "num_off_entries" ;


    private int id;
    private double currentBalance;
    private double expensesRemaining;
    private double totalSavings;
    private int weeksUsed;
    private int weeksDelinquent;
    private int currentIndex;
    private int numOfEntries;


    public BudgetData(int id, double currentBalance, double expensesRemaining, double totalSavings,
          int weeksUsed, int weeksDelinquent, int currentIndex, int numOfEntries) {
        this.id = id;
        this.currentBalance = currentBalance;
        this.expensesRemaining = expensesRemaining;
        this.totalSavings = totalSavings;
        this.weeksUsed = weeksUsed;
        this.weeksDelinquent = weeksDelinquent;
        this.currentIndex = currentIndex;
        this.numOfEntries = numOfEntries;
    }

    public BudgetData() {

    }

    //Getters and Setters
    public  int getId() { return id; }
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

