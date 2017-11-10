package team_18.financialadvisor;

/**
 * Created by Avtar on 10/31/17.
 * Revised by Ian on 11/1/17.
 */

import team_18.financialadvisor.data.model.NewTransaction;
import team_18.financialadvisor.data.repo.BudgetDataRepo;
import team_18.financialadvisor.data.repo.NewTransactionRepo;
import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Date;


public class AddExpenses extends AppCompatActivity {
    //Text input/Edit
    EditText amtPerMonth, transactionCmt;

    String transactionType, transactionComment , transaction_recurring;
    double transactionAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // set the format to sql date time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date date = new Date();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
        final Spinner mySpinner=(Spinner) findViewById(R.id.spinner_transaction_type);
        final Spinner tSpinner= (Spinner) findViewById(R.id.expenses_every);
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
                    startActivity(new Intent(AddExpenses.this, AddExpenses.class));
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

                transaction_recurring = tSpinner.getSelectedItem().toString();

                transactionCmt = (EditText)findViewById(R.id.text_box_transactionComment);
                transactionComment = transactionCmt.getText().toString();

                //add transaction to table
                NewTransactionRepo addTrRepo = new NewTransactionRepo();
                NewTransaction addTransaction = new NewTransaction();

                addTransaction.setTransactionID(updateDB().getInt(8)+1);
                addTransaction.setTransactionAmount(-transactionAmount);
                addTransaction.setTransactionRecurring(transaction_recurring);
                addTransaction.setTransactionType(transactionType);
                addTransaction.setTransactionComment(transactionComment);
                addTransaction.setDate(date.toString());

                addTrRepo.insert(addTransaction);

                Toast.makeText(getApplicationContext(), "Transaction Added", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(AddExpenses.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

    }



    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");

    }
    //get cursor object from the Budget Database and use it to get values
    public Cursor updateDB(){

        Cursor cursor = BudgetDataRepo.getAllData();
        if(cursor != null)
            cursor.moveToFirst();

        return cursor;
    }
}
