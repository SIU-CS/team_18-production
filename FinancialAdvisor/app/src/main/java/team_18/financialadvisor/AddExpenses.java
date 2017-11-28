package team_18.financialadvisor;

/**
 * Created by Avtar on 10/31/17.
 * Revised by Ian on 11/1/17.
 */

import team_18.financialadvisor.data.model.NewTransaction;
import team_18.financialadvisor.data.repo.BudgetDataRepo;
import team_18.financialadvisor.data.repo.NewTransactionRepo;
import team_18.financialadvisor.data.repo.RecurringExpenseRepo;
import team_18.financialadvisor.data.repo.RecurringIncomeRepo;

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
import android.widget.CheckBox;
import android.view.View.OnClickListener;


import java.text.SimpleDateFormat;
import java.util.Date;


public class AddExpenses extends AppCompatActivity {
    //Text input/Edit
    EditText amtPerMonth, transactionCmt;

    String transactionType, transactionComment , transaction_recurring;
    double transactionAmount;
    private CheckBox chkRecurring;
    boolean isRecurring = true;

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

        Button addIncome = (Button)findViewById(R.id.button_add_income);

        addListenerOnChkRecurring();


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

        addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getting all the values from fields
                transactionType = mySpinner.getSelectedItem().toString();


                amtPerMonth = (EditText)findViewById(R.id.text_box_paid);
                transactionAmount = Double.parseDouble(amtPerMonth.getText().toString());

                transaction_recurring = tSpinner.getSelectedItem().toString();

                transactionCmt = (EditText)findViewById(R.id.text_box_transactionComment);
                transactionComment = transactionCmt.getText().toString();

                //add transaction to table depending on type
                NewTransactionRepo addTrRepo = new NewTransactionRepo();
                NewTransaction addTransaction = new NewTransaction();
                RecurringExpenseRepo addExpRepo = new RecurringExpenseRepo();


                addTransaction.setTransactionID(updateDB().getInt(7)+1);
                addTransaction.setTransactionAmount(-transactionAmount);
                addTransaction.setTransactionRecurring(transaction_recurring);
                addTransaction.setTransactionType(transactionType);
                addTransaction.setTransactionComment(transactionComment);


                if (isRecurring == true) {
                    addTransaction.setDate(DatePickerFragment.getDate());
                    addExpRepo.insert(addTransaction);

                }
                else
                {
                    addTransaction.setDate(date.toString());
                    addTrRepo.insert(addTransaction);

                }

                Intent myIntent = new Intent(AddExpenses.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

    }

    public void addListenerOnChkRecurring() {

        chkRecurring = (CheckBox) findViewById(R.id.isRecurring);

        chkRecurring.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    findViewById(R.id.expenses_every).setVisibility(View.VISIBLE);
                    findViewById(R.id.datePicker).setVisibility(View.VISIBLE);
                    findViewById(R.id.text_displayDate).setVisibility(View.VISIBLE);
                    //Toast.makeText(getApplicationContext(), "is recurring = true", Toast.LENGTH_SHORT).show();
                    isRecurring = true;
                }
                else{
                    findViewById(R.id.expenses_every).setVisibility(View.GONE);
                    findViewById(R.id.datePicker).setVisibility(View.GONE);
                    findViewById(R.id.text_displayDate).setVisibility(View.GONE);
                    //Toast.makeText(getApplicationContext(), "is recurring = false", Toast.LENGTH_SHORT).show();
                    isRecurring = false;
                }
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
