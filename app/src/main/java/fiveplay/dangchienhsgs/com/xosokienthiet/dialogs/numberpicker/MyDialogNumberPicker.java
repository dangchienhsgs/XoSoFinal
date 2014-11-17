package fiveplay.dangchienhsgs.com.xosokienthiet.dialogs.numberpicker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import fiveplay.dangchienhsgs.com.xosokienthiet.R;


public class MyDialogNumberPicker extends DialogFragment {
    private final String TAG = "My number Picker Dialogs";

    private NumberPicker numberPicker;
    private NumberPickerListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_number_picker, null);

        numberPicker = (NumberPicker) view.findViewById(R.id.number_picker);
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(1);
        numberPicker.setWrapSelectorWheel(false);

        builder.setView(view);

        builder.setPositiveButton("Choose", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.onNumberPickerReturn(numberPicker.getValue());
            }
        });

        builder.setTitle("Pick number");
        return builder.create();
    }

    public void setListener(NumberPickerListener numberPickerListener) {
        this.mListener = numberPickerListener;
    }

    public void setInitnumber(int initnumber) {
        numberPicker.setValue(initnumber);
    }

    public interface NumberPickerListener {
        public void onNumberPickerReturn(int value);
    }
}
