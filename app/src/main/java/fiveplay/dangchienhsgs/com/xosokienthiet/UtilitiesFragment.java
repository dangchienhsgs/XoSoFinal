package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.UtilitiesArrayAdapter;


public class UtilitiesFragment extends Fragment {
    private UtilitiesArrayAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_utilities, container, false);

        ListView listUtilities = (ListView) view.findViewById(R.id.list_utilities);

        mAdapter = new UtilitiesArrayAdapter(
                getActivity(),
                R.layout.row_list_utilities,
                Arrays.asList(Common.UTILITIES)
        );

        listUtilities.setAdapter(mAdapter);

        listUtilities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {

                }
            }
        });


        return view;
    }
}
