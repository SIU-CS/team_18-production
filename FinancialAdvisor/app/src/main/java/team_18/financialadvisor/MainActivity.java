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
import team_18.financialadvisor.data.model.BudgetData;
import team_18.financialadvisor.data.model.RecIncome;
import team_18.financialadvisor.data.repo.RecurringExpenseRepo;

public class MainActivity extends AppCompatActivity {
    @SuppressWarnings("unused")
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //todo fix month
        //RecIncome.setIncome();
        //Declaring button
        Button buttonMMGoToNext = (Button) findViewById(R.id.buttonMMGoToNext);
        Button buttonMMGoToAddIncome = (Button) findViewById(R.id.buttonMMGoToAddIncome);
        Button MMButtonSubtractFromBudget = (Button) findViewById(R.id.MMButtonSubtractFromBudget);
        ListView simpleList = (ListView) findViewById(R.id.MMListViewUpcomingBills);

        //Financial Health status stuff
        final FinancialHealthStatus health =new FinancialHealthStatus();
        final EditText healthText = (EditText)findViewById(R.id.MMEditTextFinancialHealth);
        healthText.setText(health.generateStatus(), TextView.BufferType.EDITABLE);

        //list bills
        listItems(simpleList);

        //Declaring Budget EditText
        BudgetData newData = App.getDBdata();
        final EditText budgetText = (EditText)findViewById(R.id.MMEditTextCurrentBudget);
        budgetText.setText(String.valueOf(newData.getCurrentBalance()));


        //Setting button behaviors
        buttonMMGoToNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, BudgetSummary.class);
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
                        @SuppressWarnings("unused") String bill = String.valueOf(parent.getItemAtPosition(position));

                       // Toast.makeText(MainActivity.this, " Index " + position, Toast.LENGTH_LONG).show();

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
        ArrayList<String> myList = new ArrayList<String>();

        allBills.moveToFirst();

        String bills, billType, amount, due;
        if (allBills != null && allBills.moveToFirst()) {

            do {

                billType = allBills.getString(3);
                amount = allBills.getString(1);
                due = allBills.getString(5);
                bills = "$ " + amount + "   " + billType + " Due On: " + due;
                myList.add(bills);

            } while (allBills.moveToNext());

            for (int i = 0; i <= 4; i++) {

                ArrayAdapter<String> arrayAdapter;
                arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, myList);
                simpleList.setAdapter(arrayAdapter);

            }
        }
    }

}

