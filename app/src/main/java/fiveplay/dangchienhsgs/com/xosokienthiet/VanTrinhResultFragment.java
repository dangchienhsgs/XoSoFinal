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
import fiveplay.dangchienhsgs.com.xosokienthiet.model.VanTrinh;
import fiveplay.dangchienhsgs.com.xosokienthiet.service.ServiceUtilities;
import fiveplay.dangchienhsgs.com.xosokienthiet.utils.PreferencesHandler;


public class VanTrinhResultFragment extends Fragment {
    private int year;
    private int month;
    private int day;


    private TextView textVanTrinh;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_van_trinh, container, false);

        initComponents(view);

        return view;
    }

    public void initComponents(View view) {
        textVanTrinh = (TextView) view.findViewById(R.id.text_result_van_trinh);

        year = Integer.parseInt(PreferencesHandler.getValueFromPreferences(Common.KEY_VAN_TRINH_YEAR, getActivity()));
        month = Integer.parseInt(PreferencesHandler.getValueFromPreferences(Common.KEY_VAN_TRINH_MONTH, getActivity()));
        day = Integer.parseInt(PreferencesHandler.getValueFromPreferences(Common.KEY_VAN_TRINH_DAY, getActivity()));

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
            String result = ServiceUtilities.getVanTrinh(year, month, day);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                if (result.trim().isEmpty()) {
                    Toast.makeText(getActivity(), "Server is error !", Toast.LENGTH_SHORT).show();
                } else {
                    VanTrinh vanTrinh = VanTrinh.parse(result);

                    if (vanTrinh == null) {
                        Toast.makeText(getActivity(), "Server is error !", Toast.LENGTH_SHORT).show();
                    } else {

                        textVanTrinh.setText(Html.fromHtml(vanTrinh.getVantrinhTongThe()));
                    }
                }
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

                dialog.show(VanTrinhResultFragment.this.getFragmentManager(), "Error Network Dialog");
            }

        }
    }
}
