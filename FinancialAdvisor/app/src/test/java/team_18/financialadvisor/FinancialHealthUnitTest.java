/*package team_18.financialadvisor;

import android.content.Context;
import android.database.Cursor;

import org.junit.Test;

import team_18.financialadvisor.data.DBHelper;
import team_18.financialadvisor.data.DatabaseManager;
import team_18.financialadvisor.data.model.BudgetData;
import team_18.financialadvisor.data.repo.BudgetDataRepo;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
/*
public class FinancialHealthUnitTest {
    @Test
    public void FinancialHealth() throws Exception {
        Context context;
        DBHelper dbHandler;
        BudgetDataRepo.setBudget();
        getDBdata();
        dbHandler = new DBHelper();
        DatabaseManager.initializeInstance(dbHandler);
        final FinancialHealthStatus health =new FinancialHealthStatus();

        String status= health.generateStatus();
        assertEquals("Poor", status);
    }
    public static BudgetData getDBdata() {

        Cursor allData = BudgetDataRepo.getAllData();
        // BudgetData allBudgetData = new BudgetData();

        if(allData != null && allData.moveToFirst()) {

            BudgetData setBudgetData = new BudgetData(allData.getInt(0),allData.getDouble(1),
                    allData.getDouble(2), allData.getDouble(3),allData.getInt(4),
                    allData.getInt(5), allData.getInt(6), allData.getInt(7));
            return setBudgetData;
        }

        return null;
    }
}
*/