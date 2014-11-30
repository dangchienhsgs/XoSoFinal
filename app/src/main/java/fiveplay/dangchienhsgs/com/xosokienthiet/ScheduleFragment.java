package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.Arrays;

import fiveplay.dangchienhsgs.com.xosokienthiet.R;
import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.ScheduleArrayAdapter;
import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.TwoColumnArrayAdapter;

public class ScheduleFragment extends Fragment {
    private static final String TAG = "Schedule Fragment";

    private ScheduleArrayAdapter mAdapter;
    private ExpandableListView listDay;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view=inflater.inflate(R.layout.fragment_schedule, container, false);

        listDay = (ExpandableListView) view.findViewById(R.id.list_day);
        Log.d(TAG, Arrays.asList(Common.DAY_IN_WEEK).toString());

        mAdapter=new ScheduleArrayAdapter(
                getActivity(),
                Common.getDay(),
                Common.getScheduleMap()
        );

        listDay.setAdapter(mAdapter);

        listDay.setGroupIndicator(getResources().getDrawable(R.drawable.icon_expandable_indicator));
        listDay.setIndicatorBounds(10, 10);
        listDay.setFooterDividersEnabled(false);
        listDay.setHeaderDividersEnabled(false);

        return view;
    }
}
