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

        Button buttonBSGoToMM = (Button)findViewById(R.id.buttonPBToMM);
        Button payBill = (Button)findViewById(R.id.button_pay);

        //Setting button behavior
        buttonBSGoToMM.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(PayBills.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

       setBillPayment();

        payBill.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                TextView date = (TextView) findViewById(R.id.text_date);

                Bundle b = getIntent().getExtras();
                int keyID = 0;
                keyID = b.getInt("key_val") + 1;

                RecurringExpenseRepo.setNewDate(getDate(), keyID);
                Intent myIntent = new Intent(PayBills.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

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
        cal.add(Calendar.MONTH, 1);
        String thisDate = cal.get(Calendar.MONTH) +"/" +cal.get(Calendar.DAY_OF_MONTH)+"/"+ cal.get(Calendar.YEAR);
        return thisDate;
    }

}
