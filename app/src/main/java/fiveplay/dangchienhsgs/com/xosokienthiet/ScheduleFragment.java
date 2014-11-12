package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

import fiveplay.dangchienhsgs.com.xosokienthiet.R;
import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.ScheduleArrayAdapter;
import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.TwoColumnArrayAdapter;

public class ScheduleFragment extends Fragment {

    private ScheduleArrayAdapter mAdapter;
    private ListView listSchedule;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view=inflater.inflate(R.layout.fragment_schedule, container, false);

        listSchedule=(ListView) view.findViewById(R.id.list_schedule);

        mAdapter=new ScheduleArrayAdapter(
                getActivity(),
                R.layout.row_list_schedule_days,
                Arrays.asList(Common.DAY_IN_WEEK)
        );

        listSchedule.setAdapter(mAdapter);

        return view;
    }
}
