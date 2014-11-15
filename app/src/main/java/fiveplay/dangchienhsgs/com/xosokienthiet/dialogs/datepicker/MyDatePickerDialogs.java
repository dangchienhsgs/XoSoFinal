package fiveplay.dangchienhsgs.com.xosokienthiet.dialogs.datepicker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import fiveplay.dangchienhsgs.com.xosokienthiet.R;


public class MyDatePickerDialogs extends DialogFragment {
    private final String TAG="My Date Picker Dialogs";

    private int day;
    private int month;
    private int year;

    private DatePicker datePicker;
    private DatePickerListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date_picker, null);

        datePicker = (DatePicker) view.findViewById(R.id.date_picker);

        datePicker.updateDate(year, month, day);

        builder.setView(view);

        builder.setPositiveButton("Choose", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.onDatePickerReturn(
                        datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth()
                );
            }
        });

        builder.setTitle("Pick date");
        return builder.create();
    }

    public void setDatePickerListener(DatePickerListener datePickerListener) {
        this.mListener = datePickerListener;
    }

    public void setInitDate(int year, int month, int day) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public interface DatePickerListener {
        public void onDatePickerReturn(int year, int month, int day);
    }
}
