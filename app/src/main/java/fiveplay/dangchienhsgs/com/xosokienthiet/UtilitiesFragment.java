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

import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.UtilitiesArrayAdapter;


public class UtilitiesFragment extends Fragment {
    public static final int INDEX_DREAM_BOOK = 0;
    public static final int INDEX_TRY_PLAY = 1;
    public static final int INDEX_GOLD_PRICE = 2;
    public static final int INDEX_VAN_TRINH = 3;
    public static final int INDEX_NGU_HANH = 4;
    public static final int INDEX_FUN_STORY = 5;
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

                MainActivity mainActivity = (MainActivity) getActivity();

                switch (i) {
                    case INDEX_DREAM_BOOK:
                        mainActivity.replaceFragment(R.id.fragment_utilities_root, new DreamBookFragment());
                        mainActivity.setIndexFragment(Common.INDEX_DREAM_BOOK_FRAGMENT);
                        break;

                    case INDEX_VAN_TRINH:
                        mainActivity.replaceFragment(R.id.fragment_utilities_root, new VanTrinhRootFragment());
                        mainActivity.setIndexFragment(Common.INDEX_VAN_TRINH_FRAGMENT);
                        break;

                    case INDEX_GOLD_PRICE:
                        mainActivity.replaceFragment(R.id.fragment_utilities_root, new GoldPriceFragment());
                        mainActivity.setIndexFragment(Common.INDEX_GOLD_PRICE_FRAGMENT);
                        break;

                    case INDEX_TRY_PLAY:
                        mainActivity.replaceFragment(R.id.fragment_utilities_root, new TryPlayFragment());
                        mainActivity.setIndexFragment(Common.INDEX_TRY_PLAY_FRAGMENT);
                        break;

                    case INDEX_NGU_HANH:
                        mainActivity.replaceFragment(R.id.fragment_utilities_root, new NguHanhRootFragment());
                        mainActivity.setIndexFragment(Common.INDEX_NGU_HANH_FRAGMENT);
                        break;
                }
            }
        });


        return view;
    }
}
