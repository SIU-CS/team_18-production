package team_18.financialadvisor.data.model;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import team_18.financialadvisor.data.repo.BudgetDataRepo;
import team_18.financialadvisor.data.repo.RecurringIncomeRepo;

/**
 * Created by Avtar on 12/6/17.
 */

public class RecIncome extends AppCompatActivity {

    public static void setIncome(){

        DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
        Date calDate, dbDate = null;
        Date myDate = null;
        calDate = getCurrentDate();
        double myBudget = 0.00;

        Cursor allIncomes = RecurringIncomeRepo.getIncomes();
        Cursor budget = BudgetDataRepo.getAllData();
        budget.moveToFirst();
        allIncomes.moveToFirst();

        myBudget = budget.getDouble(1);

        do {

            double amount = allIncomes.getDouble(1);

            try {
                dbDate = df.parse(allIncomes.getString(5));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (toJulianDate(calDate)== toJulianDate(dbDate) || toJulianDate(calDate)> toJulianDate(dbDate)){
                myBudget += amount;
                BudgetDataRepo.updateBudget(myBudget);

                String dateDue = allIncomes.getString(5);
                try {
                    myDate = df.parse(dateDue);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(myDate);

                //Update expenses in the recurring transaction and add to transactions table
                if (allIncomes.getString(2).compareToIgnoreCase("Weekly") == 0)
                {

                    cal.add(Calendar.DAY_OF_MONTH, 7);

                }
                else if(allIncomes.getString(2).compareToIgnoreCase("Bi-Weekly") == 0)
                {


                    cal.add(Calendar.DAY_OF_MONTH, 14);
                }
                else
                {
                    cal.add(Calendar.MONTH, 1);

                }

                df = new SimpleDateFormat("mm/dd/yyyy");
                Date resultdate = new Date(cal.getTimeInMillis());
                String thisDate = df.format(resultdate);
                RecurringIncomeRepo.setNewDate(thisDate, allIncomes.getInt(0));

            }


        } while (allIncomes.moveToNext());
    }


    public static Integer toJulianDate(Date pDate) {
        if (pDate == null) {
            return null;
        }
        Calendar lCal = Calendar.getInstance();
        lCal.setTime(pDate);
        int lYear = lCal.get(Calendar.YEAR);
        int lMonth = lCal.get(Calendar.MONTH) + 1;
        int lDay = lCal.get(Calendar.DATE);
        int a = (14 - lMonth) / 12;
        int y = lYear + 4800 - a;
        int m = lMonth + 12 * a - 3;
        return lDay + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045;
    }

    public static Date getCurrentDate(){
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();

        Date theDate = c.getTime();

        return theDate;

    }

}
