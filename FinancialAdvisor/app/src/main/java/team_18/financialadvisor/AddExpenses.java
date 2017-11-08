package team_18.financialadvisor;

/**
 * Created by Avtar on 10/31/17.
 * Revised by Ian on 11/1/17.
 */


import team_18.financialadvisor.data.model.BudgetData;
import team_18.financialadvisor.data.model.NewTransaction;
import team_18.financialadvisor.data.repo.NewTransactionRepo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class AddExpenses extends AppCompatActivity {
    //Text input/Edit
    EditText id, amtPerMonth, transactionCmt, recurring;

    String transactionType;
    double transactionAmount;
    String transactionComment;
    int transactionID;
    int transaction_recurring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final BudgetData database = new BudgetData();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
        final Spinner mySpinner=(Spinner) findViewById(R.id.spinner_transaction_type);
        Button buttonGVGoToMM = (Button)findViewById(R.id.MMButtonGVGoToMM);
        final Button reset = (Button)findViewById(R.id.button_clear);
        Button addINcome = (Button)findViewById(R.id.button_add_income);



        buttonGVGoToMM.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(AddExpenses.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v==reset) {
                    startActivity(new Intent(AddExpenses.this, AddIncome.class));
                }
            }
        });

        addINcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting all the values from fields
                transactionType = mySpinner.getSelectedItem().toString();


                amtPerMonth = (EditText)findViewById(R.id.text_box_paid);
                transactionAmount = Double.parseDouble(amtPerMonth.getText().toString());

                recurring = (EditText)findViewById(R.id.expenses_every);
                transaction_recurring = Integer.parseInt(recurring.getText().toString());


                id = (EditText)findViewById(R.id.input_transaction_ID);
                transactionID = Integer.parseInt(id.getText().toString());

                transactionCmt = (EditText)findViewById(R.id.text_box_transactionComment);
                transactionComment = transactionCmt.getText().toString();

                //add transaction to table
                NewTransactionRepo addTrRepo = new NewTransactionRepo();
                NewTransaction addTransaction = new NewTransaction();

                addTransaction.setTransactionID(transactionID);
                addTransaction.setTransactionAmount(transactionAmount);
                addTransaction.setTransactionRecurring(transaction_recurring);
                addTransaction.setTransactionType(transactionType);
                addTransaction.setTransactionComment(transactionComment);
                addTrRepo.insert(addTransaction);


                Toast.makeText(getApplicationContext(), "Transaction Added", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
