package team_18.financialadvisor;

import android.app.DialogFragment;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.DatePicker;




/**
 * Created by Avtar on 11/9/17.
 */

public class DatePickerFragment extends DialogFragment  implements DatePickerDialog.OnDateSetListener {

    private TextView pDisplayDate;
    private Button pPickDate;
    private int pYear;
    private int pMonth;
    private int pDay;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {



        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);




        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }


    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        ((TextView) getActivity().findViewById(R.id.text_displayDate)).setText(month +"/"+ day+"/" + year);

    }


}