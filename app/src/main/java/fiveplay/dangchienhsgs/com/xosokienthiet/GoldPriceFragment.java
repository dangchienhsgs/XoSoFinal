package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.FourColumnArrayAdapter;
import fiveplay.dangchienhsgs.com.xosokienthiet.dialogs.alerterror.NetworkErrorDialog;
import fiveplay.dangchienhsgs.com.xosokienthiet.service.ServiceUtilities;
import fiveplay.dangchienhsgs.com.xosokienthiet.utils.StringUtils;


public class GoldPriceFragment extends Fragment {
    private final String TAG = "Gold Price Fragment";

    private String goldInfo;
    private String moneyInfo;

    private ListView listGoldPrice;
    private ListView listMoneyPrice;

    private HashMap<String, String> goldMap;
    private HashMap<String, String> moneyMap;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_gold_price, container, false);
        listGoldPrice = (ListView) view.findViewById(R.id.list_gold_price);
        listMoneyPrice = (ListView) view.findViewById(R.id.list_money_price);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DownloadResultTask(year, month, day).execute();


        EasyTracker.getInstance(getActivity()).send(MapBuilder.createEvent(
                "Gia vang " + year + "/" + month + "/" + day,
                "View Button",
                "Van Trinh Day Picker Fragment",
                null).build());

        return view;
    }


    public void analyzeResult(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray information = jsonObject.getJSONArray("mRateMessagesList");

            JSONObject goldPrice = information.getJSONObject(0);
            JSONObject moneyPrice = information.getJSONObject(1);

            goldInfo = goldPrice.getString("MessageContent");
            moneyInfo = moneyPrice.getString("MessageContent");

            goldMap = StringUtils.analyzeGOLD(StringUtils.goldCompanies, goldInfo);
            moneyMap = StringUtils.analyzeGOLD(StringUtils.moneyCountries, moneyInfo);

            updateView();
        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
            NetworkErrorDialog dialog = new NetworkErrorDialog();
            dialog.setTitle("Thông báo");
            dialog.setContent("Lỗi mạng hoặc lỗi server, ấn retry để kết nối lại !");
            dialog.setListener(new NetworkErrorDialog.OnRetryListener() {
                @Override
                public void onDialogRetry() {
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    new DownloadResultTask(year, month, day).execute();
                }

                @Override
                public void onDialogClose() {
                    getActivity().onBackPressed();
                }
            });

            dialog.show(GoldPriceFragment.this.getFragmentManager(), "Error Network Dialog");
        }
    }

    public void updateView() {

        List<String> listIndex = new ArrayList<String>();

        for (int i = 0; i < StringUtils.goldCompanies.length; i++) {
            listIndex.add(String.valueOf(i + 1));
        }

        List<String> goldCompanies = new ArrayList<String>();
        List<String> goldIn = new ArrayList<String>();
        List<String> goldOut = new ArrayList<String>();

        for (String key : goldMap.keySet()) {
            String value = goldMap.get(key);

            goldCompanies.add(key);
            goldIn.add(value.split("-")[0]);
            goldOut.add(value.split("-")[1]);

        }

        FourColumnArrayAdapter goldAdapter = new FourColumnArrayAdapter(
                getActivity(),
                R.layout.row_giavang,
                R.id.text_first_column,
                listIndex,
                goldCompanies,
                goldIn,
                goldOut
        );

        listGoldPrice.setAdapter(goldAdapter);

        List<String> countries = new ArrayList<String>();
        List<String> moneyIn = new ArrayList<String>();
        List<String> moneyCK = new ArrayList<String>();
        List<String> moneyOut = new ArrayList<String>();

        for (String key : moneyMap.keySet()) {
            String value = moneyMap.get(key);

            countries.add(key);
            moneyIn.add(value.split("-")[0]);
            moneyCK.add(value.split("-")[1]);
            moneyOut.add(value.split("-")[2]);
        }

        FourColumnArrayAdapter moneyAdapter = new FourColumnArrayAdapter(
                getActivity(),
                R.layout.row_giavang,
                R.id.text_first_column,
                countries,
                moneyIn,
                moneyCK,
                moneyOut
        );

        listMoneyPrice.setAdapter(moneyAdapter);
    }

    private class DownloadResultTask extends AsyncTask<Void, String, String> {
        private int year;
        private int month;
        private int day;

        private DownloadResultTask(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }


        @Override
        protected String doInBackground(Void... voids) {
            String result = ServiceUtilities.getGiaVang(year, month, day);
            return result;
        }


        @Override
        protected void onPostExecute(String result) {
            analyzeResult(result);
        }
    }
}
