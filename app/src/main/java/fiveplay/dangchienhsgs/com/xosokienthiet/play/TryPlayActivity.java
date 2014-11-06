package fiveplay.dangchienhsgs.com.xosokienthiet.play;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


import fiveplay.dangchienhsgs.com.xosokienthiet.Common;
import fiveplay.dangchienhsgs.com.xosokienthiet.R;

public class TryPlayActivity extends Activity implements Button.OnClickListener{
    private ListView listPrizeValue;
    private PrizeValueArrayAdapter mAdapter;

    private List<String> listValue;
    private List<String> listPrize;

    private Button buttonPlay;

    private int[] randomNumbers;


    private boolean isPlaying=false;

    private TextView textResult;

    private RandomTask randomTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_play);

        //Get ListView of the table
        listPrizeValue=(ListView) findViewById(R.id.list_prize_and_value);
        textResult =(TextView) findViewById(R.id.text_result);
        buttonPlay=(Button) findViewById(R.id.button_play);

        //Create the adapter
        mAdapter=new PrizeValueArrayAdapter(
                getApplicationContext(),
                R.layout.layout_row_list_prize_and_value
        );

        //get prize's name list from resource
        listPrize= Arrays.asList(getResources().getStringArray(R.array.prize_name));

        //init the value list by all elements are equal '-----' at the first time
        listValue=new ArrayList<String>();
        String initValue="-";

        for (int i=1; i<Common.NUMBER_DIGIT_LOTTO_VALUE; i++){
            initValue=initValue+"-";
        }
        for (int i=0; i<listPrize.size(); i++){
            listValue.add(initValue);
        }

        // Set adapter 's list prize and values
        mAdapter.setListPrizeName(listPrize);
        mAdapter.setListPrizeValue(listValue);

        // Set adapter for list prize
        listPrizeValue.setAdapter(mAdapter);

        // Set Button Play Listener
        buttonPlay.setOnClickListener(this);

        //
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_try_play, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (!isPlaying){
            randomTask=new RandomTask();
            randomNumbers=new int[Common.NUMBER_DIGIT_LOTTO_VALUE];
            isPlaying=true;
            Log.d("asdasd", isPlaying+"");
            randomTask.execute();
        } else {
            randomTask.cancel(true);
            isPlaying=false;
            Log.d("ASdasd", isPlaying+"");
        }
    }

    private class RandomTask extends AsyncTask<Void, String, Void>{
        private final String TAG="Random Task";

        private Handler mHandler;

        private String results;

        private RandomTask() {
            mHandler=new Handler();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                do{
                    if (!isCancelled()){
                        Random random=new Random();
                        results="";

                        int[] numbers=new int[Common.NUMBER_DIGIT_LOTTO_VALUE];
                        for (int i=0; i<Common.NUMBER_DIGIT_LOTTO_VALUE; i++){
                            numbers[i]=random.nextInt(10);
                            results=results+numbers[i];
                        }

                        publishProgress(results);
                        Thread.sleep(300);
                    } else{
                        break;
                    }
                }
                while (true);
            } catch (Exception e){
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
