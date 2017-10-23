package team_18.financialadvisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalendarView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);

        Button buttonCVGoToBS = (Button)findViewById(R.id.buttonCVGoToBS);
        Button buttonCVGoToMM = (Button)findViewById(R.id.buttonCVGoToMM);
        Button buttonCVGoToGV = (Button)findViewById(R.id.buttonCVGoToGV);

        //Setting button behavior
        buttonCVGoToMM.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(CalendarView.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        buttonCVGoToBS.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(CalendarView.this, BudgetSummary.class);
                startActivity(myIntent);
            }
        });

        buttonCVGoToGV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(CalendarView.this, GraphView.class);
                startActivity(myIntent);
            }
        });

    }
}
