package team_18.financialadvisor;


/**
 * Created by Dakota on 10/30/2017.
 */

public class FinancialHealthStatus {
    private double currentBalance;
    private double expensesRemaining;
    private int weeksUsed;
    private int weeksDelinquent;
    private int weeksClose;
    private double totalSavings;

    public FinancialHealthStatus(PseudoDatabase database){
        currentBalance=database.getCurrentBalance();
        expensesRemaining=database.getExpensesRemaining();
        weeksUsed=database.getWeeksUsed();
        weeksDelinquent=database.getWeeksDelinquent();
        weeksClose=database.getWeeksClose();
        totalSavings=database.getTotalSavings();

    }

    public void refreshStatus(PseudoDatabase database){
        setCurrentBalance(database);
        setExpensesRemaining(database);
        setTotalSavings(database);
        setWeeksClose(database);
        setWeeksDelinquent(database);
        setWeeksUsed(database);
    }
    public String generateStatus(){
        
    }


    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(PseudoDatabase database) {
        this.currentBalance = database.getCurrentBalance();
    }

    public double getExpensesRemaining() {
        return expensesRemaining;
    }

    public void setExpensesRemaining(PseudoDatabase database) {
        this.expensesRemaining = database.getExpensesRemaining();
    }

    public int getWeeksUsed() {
        return weeksUsed;
    }

    public void setWeeksUsed(PseudoDatabase database) {
        this.weeksUsed = database.getWeeksUsed();
    }

    public int getWeeksDelinquent() {
        return weeksDelinquent;
    }

    public void setWeeksDelinquent(PseudoDatabase database) {
        this.weeksDelinquent = database.getWeeksDelinquent();
    }

    public int getWeeksClose() {
        return weeksClose;
    }

    public void setWeeksClose(PseudoDatabase database) {
        this.weeksClose = database.getWeeksClose();
    }

    public double getTotalSavings() {
        return totalSavings;
    }

    public void setTotalSavings(PseudoDatabase database) {
        this.totalSavings = database.getTotalSavings();
    }
}
