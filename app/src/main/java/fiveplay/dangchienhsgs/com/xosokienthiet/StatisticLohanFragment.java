package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.TwoColumnArrayAdapter;
import fiveplay.dangchienhsgs.com.xosokienthiet.model.NumberCouple;
import fiveplay.dangchienhsgs.com.xosokienthiet.model.StringCouple;
import fiveplay.dangchienhsgs.com.xosokienthiet.service.ServiceUtilities;
import fiveplay.dangchienhsgs.com.xosokienthiet.utils.URLBuilder;

/**
 * Created by dangchienbn on 15/11/2014.
 */
public class StatisticLohanFragment extends Fragment implements Button.OnClickListener {
    private static final String TAG = "Statistic Lohan Fragment";

    private Button buttonNorth;
    private Button buttonMiddle;
    private Button buttonSouth;

    private LinearLayout layoutGroupCompanies;

    private TextView textLogan;
    private TextView textXoso;

    private String[] choosingCompanies;
    private String[] choosingCompaniesID;

    private ListView listViewLotto;
    private ListView listViewCouple;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_statistic_logan, container, false);

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


        textLogan = (TextView) view.findViewById(R.id.text_title_logan);
        textXoso = (TextView) view.findViewById(R.id.text_title_logan_lotto);


        layoutGroupCompanies = (LinearLayout) view.findViewById(R.id.layout_group_companies);

        listViewLotto = (ListView) view.findViewById(R.id.list_logan_lotto);
        listViewCouple = (ListView) view.findViewById(R.id.list_logan_couple);


        textLogan.setVisibility(View.INVISIBLE);
        textXoso.setVisibility(View.INVISIBLE);


        listViewCouple.setVisibility(View.INVISIBLE);
        listViewLotto.setVisibility(View.INVISIBLE);
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
                    new DownloadInfoTask(code).execute();

                }
            });

            layoutGroupCompanies.addView(companyView);
        }
    }

    public void loadResult(String code) {
        try {
            JSONObject jsonObject = new JSONObject(code);
            JSONArray jsonLotto = jsonObject.getJSONArray("loto");

            Log.d(TAG, jsonLotto.toString());

            List<NumberCouple> listLotto = new ArrayList<NumberCouple>();
            List<StringCouple> listCouple = new ArrayList<StringCouple>();


            // Read list lotto
            for (int i = 0; i < jsonLotto.length(); i++) {
                JSONObject element = (JSONObject) jsonLotto.get(i);

                NumberCouple numberCouple = new NumberCouple(
                        Integer.parseInt((String) element.get("number")),
                        element.getInt("lastOpenDateInDays")
                );

                listLotto.add(numberCouple);
            }

            // Read list Couple
            for (int i = 0; i < jsonLotto.length(); i++) {
                JSONObject element = (JSONObject) jsonLotto.get(i);
                String keyElement = element.getString("number");
                String reverseKeyElement = new StringBuilder(keyElement).reverse().toString();

                // Check element if exist in listCouple
                // check=true if exist

                boolean check = false;
                for (int j = 0; j < listCouple.size(); j++) {
                    String keyCouple = String.valueOf(listCouple.get(j).getKey());

                    if (keyElement.equals(keyCouple) || reverseKeyElement.equals(keyCouple)) {
                        check = true;
                    }
                }

                if (!check) {

                    JSONObject reverseElement = null;

                    // Find JSON inverse of the element
                    for (int j = 0; j < listLotto.size(); j++) {
                        JSONObject temp = (JSONObject) jsonLotto.get(j);
                        String key = temp.getString("number");

                        if (key.equals(reverseKeyElement)) {
                            reverseElement = temp;
                        }
                    }

                    // Finish find

                    int elementValue = element.getInt("lastOpenDateInDays");
                    int reverseValue = reverseElement.getInt("lastOpenDateInDays");

                    Log.d(TAG, keyElement + " " + elementValue + " " + reverseValue);
                    int value = 0;

                    if (elementValue < reverseValue) {
                        value = elementValue;
                    } else {
                        value = reverseValue;
                    }

                    listCouple.add(new StringCouple(
                            keyElement,
                            value
                    ));
                }

            }

            // Sort list
            for (int i = 0; i < listLotto.size(); i++) {
                for (int j = i + 1; j < listLotto.size(); j++) {
                    NumberCouple temp1 = listLotto.get(i);
                    NumberCouple temp2 = listLotto.get(j);

                    if (temp1.getValue() < temp2.getValue()) {
                        NumberCouple temp3 = temp1;
                        listLotto.set(i, temp2);
                        listLotto.set(j, temp3);
                    }
                }
            }


            // SortList

            for (int i = 0; i < listCouple.size(); i++) {
                for (int j = i + 1; j < listCouple.size(); j++) {
                    StringCouple temp1 = listCouple.get(i);
                    StringCouple temp2 = listCouple.get(j);

                    if (temp1.getValue() < temp2.getValue()) {
                        StringCouple temp3 = temp1;
                        listCouple.set(i, temp2);
                        listCouple.set(j, temp3);
                    }
                }
            }


            // Create adapter for the listview

            List<String> indexLotto = new ArrayList<String>();
            List<String> valueLotto = new ArrayList<String>();
            List<String> indexCouple = new ArrayList<String>();
            List<String> valueCouple = new ArrayList<String>();

            for (int i = 0; i < 10; i++) {
                NumberCouple lottoElement = listLotto.get(i);
                StringCouple coupleElement = listCouple.get(i);

                indexLotto.add(String.valueOf(lottoElement.getIndex()));
                valueLotto.add(String.valueOf(lottoElement.getValue()));

                String coupleKey = String.valueOf(coupleElement.getKey());
                indexCouple.add(coupleKey + "-" + new StringBuilder(coupleKey).reverse());
                valueCouple.add(String.valueOf(coupleElement.getValue()));
            }


            TwoColumnArrayAdapter lottoAdapter = new TwoColumnArrayAdapter(
                    getActivity(),
                    R.layout.row_two_columns,
                    R.id.text_first_column,
                    indexLotto,
                    valueLotto
            );

            TwoColumnArrayAdapter coupleAdapter = new TwoColumnArrayAdapter(
                    getActivity(),
                    R.layout.row_two_columns,
                    R.id.text_first_column,
                    indexCouple,
                    valueCouple
            );

            listViewLotto.setAdapter(lottoAdapter);
            listViewCouple.setAdapter(coupleAdapter);

            listViewLotto.setVisibility(View.VISIBLE);
            listViewCouple.setVisibility(View.VISIBLE);
            textLogan.setVisibility(View.VISIBLE);
            textXoso.setVisibility(View.VISIBLE);

        } catch (JSONException e) {
            Log.d(TAG, "Json from server is error: " + code);
        }
    }

    private class DownloadInfoTask extends AsyncTask<Void, String, String> {
        private String code;

        private DownloadInfoTask(String code) {
            this.code = code;

        }

        @Override
        protected void onPostExecute(String result) {
            loadResult(result);
        }

        @Override
        protected String doInBackground(Void... voids) {
            URLBuilder urlBuilder = new URLBuilder(URLBuilder.URL_THONG_KE_LO_GAN);
            urlBuilder.append(Common.LOCATION_CODE, code);

            String url = urlBuilder.create();
            String result = ServiceUtilities.sendGet(url, null);

            return result;

        }
    }
}
