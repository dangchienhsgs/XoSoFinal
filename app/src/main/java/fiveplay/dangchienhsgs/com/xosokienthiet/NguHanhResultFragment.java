package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fiveplay.dangchienhsgs.com.xosokienthiet.dialogs.datepicker.MyDatePickerDialogs;
import fiveplay.dangchienhsgs.com.xosokienthiet.model.NguHanh;
import fiveplay.dangchienhsgs.com.xosokienthiet.service.ServiceUtilities;
import fiveplay.dangchienhsgs.com.xosokienthiet.utils.PreferencesHandler;

/**
 * Created by dangchienbn on 19/11/2014.
 */
public class NguHanhResultFragment extends Fragment {

    private final String TAG = "Ngu Hanh Fragment";
    private int year;
    private int month;
    private int day;

    private TextView textNguHanh;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_ngu_hanh, container, false);
        initComponents(view);

        return view;
    }

    public void initComponents(View view) {
        textNguHanh = (TextView) view.findViewById(R.id.text_result_ngu_hanh);

        year = Integer.parseInt(PreferencesHandler.getValueFromPreferences(Common.KEY_NGU_HANH_YEAR, getActivity()));
        month = Integer.parseInt(PreferencesHandler.getValueFromPreferences(Common.KEY_NGU_HANH_MONTH, getActivity()));
        day = Integer.parseInt(PreferencesHandler.getValueFromPreferences(Common.KEY_NGU_HANH_DAY, getActivity()));

        new UrlDownloadTask(year, month, day).execute();
    }


    private class UrlDownloadTask extends AsyncTask<String, String, String> {
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
            Log.d(TAG, year + " " + month + " " + day);
            String result = ServiceUtilities.getNguHanh(year, month, day);
            Log.d(TAG, result);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, result);
            if (result.trim().isEmpty()) {
                Toast.makeText(getActivity(), "Server is error !", Toast.LENGTH_SHORT).show();
            } else {
                NguHanh nguHanh = NguHanh.parse(result);

                Log.d(TAG, result);

                if (nguHanh == null) {
                    Toast.makeText(getActivity(), "Server is error !", Toast.LENGTH_SHORT).show();
                } else {
                    textNguHanh.setText(Html.fromHtml(nguHanh.getContent()));
                }
            }
        }
    }
}
