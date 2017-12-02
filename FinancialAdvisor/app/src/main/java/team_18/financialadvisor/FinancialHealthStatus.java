package team_18.financialadvisor;


import android.database.Cursor;

import team_18.financialadvisor.data.repo.BudgetDataRepo;
import team_18.financialadvisor.data.repo.RecurringExpenseRepo;

/**
 * Created by Dakota on 10/30/2017.
 */

public class FinancialHealthStatus {
    private double currentBalance;
    private double expensesRemaining;
    private double totalSavings;
    private int weeksDelinquent;
    private int weeksUsed;


    public int getWeeksDelinquent(){
        int weeks = 0;
        Cursor budgetStats = BudgetDataRepo.getAllData();
        weeks = budgetStats.getInt(4);
        return weeks;
    }

    public double getTotalSavings(){
        double savings = 0;
        Cursor budgetStats = BudgetDataRepo.getAllData();
        savings = budgetStats.getDouble(3);
        return savings;
    }

    public int getWeeksUsed(){
        int weeks = 0;
        Cursor budgetStats = BudgetDataRepo.getAllData();
        weeks = budgetStats.getInt(6);
        return weeks;
    }

    public double getExpensesRemaining(){
        double expenses = 0;
        Cursor budgetStats = BudgetDataRepo.getAllData();
        expenses = budgetStats.getDouble(2);
        return expenses;
    }

    public double getCurrentBalance(){
        double balance = 0.00;
        Cursor budgetStats = BudgetDataRepo.getAllData();
        balance = budgetStats.getDouble(4);
        return balance;
    }


    /**
    public FinancialHealthStatus(PseudoDatabase database){
        currentBalance=database.getCurrentBalance();
        expensesRemaining=database.getExpensesRemaining();
        totalSavings=database.getTotalSavings();
    }

    public void refreshStatus(PseudoDatabase database){
        setCurrentBalance(database);
        setExpensesRemaining(database);
        setTotalSavings(database);
    }

    public int currentBalancePoints(){
        int points = 0;
         if(getCurrentBalance()<=50)
             points=5;
         else if (getCurrentBalance()<=100)
             points=10;
        else if (getCurrentBalance()<=150)
            points=15;
        else if(getCurrentBalance()<=200)
            points=20;
        else if (getCurrentBalance()>200)
            points=25;
        return points;
    }

    public int expensesPoints(){
        int points = 0;
        if(getExpensesRemaining()<=50)
            points=3;
        else if (getExpensesRemaining()<=100)
            points=2;
        else if (getExpensesRemaining()<=150)
            points=1;
        return points;
    }
    public int savingsPoints(){
        int points = 0;
        if(getTotalSavings()<=250)
            points=1;
        else if (getTotalSavings()<=500)
            points=2;
        else if (getTotalSavings()<=1500)
            points=3;
        else if(getTotalSavings()<=5000)
            points=4;
        else if (getTotalSavings()>5000)
            points=5;
        return points;

    }

    public String generateStatus(PseudoDatabase database){
        refreshStatus(database);
        int points = (currentBalancePoints()+expensesPoints()+ savingsPoints());
        String status=null;
        if (points<=7)
            status="Bad";
        else if(points<=13)
            status="Poor";
        else if (points<=19)
            status="Good";
        else if (points<=25)
            status="Very Good";
        else if(points>25)
            status="Excellent";
        return status;
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


    public double getTotalSavings() {
        return totalSavings;
    }

    public void setTotalSavings(PseudoDatabase database) {
        this.totalSavings = getTotalSavings();
    }
    */
}
