package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fiveplay.dangchienhsgs.com.xosokienthiet.dialogs.datepicker.MyDatePickerDialogs;
import fiveplay.dangchienhsgs.com.xosokienthiet.model.VanTrinh;
import fiveplay.dangchienhsgs.com.xosokienthiet.service.ServiceUtilities;


public class VanTrinhFragment extends Fragment implements MyDatePickerDialogs.DatePickerListener{
    private int year;
    private int month;
    private int day;

    private Button buttonSearch;
    private EditText editBirth;
    private TextView textVanTrinh;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.fragment_van_trinh, container, false);
        return view;
    }

    public void onClickSearch(View view){
        MyDatePickerDialogs myDatePickerDialogs=new MyDatePickerDialogs();
        myDatePickerDialogs.show(getFragmentManager(), "DatePickerDialogs");
    }

    public void onClickBirthPicker(View view){
        MyDatePickerDialogs myDatePickerDialogs=new MyDatePickerDialogs();

        myDatePickerDialogs.show(getFragmentManager(), null);
    }

    private class UrlDownloadTask extends AsyncTask<String, String, String>{
        private int year;
        private int month;
        private int day;

        private UrlDownloadTask(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        @Override
        protected String doInBackground(String[] urls) {
            String result= ServiceUtilities.getNguHanh(day, month, year);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.trim().isEmpty()){
                Toast.makeText(getActivity(), "Server is error !", Toast.LENGTH_SHORT).show();
            } else {
                VanTrinh vanTrinh=VanTrinh.parse(result);

                if (vanTrinh==null){
                    Toast.makeText(getActivity(), "Server is error !", Toast.LENGTH_SHORT).show();
                } else {

                    textVanTrinh=(TextView) getView().findViewById(R.id.text_result_van_trinh);
                    textVanTrinh.setText(vanTrinh.getVantrinhTongThe());
                }
            }
        }
    }

    @Override
    public void onDatePickerReturn(int year, int month, int day) {
        this.year=year;
        this.month=month;
        this.day=day;
    }
}
