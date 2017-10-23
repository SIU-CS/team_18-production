package team_18.financialadvisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BudgetSummary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_summary);

        Button buttonBSGoToMM = (Button)findViewById(R.id.buttonBSGoToMM);
        Button buttonBSGoToGV = (Button)findViewById(R.id.buttonBSGoToGV);
        Button buttonBSGoToCV = (Button)findViewById(R.id.buttonBSGoToCV);

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
}
