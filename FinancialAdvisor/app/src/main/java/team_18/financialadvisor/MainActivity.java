package team_18.financialadvisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
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
        final PseudoDatabase database = new PseudoDatabase();
        PseudoUpcomingDatabase upcoming = new PseudoUpcomingDatabase();
        final FinancialHealthStatus health = new FinancialHealthStatus(database);

        //Declaring Financial Health EditText
        EditText healthText = (EditText)findViewById(R.id.MMEditTextFinancialHealth);
        healthText.setText(health.generateStatus(database), TextView.BufferType.EDITABLE);

        //Entries

        database.newDatabaseEntry("Bill", -120.00, "Electricity Bill", 1);
        database.newDatabaseEntry("Paycheck", 1000.00, "Payday", 2);
        database.newDatabaseEntry("Car payment", -200.00, "Car Payment", 3);

        //Financial health status generation
        health.generateStatus(database);
<<<<<<< Updated upstream

        //Declaring Budget EditText
        final EditText budgetText = (EditText)findViewById(R.id.MMEditTextCurrentBudget);
        budgetText.setText(database.budgetToString());

        //Initial ListView creation
        refreshList(database);

        //Setting button behaviors

        //TODO: TEST BUTTONS, REMOVE LATER
        testBudget.setOnClickListener(new View.OnClickListener() {
                                          public void onClick(View v) {
                                              database.newDatabaseEntry("Vet Bill", -100.00, "Fido's Medication", 4);
                                              refreshList(database);
                                              health.generateStatus(database);
                                              budgetText.setText(database.budgetToString());
                                          }
                                      });
        testBudget2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                database.newDatabaseEntry("Birthday Present", 100.00, "B-Day Present from Mom", 5);
                refreshList(database);
                health.generateStatus(database);
                budgetText.setText(database.budgetToString());
            }
        });

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

            }
        });

        MMButtonSubtractFromBudget.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }

    }

    //Upcoming bills ListView
    public void refreshList(PseudoDatabase database) {
        ArrayList<String> bills = new ArrayList<>();
        PseudoDatabaseEntry pos = database.root;
        for (int i = 0; i <= (database.getNumOfEntries() - 1); i++) {
            bills.add(i, pos.toString());
            if (pos.nextEntry != null) {
                pos = pos.nextEntry;
            }
        }

        ListView simpleList;
        ArrayAdapter<String> arrayAdapter;


        simpleList = (ListView) findViewById(R.id.MMListViewUpcomingBills);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, bills);
        simpleList.setAdapter(arrayAdapter);
    }

}
=======
>>>>>>> development

