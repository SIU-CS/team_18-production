package team_18.financialadvisor;

/**
 * Created by Avtar on 10/31/17.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class AddIncome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        Button buttonGVGoToMM = (Button)findViewById(R.id.MMButtonGVGoToMM);
        final Button reset = (Button)findViewById(R.id.button_clear);

        buttonGVGoToMM.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(AddIncome.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v==reset) {
                    startActivity(new Intent(AddIncome.this, AddIncome.class));
                }
            }
        });

    }
}
