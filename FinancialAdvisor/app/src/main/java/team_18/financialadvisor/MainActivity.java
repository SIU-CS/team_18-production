package team_18.financialadvisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import android.database.Cursor;
import android.widget.Toast;

import team_18.financialadvisor.data.model.BudgetData;
import team_18.financialadvisor.data.repo.BudgetDataRepo;
import team_18.financialadvisor.data.repo.NewTransactionRepo;
import team_18.financialadvisor.data.repo.RecurringExpenseRepo;

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
        ListView simpleList = (ListView) findViewById(R.id.MMListViewUpcomingBills);

        //todo Declaring Financial Health EditText
        EditText healthText = (EditText)findViewById(R.id.MMEditTextFinancialHealth);
          //healthText.setText(health.generateStatus(database), TextView.BufferType.EDITABLE);

        //list bills
        listItems(simpleList);

        //Declaring Budget EditText
        BudgetData newData = App.getDBdata();
        final EditText budgetText = (EditText)findViewById(R.id.MMEditTextCurrentBudget);
            budgetText.setText(String.valueOf(newData.getCurrentBalance()));

        //todo Financial health status generation
            //health.generateStatus(database);

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
        simpleList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String bill = String.valueOf(parent.getItemAtPosition(position));

                       // Toast.makeText(MainActivity.this, " INdex " + position, Toast.LENGTH_LONG).show();

                        //Create a bundle object
                        Bundle b = new Bundle();

                        //Insert a String value into the mapping of this Bundle
                        b.putInt("key_val", position);

                        //Add the bundle to the intent.
                        Intent myIntent = new Intent(MainActivity.this, PayBills.class);
                        myIntent.putExtras(b);

                        //start and Display Activity
                        startActivity(myIntent);
                    }

                });
    }

    public void listItems(ListView simpleList) {

        Cursor allBills = RecurringExpenseRepo.getAllBills();
        ArrayList<String> mylist = new ArrayList<String>();

        allBills.moveToFirst();

        String bills, billType, amount, due;
        if (allBills != null && allBills.moveToFirst()) {

            do {

                billType = allBills.getString(3);
                amount = allBills.getString(1);
                due = allBills.getString(5);
                bills = "$ " + amount + "   " + billType + " Due On: " + due;
                mylist.add(bills);

            } while (allBills.moveToNext());

            for (int i = 0; i <= 4; i++) {

                ArrayAdapter<String> arrayAdapter;
                arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, mylist);
                simpleList.setAdapter(arrayAdapter);

            }
        }
    }

    //todo may not need this!
    public void getBillID() {

        Cursor allBills = RecurringExpenseRepo.getAllBills();

        allBills.moveToFirst();

        String bills, billType, amount, due;
        if(allBills != null && allBills.moveToFirst()) {

            do{

                // get id of the right bill

            }while ( allBills.moveToNext());


        }

    }


}

