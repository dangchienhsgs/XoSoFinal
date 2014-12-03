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

import fiveplay.dangchienhsgs.com.xosokienthiet.dialogs.alerterror.NetworkErrorDialog;
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
            String result = ServiceUtilities.getNguHanh(year, month, day);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                NguHanh nguHanh = NguHanh.parse(result);

                textNguHanh.setText(Html.fromHtml(nguHanh.getContent()));

            } catch (Exception e) {
                NetworkErrorDialog dialog = new NetworkErrorDialog();
                dialog.setTitle("Thông báo");
                dialog.setContent("Lỗi mạng hoặc lỗi server, ấn retry để kết nối lại !");
                dialog.setListener(new NetworkErrorDialog.OnRetryListener() {
                    @Override
                    public void onDialogRetry() {
                        new UrlDownloadTask(year, month, day).execute();
                    }

                    @Override
                    public void onDialogClose() {
                        getActivity().onBackPressed();
                    }
                });

                dialog.show(NguHanhResultFragment.this.getFragmentManager(), "Error Network Dialog");
            }
        }
    }
}
