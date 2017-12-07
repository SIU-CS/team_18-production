package team_18.financialadvisor.data.model;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
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

        SimpleDateFormat df = new SimpleDateFormat("mm/dd/yyyy");
        Date calDate, dbDate = null;
        calDate = new Date();
        double myBudget = 0.00;
        Cursor allIncomes = RecurringIncomeRepo.getIncomes();
        Cursor budget = BudgetDataRepo.getAllData();
        budget.moveToFirst();
        myBudget = budget.getDouble(1);
        allIncomes.moveToFirst();

        do {

            double amount = allIncomes.getDouble(1);

            try {
                dbDate = df.parse(allIncomes.getString(5));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (toJulianDate(df.format(calDate) )<= toJulianDate(dbDate)){
                myBudget += amount;
                BudgetDataRepo.updateBudget(myBudget);
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

}
