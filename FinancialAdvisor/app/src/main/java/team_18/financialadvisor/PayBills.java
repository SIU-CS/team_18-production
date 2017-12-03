package team_18.financialadvisor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.database.Cursor;

import team_18.financialadvisor.data.repo.RecurringExpenseRepo;

/**
 * Created by Avtar on 11/30/17.
 */

public class PayBills extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_bills);

        Button buttonBSGoToMM = (Button)findViewById(R.id.buttonPBToMM);
        Button buttonBSGoToGV = (Button)findViewById(R.id.buttonPBToCV);
        Button buttonBSGoToCV = (Button)findViewById(R.id.buttonPBToBS);

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

    }
    public void setBillPayment(){
        Bundle b = getIntent().getExtras();
        TextView billType = (TextView) findViewById(R.id.text_bill_type);
        TextView date = (TextView) findViewById(R.id.text_date);
        TextView amount = (TextView) findViewById(R.id.text_view_amout);
        TextView coment = (TextView) findViewById(R.id.text_comment);
        int keyID = 0;
        keyID = b.getInt("key_val") + 1;
        Cursor thisBill = RecurringExpenseRepo.getBill(keyID);
        thisBill.moveToFirst();
        billType.setText(thisBill.getString(3));
        date.setText("Due Date: " + thisBill.getString(5));
        amount.setText("$" + thisBill.getDouble(1) * -1.00);
        coment.setText(thisBill.getString(4));
    }
}
