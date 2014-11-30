package fiveplay.dangchienhsgs.com.xosokienthiet.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import fiveplay.dangchienhsgs.com.xosokienthiet.Common;
import fiveplay.dangchienhsgs.com.xosokienthiet.R;


public class ScheduleArrayAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "Schedule Array Adapter";


    private Context context;
    private List<String> header;
    private Map<String, List<String>> company;

    public ScheduleArrayAdapter(Context context, List<String> header, Map<String, List<String>> company) {
        this.context = context;
        this.header = header;
        this.company = company;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        String title = header.get(i);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.row_list_schedule_days, viewGroup, false);
        }

        TextView textDay = (TextView) view.findViewById(R.id.text_day_name);
        textDay.setText(title);

        return view;
    }

    @Override
    public View getChildView(int i, int i2, boolean b, View view, ViewGroup viewGroup) {

        String areaTitle = Common.AREAS[i2];

        Log.d(TAG, i + " " + i2);
        String companyTitle = company.get(header.get(i)).get(i2);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.row_list_schedule_child, viewGroup, false);
        }

        TextView textArea = (TextView) view.findViewById(R.id.text_area);
        TextView textCompany = (TextView) view.findViewById(R.id.text_company);

        textArea.setText(areaTitle);
        textCompany.setText(companyTitle);


        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return false;
    }


    @Override
    public int getGroupCount() {
        return header.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return Common.AREAS.length;
    }

    @Override
    public Object getGroup(int i) {
        return header.get(i);
    }

    @Override
    public Object getChild(int i, int i2) {
        return company.get(header.get(i)).get(i2);
    }

    @Override
    public long getGroupId(int position) {
        return position;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

}


