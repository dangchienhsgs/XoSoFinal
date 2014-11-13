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
import android.widget.ListView;
import android.widget.Spinner;

import java.util.Arrays;

import fiveplay.dangchienhsgs.com.xosokienthiet.Common;
import fiveplay.dangchienhsgs.com.xosokienthiet.R;
import fiveplay.dangchienhsgs.com.xosokienthiet.dialogs.datepicker.MyDatePickerDialogs;
import fiveplay.dangchienhsgs.com.xosokienthiet.model.LotteryResult;
import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.TwoColumnArrayAdapter;
import fiveplay.dangchienhsgs.com.xosokienthiet.utils.URLBuilder;
import fiveplay.dangchienhsgs.com.xosokienthiet.utils.URLContentHandler;

public class ResultFragment extends Fragment implements MyDatePickerDialogs.DatePickerListener{
    private Spinner spinnerLottoHost;

    private Button buttonResult;
    private Button buttonDay;

    private ListView listResult;
    private ListView listHeadTail;


    private int day;
    private int month;
    private int year;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_result, container, false);
        initComponents(view);

        return view;
    }

    public void initComponents(View view){
        spinnerLottoHost=(Spinner) view.findViewById(R.id.spinner_lotto_companies);
        buttonDay=(Button) view.findViewById(R.id.button_day);
        buttonResult=(Button) view.findViewById(R.id.button_view_result);
        listResult=(ListView) view.findViewById(R.id.list_result);
        listHeadTail=(ListView)view.findViewById(R.id.list_head_tail);


        ArrayAdapter<String> spinnerAdapter=new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                Common.LOTTO_HOST
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLottoHost.setAdapter(spinnerAdapter);

        /*TwoColumnArrayAdapter listAdapter=new TwoColumnArrayAdapter(
                getActivity(),
                R.layout.layout_row_two_columns,
                null, // list prize
                null  // list value
        );*/

        buttonDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelectedDay(view);
            }
        });


        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadResultTask().execute();
            }
        });
    }

    @Override
    public void onDatePickerReturn(int year, int month, int day) {

    }

    public void onSelectedDay(View view){
        Log.d("asd, ", "Clicked");
        MyDatePickerDialogs dialogs=new MyDatePickerDialogs();
        dialogs.setDatePickerListener(this);
        dialogs.show(getFragmentManager(), "Nothing");
    }

    class DownloadResultTask extends AsyncTask<Void,String, String>{
        private static final String TAG="Download Result Task";

        @Override
        protected String doInBackground(Void... voids) {
            URLBuilder urlBuilder=new URLBuilder(URLBuilder.URL_KET_QUA);
            urlBuilder.append(Common.LOCATION_CODE, "mienbac");
            urlBuilder.append(Common.DATE, "2014-10-1");

            String url=urlBuilder.create();
            String result= URLContentHandler.getURLContent(url);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, result);

            LotteryResult lottoResult=new LotteryResult(result);
            TwoColumnArrayAdapter resultAdapter=new TwoColumnArrayAdapter(
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
