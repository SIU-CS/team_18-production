package team_18.financialadvisor;

/**
 * Created by Avtar on 10/31/17.
 * Revised by Ian on 11/1/17
 */

import team_18.financialadvisor.data.model.BudgetData;
import team_18.financialadvisor.data.model.NewTransaction;
import team_18.financialadvisor.data.repo.BudgetDataRepo;
import team_18.financialadvisor.data.repo.NewTransactionRepo;
import team_18.financialadvisor.data.repo.RecurringIncomeRepo;

import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.CheckBox;
import android.view.View.OnClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddIncome extends AppCompatActivity {
    EditText amtPerMonth, transactionCmt;

    String transactionType, transaction_recurring, transactionComment;
    double transactionAmount;
    boolean isRecurring = true;

    private RadioGroup radioIncomeGroup;
    private RadioButton radioIncomeButton;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        // set the format to sql date time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date date = new Date();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        final Spinner tSpinner = (Spinner) findViewById(R.id.income_every);
        Button buttonGVGoToMM = (Button) findViewById(R.id.MMButtonGVGoToMM);
        final Button reset = (Button) findViewById(R.id.button_clear);
        Button addIncome = (Button) findViewById(R.id.button_add_income);

        addListenerOnChkRecurring();

        //Go to main menu button
        buttonGVGoToMM.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(AddIncome.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        //Clear button
        reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v == reset) {
                    startActivity(new Intent(AddIncome.this, AddIncome.class));
                }
            }
        });

        //done button
        addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting all the values from fields
                transactionType = radioIncomeButton.toString();


                amtPerMonth = (EditText) findViewById(R.id.text_box_income);
                transactionAmount = Double.parseDouble(amtPerMonth.getText().toString());

                transaction_recurring = tSpinner.getSelectedItem().toString();

                transactionCmt = (EditText) findViewById(R.id.text_box_transactionComment);
                transactionComment = transactionCmt.getText().toString();

                //add transaction to table
                NewTransactionRepo addTrRepo = new NewTransactionRepo();
                NewTransaction addTransaction = new NewTransaction();
                RecurringIncomeRepo addIncomeRepo = new RecurringIncomeRepo();

                addTransaction.setTransactionID(updateDB().getInt(7) + 1);
                addTransaction.setTransactionAmount(transactionAmount);
                addTransaction.setTransactionRecurring(transaction_recurring);
                addTransaction.setTransactionType(transactionType);
                addTransaction.setTransactionComment(transactionComment);

                if (isRecurring == true) {
                    addTransaction.setDate(DatePickerFragment.getDate());
                    addIncomeRepo.insert(addTransaction);
                    Toast.makeText(getApplicationContext(), "Recurring Income Added", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    addTransaction.setDate(date.toString());
                    addTrRepo.insert(addTransaction);
                    Toast.makeText(getApplicationContext(), "Transaction Added", Toast.LENGTH_SHORT).show();
                }

                Intent myIntent = new Intent(AddIncome.this, MainActivity.class);
                startActivity(myIntent);

            }


        });

    }


    public void onItemSelected(AdapterView<?> spinnerOption, GridLayout view, int pos, long id)
    {
        spinnerOption.setVisibility(View.VISIBLE);
    }

    public void addListenerOnChkRecurring() {

        radioIncomeGroup = (RadioGroup) findViewById(R.id.radioIncomeType);
        RadioGroup checkRecurring = (RadioGroup) findViewById(R.id.radioIncomeType);
        RadioButton radio1= (RadioButton)checkRecurring.findViewById(R.id.radioWages);
        RadioButton radio2= (RadioButton)checkRecurring.findViewById(R.id.radioOther);

        radio1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                findViewById(R.id.grid_wages).setVisibility(View.VISIBLE);
                findViewById(R.id.income_every).setVisibility(View.VISIBLE);
                findViewById(R.id.datePicker).setVisibility(View.VISIBLE);
                findViewById(R.id.text_displayDate).setVisibility(View.VISIBLE);

                isRecurring = true;
                Toast.makeText(getApplicationContext(), "Wages", Toast.LENGTH_SHORT).show();
            }
        });

        radio2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                findViewById(R.id.grid_wages).setVisibility(View.GONE);
                findViewById(R.id.income_every).setVisibility(View.GONE);
                findViewById(R.id.datePicker).setVisibility(View.GONE);
                findViewById(R.id.text_displayDate).setVisibility(View.GONE);

                isRecurring = false;
                Toast.makeText(getApplicationContext(), "Other", Toast.LENGTH_SHORT).show();
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
