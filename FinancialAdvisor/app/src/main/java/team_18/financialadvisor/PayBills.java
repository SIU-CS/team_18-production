package team_18.financialadvisor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Avtar on 11/30/17.
 */

public class PayBills extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_bills);

        Button buttonBSGoToMM = (Button)findViewById(R.id.buttonBSGoToMM);
        Button buttonBSGoToGV = (Button)findViewById(R.id.buttonBSGoToGV);
        Button buttonBSGoToCV = (Button)findViewById(R.id.buttonBSGoToCV);

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
                Intent myIntent = new Intent(PayBills.this, GraphView.class);
                startActivity(myIntent);
            }
        });

    }
}
