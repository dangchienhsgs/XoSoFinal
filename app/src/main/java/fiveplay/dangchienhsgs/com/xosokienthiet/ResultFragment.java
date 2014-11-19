package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fiveplay.dangchienhsgs.com.xosokienthiet.Common;
import fiveplay.dangchienhsgs.com.xosokienthiet.R;
import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.FourColumnArrayAdapter;
import fiveplay.dangchienhsgs.com.xosokienthiet.dialogs.datepicker.MyDatePickerDialogs;
import fiveplay.dangchienhsgs.com.xosokienthiet.model.LotteryResult;
import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.TwoColumnArrayAdapter;
import fiveplay.dangchienhsgs.com.xosokienthiet.service.ServiceUtilities;
import fiveplay.dangchienhsgs.com.xosokienthiet.utils.URLBuilder;

public class ResultFragment extends Fragment implements Button.OnClickListener {
    private Button buttonNorth;
    private Button buttonMiddle;
    private Button buttonSouth;

    private LinearLayout layoutGroupCompanies;

    private ListView listResult;
    private ListView listHeadTail;


    private String[] choosingCompanies;
    private String[] choosingCompaniesID;


    private int day;
    private int month;
    private int year;

    private int choosingButton = 0;


    private String choosingArea;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        initComponents(view);

        return view;
    }

    public void initComponents(View view) {

        buttonNorth = (Button) view.findViewById(R.id.button_area_north);
        buttonMiddle = (Button) view.findViewById(R.id.button_area_middle);
        buttonSouth = (Button) view.findViewById(R.id.button_area_south);

        buttonNorth.setOnClickListener(this);
        buttonMiddle.setOnClickListener(this);
        buttonSouth.setOnClickListener(this);


        layoutGroupCompanies = (LinearLayout) view.findViewById(R.id.layout_group_companies);

        listResult = (ListView) view.findViewById(R.id.list_prize_and_value);
        listHeadTail = (ListView) view.findViewById(R.id.list_lotto_head_tail);

    }

    public void setDate(int year, int month, int day) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button_area_north:
                choosingCompanies = Common.COMPANIES_IN_NORTH;
                choosingCompaniesID = Common.COMPANIES_IN_NORTH_ID;
                break;
            case R.id.button_area_middle:
                choosingCompanies = Common.COMPANIES_IN_MIDDLE;
                choosingCompaniesID = Common.COMPANIES_IN_MIDDLE_ID;
                break;
            case R.id.button_area_south:
                choosingCompanies = Common.COMPANIES_IN_SOUTH;
                choosingCompaniesID = Common.COMPANIES_IN_SOUTH_ID;
                break;
        }

        layoutGroupCompanies.removeAllViews();

        for (int i = 0; i < choosingCompanies.length; i++) {

            final String company = choosingCompanies[i];

            // Add button to the row
            Button button = new Button(getActivity());
            button.setText(company);
            button.setTag(choosingCompaniesID[i]);

            // Set onClickListener for the Button
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Reset title for the activity
                    getActivity().getActionBar().setTitle(company);

                    // Get the company code
                    String code = (String) view.getTag();

                    // load Result
                    loadResult(code);

                }
            });

            layoutGroupCompanies.addView(button);
        }
    }

    public void loadResult(String code) {
        new DownloadResultTask(code).execute();
    }

    private class DownloadResultTask extends AsyncTask<Void, String, String> {
        private static final String TAG = "Download Result Task";

        private String code;

        DownloadResultTask(String code) {
            this.code = code;
        }

        @Override
        protected String doInBackground(Void... voids) {
            URLBuilder urlBuilder = new URLBuilder(URLBuilder.URL_KET_QUA);
            urlBuilder.append(
                    Common.LOCATION_CODE,
                    code);
            urlBuilder.append(Common.DATE, year + "-" + month + "-" + day);

            String url = urlBuilder.create();

            Log.d(TAG, url);

            String result = ServiceUtilities.sendGet(url, null);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, result);

            LotteryResult lottoResult = new LotteryResult(result);
            Log.d(TAG, lottoResult.getLottoHeadTail().size() + "");
            Log.d(TAG, lottoResult.getLottoTailHead().size() + "");

            TwoColumnArrayAdapter lottoAdapter = new TwoColumnArrayAdapter(
                    getActivity(),
                    R.layout.row_two_columns,
                    R.id.text_first_column,
                    Arrays.asList(Common.PRIZE_NAME),
                    lottoResult.getPrize()
            );


            Log.d(TAG, lottoResult.getLottoHeadTail().toString());
            Log.d(TAG, lottoResult.getLottoTailHead().toString());

            List<String> listDigits = new ArrayList<String>();
            for (String str : Common.DIGITS) {
                listDigits.add(str);
            }

            listDigits.add(0, "Đầu");
            lottoResult.getLottoHeadTail().add(0, "Đuôi");
            lottoResult.getLottoTailHead().add(0, "Đuôi");
            FourColumnArrayAdapter loAdapter = new FourColumnArrayAdapter(
                    getActivity(),
                    R.layout.row_four_columns,
                    R.id.text_first_column,
                    listDigits,
                    lottoResult.getLottoHeadTail(),
                    lottoResult.getLottoTailHead(),
                    listDigits
            );


            listHeadTail.setAdapter(loAdapter);
            loAdapter.notifyDataSetChanged();

            listResult.setAdapter(lottoAdapter);
            lottoAdapter.notifyDataSetChanged();
        }
    }
}
