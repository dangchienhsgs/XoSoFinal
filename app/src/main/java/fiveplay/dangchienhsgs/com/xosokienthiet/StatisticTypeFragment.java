package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.TabsPagerAdapter;


public class StatisticTypeFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final int LO_GAN = 0;
    private static final int DIGIT_00_99 = 1;
    private static final int LESS_MORE = 2;
    private static final int XUAT_HIEN = 3;
    private ListView listType;
    private ArrayAdapter<String> mAdapter;
    private TabsPagerAdapter mTabsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_statistic_type, container, false);

        listType = (ListView) view.findViewById(R.id.list_type_statistic);

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.row_list_statistic_type,
                R.id.text_statistic_type,
                Arrays.asList(Common.SCHEDULE_TYPES)
        );

        listType.setAdapter(mAdapter);

        listType.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setIndexStatisticFragment(i);
        switch (i) {
            case DIGIT_00_99:
                mainActivity.replaceFragment(R.id.fragment_statistic_root, new Statistic0099Fragment());
                break;
            case LESS_MORE:
                mainActivity.replaceFragment(R.id.fragment_statistic_root, new StatisticLessMoreFragment());
                break;
            case LO_GAN:
                mainActivity.replaceFragment(R.id.fragment_statistic_root, new StatisticLohanFragment());
                break;

        }
    }
}
