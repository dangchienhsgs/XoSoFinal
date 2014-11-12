package fiveplay.dangchienhsgs.com.xosokienthiet.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fiveplay.dangchienhsgs.com.xosokienthiet.Common;
import fiveplay.dangchienhsgs.com.xosokienthiet.R;

/**
 * Created by dangchienhsgs on 05/11/2014.
 */
public class ScheduleArrayAdapter extends ArrayAdapter<String> {
    private final String TAG="Schedule Array Adapter";

    private TextView textDay;

    private ListView listCompany;

    private List<String> listDays;

    private TwoColumnArrayAdapter mAdapter;

    public ScheduleArrayAdapter (Context context, int resource, List<String> listDays) {
        super(context, resource);
        this.listDays=listDays;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.layout_row_two_columns, parent);
        }

        // Set Text View
        textDay=(TextView) convertView.findViewById(R.id.text_day_name);
        textDay.setText(listDays.get(position));


        // Set ListView

        listCompany=(ListView) convertView.findViewById(R.id.list_company);

        final List<String> listValues;

        switch (position){
            case 0: listValues= Arrays.asList(Common.COMPANY_IN_MON_DAY);
                break;
            case 1: listValues= Arrays.asList(Common.COMPANY_IN_TUESDAY);
                break;
            case 2: listValues=Arrays.asList(Common.COMPANY_IN_WEDNESDAY);
                break;
            case 3: listValues=Arrays.asList(Common.COMPANY_IN_THURSDAY);
                break;
            case 4: listValues=Arrays.asList(Common.COMPANY_IN_FRIDAY);
                break;
            case 5: listValues=Arrays.asList(Common.COMPANY_IN_SATURDAY);
                break;
            case 6: listValues=Arrays.asList(Common.COMPANY_IN_SUNDAY);
                break;
            default: listValues=Arrays.asList(Common.COMPANY_IN_MON_DAY);
        }

        TwoColumnArrayAdapter mAdapter=new TwoColumnArrayAdapter(
                getContext(),
                R.layout.layout_row_two_columns,
                Arrays.asList(Common.AREAS),
                listValues
        );

        listCompany.setAdapter(mAdapter);

        // Set OnCLick Listener for TextView
        textDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listCompany.getVisibility()==View.VISIBLE){
                    listCompany.setVisibility(View.INVISIBLE);
                } else {
                    listCompany.setVisibility(View.VISIBLE);
                }
            }
        });

        return convertView;
    }
}
