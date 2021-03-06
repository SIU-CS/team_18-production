package team_18.financialadvisor;

/**
 * Created by Avtar on 11/9/17.
 */


import android.annotation.SuppressLint;
import android.app.DialogFragment;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.DatePicker;


public class DatePickerFragment extends DialogFragment  implements DatePickerDialog.OnDateSetListener {

    private static TextView pDisplayDate;
    private static int pYear;
    private static int pMonth;
    private static int pDay;
    @SuppressWarnings("unused")
    static TextView thisDate;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        pYear = c.get(Calendar.YEAR);
        pMonth = c.get(Calendar.MONTH);
        pDay = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, pYear, pMonth, pDay);

    }

    @SuppressLint("SetTextI18n")
    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        pYear = year;
        pDay = day;
        pMonth = month;
        (pDisplayDate = (EditText) getActivity().findViewById(R.id.text_displayDate)).setText(month+1 +"/"+ day+"/" + year);

    }
    public static String getDate(){

        return pDisplayDate.getText().toString();
    }

}