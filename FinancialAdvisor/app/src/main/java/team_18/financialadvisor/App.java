package team_18.financialadvisor;

/**
 * Created by Avtar on 11/8/17.
 *
 * Creates an instance for DBHleper right when the program starts
 *
 */

import android.app.Application;
import android.content.Context;
import android.database.Cursor;

import team_18.financialadvisor.data.DBHelper;
import team_18.financialadvisor.data.DatabaseManager;
import team_18.financialadvisor.data.model.BudgetData;
import team_18.financialadvisor.data.repo.BudgetDataRepo;

public class  App extends Application {
    private static Context context;
    private static DBHelper dbHandler;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this.getApplicationContext();
        dbHandler = new DBHelper();
        DatabaseManager.initializeInstance(dbHandler);
        getDBdata();


    }

    //import initial data
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

    public static Context getContext()
    {
        return context;
    }

}

