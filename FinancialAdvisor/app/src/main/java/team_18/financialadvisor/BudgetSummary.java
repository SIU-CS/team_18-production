package team_18.financialadvisor;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import team_18.financialadvisor.data.model.BudgetData;
import team_18.financialadvisor.data.model.NewTransaction;
import team_18.financialadvisor.data.repo.BudgetDataRepo;
import team_18.financialadvisor.data.repo.NewTransactionRepo;
import team_18.financialadvisor.data.repo.RecurringExpenseRepo;

public class BudgetSummary extends AppCompatActivity {
    EditText amountToT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_summary);

        Button buttonBSGoToMM = (Button)findViewById(R.id.buttonBSGoToMM);
        Button buttonBSGoToGV = (Button)findViewById(R.id.buttonBSGoToGV);
        Button buttonTM = (Button) findViewById(R.id.btn_transfer);
        final TextView accountText = (TextView) findViewById(R.id.text_account);

        ListView listIncome = (ListView) findViewById(R.id.BSListViewEarnings);
        ListView listExpenses = (ListView) findViewById(R.id.BSListViewExpenditures);
        final Spinner accountSpinner = (Spinner) findViewById(R.id.spiner_account);

        final int[] spinnerPos = new int[1];
        listItems(listIncome, listExpenses);
        //Setting button behavior
        buttonBSGoToMM.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(BudgetSummary.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        buttonBSGoToGV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(BudgetSummary.this, GraphView.class);
                startActivity(myIntent);
            }
        });

        accountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            // will run every time the user select an item from spinner
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                // change your textView here, base on position of item selected
                if (position == 0) {
                    spinnerPos[0] = 0;
                    accountText.setText("Savings");
                }
                else if (position == 1) {
                    spinnerPos[0] = 1;
                    accountText.setText("Budget/Checkings");
                }
            }
            //for default position of the spinner
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        buttonTM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor = BudgetDataRepo.getAllData();
                cursor.moveToFirst();
                double budget  = cursor.getDouble(1);
                double savings  = cursor.getDouble(3);

                //getting all the values from fields
                amountToT = (EditText)findViewById(R.id.text_transfer_amount);
                double T_Amount = Double.parseDouble(amountToT.getText().toString());

                //validate input
                if(spinnerPos[0] == 0){
                    if(T_Amount > budget){
                        Toast.makeText(getApplicationContext(), "You only have " + budget + " in your chekings", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        budget -= T_Amount;
                        savings += T_Amount;
                        //update db
                        BudgetDataRepo.updateBudget(budget);
                        BudgetDataRepo.updateSavings(savings);

                        //return to main
                        Intent myIntent = new Intent(BudgetSummary.this, MainActivity.class);
                        startActivity(myIntent);
                    }

                }
                else
                {
                    if(T_Amount > savings){
                        Toast.makeText(getApplicationContext(), "You only have " + savings + " in your savings", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        budget += T_Amount;
                        savings -= T_Amount;
                        //update db
                        BudgetDataRepo.updateBudget(budget);
                        BudgetDataRepo.updateSavings(savings);

                        //return to main
                        Intent myIntent = new Intent(BudgetSummary.this, MainActivity.class);
                        startActivity(myIntent);
                    }
                }

            }
        });

    }
    public void listItems(ListView listIncome, ListView  listExpenses) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Cursor allBills = NewTransactionRepo.getAllBills();
        ArrayList<String> income = new ArrayList<String>();
        ArrayList<String> expenses = new ArrayList<String>();

        allBills.moveToFirst();

        String bill, amount, due;
        if (allBills != null && allBills.moveToFirst()) {

            do {
                amount = allBills.getString(1);
                due = allBills.getString(4);
                bill = "$ " + amount + " On: " + due;
                if(allBills.getDouble(1) < 0.0)
                    expenses.add(bill);
                else
                    income.add(bill);

            } while (allBills.moveToNext());

            for (int i = 0; i <= 4; i++) {
                ArrayAdapter<String> arrayAdapter;
                ArrayAdapter<String> arrayAdapter2;

                arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, expenses);
                listExpenses.setAdapter(arrayAdapter);

                arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, income);
                listIncome.setAdapter(arrayAdapter2);

            }
        }
    }
}
