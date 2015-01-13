package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


import fiveplay.dangchienhsgs.com.xosokienthiet.Common;
import fiveplay.dangchienhsgs.com.xosokienthiet.R;
import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.TwoColumnArrayAdapter;

public class TryPlayFragment extends Fragment implements Button.OnClickListener {
    private final String TAG = "Try Play Activity";

    private ListView listResultView;

    private TwoColumnArrayAdapter mAdapter;

    private List<String> listValue;
    private List<String> listPrize;

    private Button buttonPlay;

    private int[] randomNumbers;

    private Spinner companySpinner;
    private boolean isPlaying = false;

    private TextView textDigit1;
    private TextView textDigit2;
    private TextView textDigit3;
    private TextView textDigit4;
    private TextView textDigit5;

    private int currentPlayPosition = 0;

    private RandomTask randomTask;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.activity_try_play, container, false);

        initComponent(view);
        initData(view);

        EasyTracker.getInstance(getActivity()).send(MapBuilder.createEvent(
                "Choi Thu",
                "Try Play",
                "Try Play Fragment",
                null).build());

        return view;
    }

    public void initComponent(View view) {
        //Get ListView of the table
        listResultView = (ListView) view.findViewById(R.id.list_prize_and_value);
        buttonPlay = (Button) view.findViewById(R.id.button_play);

        companySpinner = (Spinner) view.findViewById(R.id.spinner_lotto_companies);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.spinner_current_item,
                Common.COMPANIES
        );

        mAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        companySpinner.setAdapter(mAdapter);

        textDigit1 = (TextView) view.findViewById(R.id.digit_1);
        textDigit2 = (TextView) view.findViewById(R.id.digit_2);
        textDigit3 = (TextView) view.findViewById(R.id.digit_3);
        textDigit4 = (TextView) view.findViewById(R.id.digit_4);
        textDigit5 = (TextView) view.findViewById(R.id.digit_5);
    }

    public void initData(View view) {

        listPrize = Arrays.asList(getResources().getStringArray(R.array.prize_name));
        listValue = new ArrayList<String>();

        String initValue = "-";
        for (int i = 1; i < Common.NUMBER_DIGIT_LOTTO_VALUE; i++) {
            initValue = initValue + "-";
        }
        for (int i = 0; i < listPrize.size(); i++) {
            listValue.add(initValue);
        }

        //Create the adapter
        mAdapter = new TwoColumnArrayAdapter(
                getActivity(),
                R.layout.row_two_columns,
                R.id.text_first_column,
                listPrize,
                listValue
        );

        // Set adapter for list prize
        listResultView.setAdapter(mAdapter);

        // Set Button Play Listener
        buttonPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (!isPlaying) {
            randomTask = new RandomTask();
            randomNumbers = new int[Common.NUMBER_DIGIT_LOTTO_VALUE];
            isPlaying = true;
            randomTask.execute();
        } else {
            randomTask.cancel(true);
            isPlaying = false;
            Log.d(TAG, "Stop play");

            // Update Table

            Log.d(TAG, "Update list value");

            String temp = textDigit1.getText().toString() + textDigit2.getText().toString() + textDigit3.getText().toString() +
                    textDigit4.getText().toString() + textDigit5.getText().toString();
            listValue.set(currentPlayPosition, temp);
            currentPlayPosition++;

            if (currentPlayPosition == 8) {
                Toast.makeText(getActivity(), "Finish !", Toast.LENGTH_SHORT).show();
                initData(getView());
                currentPlayPosition = 0;
            }
            mAdapter.notifyDataSetChanged();

            Log.d(TAG, "Finish update list value");
        }
    }

    public void updateTextResult(String text) {
        textDigit1.setText(String.valueOf(text.charAt(0)));
        textDigit2.setText(String.valueOf(text.charAt(1)));
        textDigit3.setText(String.valueOf(text.charAt(2)));
        textDigit4.setText(String.valueOf(text.charAt(3)));
        textDigit5.setText(String.valueOf(text.charAt(4)));

    }

    /**
     * Background Task do random and update it to the views
     */
    private class RandomTask extends AsyncTask<Void, String, Void> {
        private final String TAG = "Random Task";

        private Handler mHandler;

        private String results;

        private RandomTask() {
            mHandler = new Handler();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                do {
                    if (!isCancelled()) {
                        Random random = new Random();
                        results = "";

                        int[] numbers = new int[Common.NUMBER_DIGIT_LOTTO_VALUE];
                        for (int i = 0; i < Common.NUMBER_DIGIT_LOTTO_VALUE; i++) {
                            numbers[i] = random.nextInt(10);
                            results = results + numbers[i];
                        }

                        publishProgress(results);
                        Thread.sleep(100);
                    } else {
                        break;
                    }
                }
                while (true);
            } catch (Exception e) {
                Log.d(TAG, "Something was error");
            }

            return null;
        }


        @Override
        protected void onProgressUpdate(String... results) {
            updateTextResult(results[0]);
        }
    }
}
