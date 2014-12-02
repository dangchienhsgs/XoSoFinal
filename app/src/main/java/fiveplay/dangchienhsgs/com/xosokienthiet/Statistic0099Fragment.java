package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fiveplay.dangchienhsgs.com.xosokienthiet.Common;
import fiveplay.dangchienhsgs.com.xosokienthiet.R;
import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.TwoColumnArrayAdapter;
import fiveplay.dangchienhsgs.com.xosokienthiet.dialogs.numberpicker.MyDialogNumberPicker;
import fiveplay.dangchienhsgs.com.xosokienthiet.service.ServiceUtilities;
import fiveplay.dangchienhsgs.com.xosokienthiet.utils.URLBuilder;

/**
 * Created by dangchienbn on 15/11/2014.
 */
public class Statistic0099Fragment extends Fragment implements Button.OnClickListener, MyDialogNumberPicker.NumberPickerListener {
    private String TAG = "Statistic 00 99 ";

    private Button buttonNorth;
    private Button buttonMiddle;
    private Button buttonSouth;

    private Spinner spinnerNumberPicker;

    private LinearLayout layoutGroupCompanies;

    private TextView textTitle;

    private int range;

    private String[] choosingCompanies;
    private String[] choosingCompaniesID;
    private List<String> listSpinnerItems;

    private TwoColumnArrayAdapter mAdapter;
    private ArrayAdapter<String> mSpinnerAdapter;

    private ListView list0099;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_statistic_00_99, container, false);
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


        textTitle = (TextView) view.findViewById(R.id.text_title_explain_result);

        layoutGroupCompanies = (LinearLayout) view.findViewById(R.id.layout_group_companies);


        list0099 = (ListView) view.findViewById(R.id.list_lotto_statistic_00_99);
        spinnerNumberPicker = (Spinner) view.findViewById(R.id.spinner_pick_num_times);

        listSpinnerItems = new ArrayList<String>();
        for (String str : Common.DEFAULT_NUM_TIMES) {
            listSpinnerItems.add(str);
        }

        spinnerNumberPicker.setPopupBackgroundDrawable(getResources().getDrawable(R.drawable.popup_lanthongke));
        mSpinnerAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.spinner_current_item,
                listSpinnerItems
        );


        mSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinnerNumberPicker.setAdapter(mSpinnerAdapter);


        range = Integer.parseInt(listSpinnerItems.get(0));

        spinnerNumberPicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == listSpinnerItems.size() - 1) {
                    MyDialogNumberPicker dialogNumberPicker = new MyDialogNumberPicker();
                    dialogNumberPicker.setListener(Statistic0099Fragment.this);
                    dialogNumberPicker.show(getFragmentManager(), "Nothing");
                } else {
                    range = (Integer.parseInt(listSpinnerItems.get(i)));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        list0099.setVisibility(View.INVISIBLE);
        textTitle.setVisibility(View.INVISIBLE);
        spinnerNumberPicker.setVisibility(View.INVISIBLE);
        layoutGroupCompanies.setVisibility(View.INVISIBLE);
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

        layoutGroupCompanies.setVisibility(View.VISIBLE);
        spinnerNumberPicker.setVisibility(View.VISIBLE);

        layoutGroupCompanies.removeAllViews();

        for (int i = 0; i < choosingCompanies.length; i++) {

            String company = choosingCompanies[i];

            // Add button to the row
            View companyView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_text_company, null);
            TextView textCompany = (TextView) companyView.findViewById(R.id.text_company);
            textCompany.setTextColor(getResources().getColor(R.color.text_table_color));
            textCompany.setText(company);
            companyView.setTag(String.valueOf(i));

            // Set onClickListener for the Button
            companyView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Reset title for the activity
                    for (int j = 0; j < layoutGroupCompanies.getChildCount(); j++) {
                        View temp = layoutGroupCompanies.getChildAt(j);
                        ((TextView) temp.findViewById(R.id.text_company)).setTextColor(getResources().getColor(R.color.text_table_color));
                    }

                    ((TextView) view.findViewById(R.id.text_company)).setTextColor(getResources().getColor(R.color.orange_color));

                    String code = choosingCompaniesID[Integer.parseInt((String) view.getTag())];
                    String company = choosingCompanies[Integer.parseInt((String) view.getTag())];

                    getActivity().getActionBar().setTitle(company);

                    // load Result
                    loadResult(code);

                }
            });

            layoutGroupCompanies.addView(companyView);
        }
    }

    @Override
    public void onNumberPickerReturn(int value) {
        Log.d(TAG, listSpinnerItems.toString());
        listSpinnerItems.add(listSpinnerItems.size() - 1, value + "");

        mSpinnerAdapter.notifyDataSetChanged();
        spinnerNumberPicker.setSelection(listSpinnerItems.size() - 2);

        this.range = value;
    }

    public void loadResult(String code) {

        new DownloadInfoTask(code, range).execute();
    }

    private class DownloadInfoTask extends AsyncTask<Void, String, String> {

        private String code;
        private int range;

        private DownloadInfoTask(String code, int range) {
            this.code = code;
            this.range = range;
        }

        @Override
        protected String doInBackground(Void... voids) {
            URLBuilder urlBuilder = new URLBuilder(URLBuilder.URL_THONG_KE_00_99);

            urlBuilder.append(Common.LOCATION_CODE, code);
            urlBuilder.append(Common.RANGE, String.valueOf(range));

            String url = urlBuilder.create();

            String result = ServiceUtilities.sendGet(url, null);

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);

                List<String> listIndex = new ArrayList<String>();
                List<String> listValue = new ArrayList<String>();

                for (int i = 0; i < 100; i++) {
                    String index = "";
                    if (i < 10) {
                        index = "0" + i;
                    } else {
                        index = "" + i;
                    }
                    String value = jsonObject.getString(index);

                    listIndex.add(index);
                    listValue.add(value);
                }

                mAdapter = new TwoColumnArrayAdapter(
                        getActivity(),
                        R.layout.row_two_columns,
                        R.id.text_first_column,
                        listIndex,
                        listValue
                );

                mAdapter.setListFirstColumn(listIndex);
                mAdapter.setListSecondColumn(listValue);

                list0099.setAdapter(mAdapter);


                list0099.setVisibility(View.VISIBLE);
                textTitle.setVisibility(View.VISIBLE);

            } catch (JSONException e) {
                Log.d(TAG, "JSON error from url: " + s);
            }
        }
    }

}
