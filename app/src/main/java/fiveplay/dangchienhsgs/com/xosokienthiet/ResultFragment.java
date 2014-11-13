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

import java.util.Arrays;

import fiveplay.dangchienhsgs.com.xosokienthiet.Common;
import fiveplay.dangchienhsgs.com.xosokienthiet.R;
import fiveplay.dangchienhsgs.com.xosokienthiet.dialogs.datepicker.MyDatePickerDialogs;
import fiveplay.dangchienhsgs.com.xosokienthiet.model.LotteryResult;
import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.TwoColumnArrayAdapter;
import fiveplay.dangchienhsgs.com.xosokienthiet.service.ServiceUtilities;
import fiveplay.dangchienhsgs.com.xosokienthiet.utils.URLBuilder;

public class ResultFragment extends Fragment implements MyDatePickerDialogs.DatePickerListener, Button.OnClickListener {
    private Button buttonNorth;
    private Button buttonMiddle;
    private Button buttonSouth;

    private LinearLayout layoutGroupCompanies;

    private ListView listResult;
    private ListView listHeadTail;


    private int day;
    private int month;
    private int year;

    private int choosingButton = 0;

    private String choosingArea;
    private String choosingCompany;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_result, container, false);
        initComponents(view);

        return view;
    }

    public void initComponents(View view){

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

    @Override
    public void onDatePickerReturn(int year, int month, int day) {
        Toast.makeText(getActivity(), "" + year + month + day, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        String[] companies = Common.COMPANIES_IN_NORTH;
        switch (view.getId()) {
            case R.id.button_area_north:
                choosingButton = 0;
                companies = Common.COMPANIES_IN_NORTH;
                break;
            case R.id.button_area_middle:
                choosingButton = 1;
                companies = Common.COMPANIES_IN_MIDDLE;
                break;
            case R.id.button_area_south:
                choosingButton = 2;
                companies = Common.COMPANIES_IN_SOUTH;
                break;
        }

        layoutGroupCompanies.removeAllViews();
        for (final String company : companies) {
            Button button = new Button(getActivity());
            button.setText(company);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Reset title for the activity
                    getActivity().getActionBar().setTitle(company);
                    choosingCompany = company;

                    // load Result
                    loadResult();
                }
            });

            layoutGroupCompanies.addView(button);
        }
    }

    public void loadResult(){
        new DownloadResultTask().execute();
    }

    class DownloadResultTask extends AsyncTask<Void, String, String> {
        private static final String TAG = "Download Result Task";

        @Override
        protected String doInBackground(Void... voids) {
            URLBuilder urlBuilder = new URLBuilder(URLBuilder.URL_KET_QUA);
            urlBuilder.append(Common.LOCATION_CODE, "mienbac");
            urlBuilder.append(Common.DATE, "2014-10-1");

            String url = urlBuilder.create();
            String result = ServiceUtilities.sendGet(url, null);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, result);

            LotteryResult lottoResult = new LotteryResult(result);
            TwoColumnArrayAdapter resultAdapter = new TwoColumnArrayAdapter(
                    getActivity(),
                    R.layout.row_two_columns,
                    R.id.text_first_column,
                    Arrays.asList(Common.PRIZE_NAME),
                    lottoResult.getPrize()
            );
            Log.d(TAG, Arrays.asList(lottoResult.getPrize()).toString());

            listResult.setAdapter(resultAdapter);
            resultAdapter.notifyDataSetChanged();
        }
    }
}
