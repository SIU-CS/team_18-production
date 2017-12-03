package team_18.financialadvisor;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import team_18.financialadvisor.data.repo.NewTransactionRepo;
import team_18.financialadvisor.data.repo.RecurringExpenseRepo;

public class BudgetSummary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_summary);


        Button buttonBSGoToMM = (Button)findViewById(R.id.buttonBSGoToMM);
        Button buttonBSGoToGV = (Button)findViewById(R.id.buttonBSGoToGV);
        Button buttonBSGoToCV = (Button)findViewById(R.id.buttonBSGoToCV);
        ListView listIncome = (ListView) findViewById(R.id.BSListViewEarnings);
        ListView listExpenses = (ListView) findViewById(R.id.BSListViewExpenditures);

        listItems(listIncome, listExpenses);
        //Setting button behavior
        buttonBSGoToMM.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(BudgetSummary.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        buttonBSGoToCV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(BudgetSummary.this, CalendarView.class);
                startActivity(myIntent);
            }
        });

        buttonBSGoToGV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(BudgetSummary.this, GraphView.class);
                startActivity(myIntent);
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
