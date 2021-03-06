package team_18.financialadvisor;


import android.database.Cursor;

import team_18.financialadvisor.data.repo.BudgetDataRepo;


/**
 * Created by Dakota on 10/30/2017.
 */

public class FinancialHealthStatus {



    public int getWeeksDelinquent(){
        int weeks;
        Cursor budgetStats = BudgetDataRepo.getAllData();
        budgetStats.moveToFirst();
        weeks = budgetStats.getInt(4);
        return weeks;
    }

    public double getTotalSavings(){
        double savings;
        Cursor budgetStats = BudgetDataRepo.getAllData();
        budgetStats.moveToFirst();
        savings = budgetStats.getDouble(3);
        return savings;
    }

    public int getWeeksUsed(){
        int weeks;
        Cursor budgetStats = BudgetDataRepo.getAllData();
        budgetStats.moveToFirst();
        weeks = budgetStats.getInt(6);
        return weeks;
    }

    public double getExpensesRemaining(){
        double expenses;
        Cursor budgetStats = BudgetDataRepo.getAllData();
        budgetStats.moveToFirst();
        expenses = budgetStats.getDouble(2);
        return expenses;
    }

    public double getCurrentBalance(){
        double balance;

        Cursor budgetStats = BudgetDataRepo.getAllData();
        budgetStats.moveToFirst();
        balance = budgetStats.getDouble(1);
        return balance;
    }



    public FinancialHealthStatus(){

    }



    public int balanceMinusExpensesPoints(){
        int points = 0;
        if(getCurrentBalance()+getExpensesRemaining()>500)
            points=30;
        else if (getCurrentBalance()+getExpensesRemaining()>300)
            points=25;
        else if (getCurrentBalance()+getExpensesRemaining()>100)
            points=20;
        else if (getCurrentBalance()+getExpensesRemaining()> 0)
            points=10;
        else if (getCurrentBalance()+getExpensesRemaining()<0)
            points=0;
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

    public int weeksDelinquentPoints(){
        int points = 0;
        if (getWeeksDelinquent()==0)
            points=20;
        else if (getWeeksDelinquent()/getWeeksUsed()<=5)
            points=10;
        else if(getWeeksDelinquent()/getWeeksUsed()<=10)
            points=0;
        return points;
    }

    public String generateStatus(){
        int balance = balanceMinusExpensesPoints();
        int savings = savingsPoints();
        int weeks = weeksDelinquentPoints();
        int points = (balance + savings + weeks);
        String status=null;
        if (points<=10)
            status="Bad";
        else if(points<=20)
            status="Poor";
        else if (points<=30)
            status="Good";
        else if (points<=45)
            status="Very Good";
        else if(points>45)
            status="Excellent";
        return status;
    }

}
