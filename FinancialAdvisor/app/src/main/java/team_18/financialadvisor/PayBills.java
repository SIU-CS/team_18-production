package team_18.financialadvisor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.database.Cursor;

import java.text.ParseException;
import java.util.Calendar;

import team_18.financialadvisor.data.model.BudgetData;
import team_18.financialadvisor.data.model.NewTransaction;
import team_18.financialadvisor.data.repo.BudgetDataRepo;
import team_18.financialadvisor.data.repo.NewTransactionRepo;
import team_18.financialadvisor.data.repo.RecurringExpenseRepo;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by Avtar on 11/30/17.
 */

public class PayBills extends AppCompatActivity {
    private TextView textView;
    String dateDue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_bills);
        // set the format to sql date time
        final Date date = new Date();

        Button buttonBSGoToMM = (Button)findViewById(R.id.buttonPBToMM);
        Button buttonBSGoToGV = (Button)findViewById(R.id.buttonPBToCV);
        Button buttonBSGoToCV = (Button)findViewById(R.id.buttonPBToBS);
        Button payBill = (Button)findViewById(R.id.button_pay);

        //Setting button behavior
        buttonBSGoToMM.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(PayBills.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        buttonBSGoToCV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(PayBills.this, CalendarView.class);
                startActivity(myIntent);
            }
        });

        buttonBSGoToGV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(PayBills.this, BudgetSummary.class);
                startActivity(myIntent);
            }
        });

       setBillPayment();

        payBill.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Bundle b = getIntent().getExtras();
                int keyID = 0;
                keyID = b.getInt("key_val") + 1;

                NewTransaction addTransaction = new NewTransaction();
                NewTransactionRepo newTransaction = new NewTransactionRepo();
                Cursor billPaid = RecurringExpenseRepo.getBill(keyID);
                billPaid.moveToFirst();
                addTransaction.setTransactionID(AddExpenses.updateDB().getInt(7)+1);
                addTransaction.setTransactionAmount(billPaid.getDouble(1));

                addTransaction.setTransactionType(billPaid.getString(3));
                addTransaction.setTransactionComment(billPaid.getString(4));
                String fDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
                addTransaction.setDate(fDate);

                newTransaction.insert(addTransaction);
                RecurringExpenseRepo.setNewDate(getDate(), keyID);
               // setNewBudget(billPaid.getDouble(1));
                Intent myIntent = new Intent(PayBills.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

    }
    public void setBillPayment(){

        int keyID = 0;
        Bundle b = getIntent().getExtras();
        TextView billType = (TextView) findViewById(R.id.text_bill_type);
        TextView date = (TextView) findViewById(R.id.text_date);
        TextView amount = (TextView) findViewById(R.id.text_view_amout);
        TextView coment = (TextView) findViewById(R.id.text_comment);

        keyID = b.getInt("key_val") + 1;
        Cursor thisBill = RecurringExpenseRepo.getBill(keyID);
        thisBill.moveToFirst();
        billType.setText(thisBill.getString(3));
        date.setText("Due Date: " + thisBill.getString(5));
        amount.setText("$" + thisBill.getDouble(1) * -1.00);
        coment.setText(thisBill.getString(4));
    }

    /**
    public void setNewBudget(double paid){

        Cursor cursor = BudgetDataRepo.getAllData();
        cursor.moveToFirst();
        double budget  = cursor.getDouble(1);
       // BudgetDataRepo.updateBudget(budget);
    }
 */

    public String getDate() {
        Bundle b = getIntent().getExtras();
        SimpleDateFormat df = new SimpleDateFormat("mm/dd/yyyy");
        Date myDate = null;

        int keyID = 0;
        keyID = b.getInt("key_val") + 1;
        Cursor thisBill = RecurringExpenseRepo.getBill(keyID);
        thisBill.moveToFirst();


        dateDue = thisBill.getString(5);
        try {
            myDate = df.parse(dateDue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        //Update expenses in the recurring transaction and add to transactions table
        if (thisBill.getString(2).compareToIgnoreCase("Weekly") == 0)
        {

            cal.add(Calendar.DAY_OF_MONTH, 7);

        }
        else if(thisBill.getString(2).compareToIgnoreCase("Bi-Weekly") == 0)
        {


            cal.add(Calendar.DAY_OF_MONTH, 14);
        }
        else
        {
            cal.add(Calendar.MONTH, 1);

        }

        df = new SimpleDateFormat("mm/dd/yyyy");
        Date resultdate = new Date(cal.getTimeInMillis());
        String thisDate = df.format(resultdate);

        return thisDate;
    }

}
