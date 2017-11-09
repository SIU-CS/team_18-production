package team_18.financialadvisor;

/**
 * Created by Avtar on 11/8/17.
 *
 * Creates an instance for DBHleper right when the program starts
 *
 */

import android.app.Application;
import android.content.Context;

import team_18.financialadvisor.data.DBHelper;
import team_18.financialadvisor.data.DatabaseManager;

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

    }

    public static Context getContext()
    {
        return context;
    }

}

