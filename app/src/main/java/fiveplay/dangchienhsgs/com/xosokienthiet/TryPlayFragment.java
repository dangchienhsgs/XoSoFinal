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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
    private TextView textResult;

    private TwoColumnArrayAdapter mAdapter;

    private List<String> listValue;
    private List<String> listPrize;

    private Button buttonPlay;

    private int[] randomNumbers;


    private boolean isPlaying = false;

    private int currentPlayPosition = 0;

    private RandomTask randomTask;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.activity_try_play, container, false);

        initComponent(view);
        initData(view);

        return view;
    }

    public void initComponent(View view) {
        //Get ListView of the table
        listResultView = (ListView) view.findViewById(R.id.list_prize_and_value);
        textResult = (TextView) view.findViewById(R.id.text_result);
        buttonPlay = (Button) view.findViewById(R.id.button_play);
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
            listValue.set(currentPlayPosition, textResult.getText().toString());
            currentPlayPosition++;
            mAdapter.notifyDataSetChanged();

            Log.d(TAG, "Finish update list value");
        }
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
                        Thread.sleep(300);
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
            textResult.setText(results[0]);
            Log.d(TAG, results[0]);
        }
    }
}
