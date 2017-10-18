package team_18.financialadvisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GraphView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view);

        Button buttonGVGoToBS = (Button)findViewById(R.id.buttonGVGoToBS);
        Button buttonGVGoToMM = (Button)findViewById(R.id.buttonGVGoToMM);
        Button buttonGVGoToCV = (Button)findViewById(R.id.buttonGVGoToCV);

        //Setting button behavior
        buttonGVGoToBS.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(GraphView.this, BudgetSummary.class);
                startActivity(myIntent);
            }
        });

        buttonGVGoToMM.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(GraphView.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        buttonGVGoToCV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(GraphView.this, CalendarView.class);
                startActivity(myIntent);
            }
        });

    }
}
