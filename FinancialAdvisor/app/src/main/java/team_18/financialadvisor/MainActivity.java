package team_18.financialadvisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.database.Cursor;

import java.util.ArrayList;

import team_18.financialadvisor.data.model.BudgetData;
import team_18.financialadvisor.data.repo.NewTransactionRepo;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaring button
        Button buttonMMGoToBS = (Button) findViewById(R.id.buttonMMGoToBS);
        Button buttonMMGoToCV = (Button) findViewById(R.id.buttonMMGoToCV);
        Button buttonMMGoToGV = (Button) findViewById(R.id.buttonMMGoToGV);
        Button buttonMMGoToAddIncome = (Button) findViewById(R.id.buttonMMGoToAddIncome);
        Button MMButtonSubtractFromBudget = (Button) findViewById(R.id.MMButtonSubtractFromBudget);

        //TODO: TEST BUTTONS, REMOVE LATER
        Button testBudget = (Button) findViewById(R.id.buttonMMTestBudget);
        Button testBudget2 = (Button) findViewById(R.id.buttonMMTestBudget2);

        //Pseudo Database code
        final BudgetData database = new BudgetData();
        PseudoUpcomingDatabase upcoming = new PseudoUpcomingDatabase();


        //Declaring Financial Health EditText
        EditText healthText = (EditText)findViewById(R.id.MMEditTextFinancialHealth);


        //Financial health status generation


        //Declaring Budget EditText
        final EditText budgetText = (EditText)findViewById(R.id.MMEditTextCurrentBudget);
        budgetText.setText(database.budgetToString());

        //Initial ListView creation
       listItems();

        //Setting button behaviors



        buttonMMGoToBS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, BudgetSummary.class);
                startActivity(myIntent);
            }
        });

        buttonMMGoToCV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, CalendarView.class);
                startActivity(myIntent);
            }
        });

        buttonMMGoToGV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, GraphView.class);
                startActivity(myIntent);
            }
        });

        buttonMMGoToAddIncome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, AddIncome.class);
                startActivity(myIntent);

            }
        });

        MMButtonSubtractFromBudget.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, AddExpenses.class);
                startActivity(myIntent);

            }
        });
    }

    public void listItems() {

        Cursor allBills = NewTransactionRepo.getAllBills();
        ArrayList<String> mylist = new ArrayList<String>();

        allBills.moveToFirst();
        int fourBills = 0;
        String bills;
        do {

            // todo seprate recurring from one time transactins and format the output

            String billType = allBills.getString(3);
            String amout = allBills.getString(1);
            bills = billType + "  " + amout;
            mylist.add(bills);
            allBills.moveToNext();
            fourBills++;

        } while ( fourBills < 4 || !allBills.isLast());

        for (int i = 0; i <= 4; i++){
            ListView simpleList;
            ArrayAdapter<String> arrayAdapter;
            simpleList = (ListView)
                    findViewById(R.id.MMListViewUpcomingBills);
            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, mylist);
            simpleList.setAdapter(arrayAdapter);

        }

    }


}

