package team_18.financialadvisor;

/**
 * Created by Avtar on 10/31/17.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class AddExpenses extends AppCompatActivity {
    //Text input/Edit
    EditText id, amtPerMonth, transactionCmt;

    String transactionType;
    double transactionAmount;
    String transactionComment;
    int transactionID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final PseudoDatabase database = new PseudoDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
        final Spinner mySpinner=(Spinner) findViewById(R.id.spinner_transaction_type);
        Button buttonGVGoToMM = (Button)findViewById(R.id.MMButtonGVGoToMM);
        final Button reset = (Button)findViewById(R.id.button_clear);
        Button addINcome = (Button)findViewById(R.id.button_add_income);



        buttonGVGoToMM.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(AddExpenses.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v==reset) {
                    startActivity(new Intent(AddExpenses.this, AddIncome.class));
                }
            }
        });

        addINcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting all the values from fields
                transactionType = mySpinner.getSelectedItem().toString();


                amtPerMonth = (EditText)findViewById(R.id.text_box_paid);
                transactionAmount = Double.parseDouble(amtPerMonth.getText().toString());

                id = (EditText)findViewById(R.id.input_transaction_ID);
                transactionID = Integer.parseInt(id.getText().toString());

                transactionCmt = (EditText)findViewById(R.id.text_box_transactionComment);
                transactionComment = transactionCmt.getText().toString();


                //temp db code
                database.newDatabaseEntry(transactionType, transactionAmount, transactionComment, transactionID);
                Toast.makeText(getApplicationContext(), "Transaction Added", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
