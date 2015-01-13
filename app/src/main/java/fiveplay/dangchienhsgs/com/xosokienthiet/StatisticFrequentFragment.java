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

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.ThreeColumnArrayAdapter;
import fiveplay.dangchienhsgs.com.xosokienthiet.dialogs.alerterror.NetworkErrorDialog;
import fiveplay.dangchienhsgs.com.xosokienthiet.dialogs.numberpicker.MyDialogNumberPicker;
import fiveplay.dangchienhsgs.com.xosokienthiet.service.ServiceUtilities;
import fiveplay.dangchienhsgs.com.xosokienthiet.utils.URLBuilder;


public class StatisticFrequentFragment extends Fragment implements Button.OnClickListener, MyDialogNumberPicker.NumberPickerListener {

    private final String TAG = "Statistic Frequent Fragment";
    private Button buttonNorth;
    private Button buttonMiddle;
    private Button buttonSouth;

    private LinearLayout layoutGroupCompanies;
    private String[] choosingCompanies;
    private String[] choosingCompaniesID;

    private Spinner spinnerNumberPicker;
    private List<String> listSpinnerItems;
    private ArrayAdapter<String> mSpinnerAdapter;

    private String code = "mienbac";
    private TextView textTitleSpecial;
    private TextView textTitleLotto;

    private ThreeColumnArrayAdapter specialAdapter;
    private ThreeColumnArrayAdapter lottoAdapter;
    private ListView listSpecialView;
    private ListView listLottoView;

    private int range = 30;

    private boolean isStarted=false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_statistic_frequent, container, false);

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

        textTitleLotto = (TextView) view.findViewById(R.id.text_title_lotto);
        textTitleSpecial = (TextView) view.findViewById(R.id.text_title_special);



        spinnerNumberPicker = (Spinner) view.findViewById(R.id.spinner_pick_num_times);
        spinnerNumberPicker.setVisibility(View.INVISIBLE);

        listSpinnerItems = new ArrayList<String>();
        for (String str : Common.DEFAULT_NUM_TIMES) {
            listSpinnerItems.add(str);
        }


        listSpecialView = (ListView) view.findViewById(R.id.list_special_view);
        listLottoView = (ListView) view.findViewById(R.id.list_lotto_view);

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
                    dialogNumberPicker.setListener(StatisticFrequentFragment.this);
                    dialogNumberPicker.show(getFragmentManager(), "Nothing");
                } else {
                    range = (Integer.parseInt(listSpinnerItems.get(i)));

                    if (isStarted){
                        new DownloadInfoTask(code, range).execute();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        textTitleSpecial.setVisibility(View.INVISIBLE);
        textTitleLotto.setVisibility(View.INVISIBLE);
        listLottoView.setVisibility(View.INVISIBLE);
        listSpecialView.setVisibility(View.INVISIBLE);

        textTitleSpecial.setText("Thống kê đầu đuôi giải đặc biệt sau "+range+" lần quay gần nhất");
        textTitleLotto.setText("Thống kê đầu đuôi lotto sau "+range+" lần quay gần nhất");
    }

    @Override
    public void onNumberPickerReturn(int value) {
        Log.d(TAG, listSpinnerItems.toString());
        listSpinnerItems.add(listSpinnerItems.size() - 1, value + "");

        mSpinnerAdapter.notifyDataSetChanged();
        spinnerNumberPicker.setSelection(listSpinnerItems.size() - 2);

        this.range = value;
        new DownloadInfoTask(code, range).execute();
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

                    code = choosingCompaniesID[Integer.parseInt((String) view.getTag())];
                    String company = choosingCompanies[Integer.parseInt((String) view.getTag())];

                    getActivity().getActionBar().setTitle(company);

                    // load Result
                    new DownloadInfoTask(code, range).execute();

                    EasyTracker.getInstance(getActivity()).send(MapBuilder.createEvent(
                            "Thong ke: " + code + " so lan: " + range,
                            "company button",
                            "Thong ke tan suat",
                            null).build());

                    isStarted=true;
                }
            });

            layoutGroupCompanies.addView(companyView);
        }
    }


    public void loadResult(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);

            JSONArray headLottoArray = jsonObject.getJSONArray("loto_dau");
            JSONArray tailLottoArray = jsonObject.getJSONArray("loto_duoi");
            JSONArray headSpecialArray = jsonObject.getJSONArray("dacbiet_dau");
            JSONArray tailSpecialArray = jsonObject.getJSONArray("dacbiet_duoi");

            List<String> listDigit = new ArrayList<String>();
            List<String> listLottoHead = convertJSONArrayToList(headLottoArray);
            List<String> listLottoTail = convertJSONArrayToList(tailLottoArray);
            List<String> listSpecialHead = convertJSONArrayToList(headSpecialArray);
            List<String> listSpecialTail = convertJSONArrayToList(tailSpecialArray);

            listLottoHead.add(0, "Đầu");
            listLottoTail.add(0, "Đuôi");
            listSpecialHead.add(0, "Đầu");
            listSpecialTail.add(0, "Đuôi");

            for (int i = 0; i < 10; i++) {
                listDigit.add(String.valueOf(i));
            }

            specialAdapter = new ThreeColumnArrayAdapter(
                    getActivity(),
                    R.layout.row_five_columns,
                    R.id.text_first_column,
                    listDigit,
                    listSpecialHead,
                    listSpecialTail
            );

            lottoAdapter = new ThreeColumnArrayAdapter(
                    getActivity(),
                    R.layout.row_five_columns,
                    R.id.text_first_column,
                    listDigit,
                    listLottoHead,
                    listLottoTail
            );


            listSpecialView.setAdapter(specialAdapter);
            listLottoView.setAdapter(lottoAdapter);


            textTitleSpecial.setVisibility(View.VISIBLE);
            textTitleLotto.setVisibility(View.VISIBLE);
            listLottoView.setVisibility(View.VISIBLE);
            listSpecialView.setVisibility(View.VISIBLE);
        } catch (JSONException e) {
            Log.d(TAG, "JSON from server is error: " + result);
        } catch (NullPointerException e) {
            NetworkErrorDialog dialog = new NetworkErrorDialog();
            dialog.setTitle("Thông báo");
            dialog.setContent("Lỗi mạng hoặc lỗi server, ấn retry để kết nối lại !");
            dialog.setListener(new NetworkErrorDialog.OnRetryListener() {
                @Override
                public void onDialogRetry() {
                    new DownloadInfoTask(code, range).execute();
                }

                @Override
                public void onDialogClose() {

                }
            });

            dialog.show(StatisticFrequentFragment.this.getFragmentManager(), "Error Network Dialog");
        }

    }

    private List<String> convertJSONArrayToList(JSONArray jsonArray) {

        List<Integer> listInteger = new ArrayList<Integer>();
        List<String> listString = new ArrayList<String>();
        try {

            int sum = 0;
            for (int i = 0; i < jsonArray.length(); i++) {
                Integer temp = (Integer) jsonArray.get(i);

                listInteger.add(temp);
                sum = sum + temp;
            }

            for (int i = 0; i < listInteger.size(); i++) {
                float percent = (float) listInteger.get(i) / sum;
                String element = listInteger.get(i) + " (" + String.format("%.2f", percent) + "%)";
                listString.add(element);
            }

            Log.d(TAG, listString.toString());
            return listString;

        } catch (JSONException e) {
            Log.d(TAG, "JSON Convert fail " + jsonArray.toString());

            return null;
        }
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
            URLBuilder builder = new URLBuilder(URLBuilder.URL_THONG_KE_TAN_SUAT);
            builder.append(Common.LOCATION_CODE, code);
            builder.append(Common.RANGE, String.valueOf(range));

            String url = builder.create();
            Log.d(TAG, url);
            String result = ServiceUtilities.sendGet(url, null);

            Log.d(TAG, result);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            loadResult(result);
        }
    }
}

