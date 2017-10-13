package team_18.financialadvisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button budgetSummary = (Button)findViewById(R.id.buttonBudgetSummary);

        //Setting button behavior
        budgetSummary.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(MainActivity.this, BudgetSummary.class);
                startActivity(myIntent);
            }
        });

        }

    }

