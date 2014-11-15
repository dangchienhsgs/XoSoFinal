package fiveplay.dangchienhsgs.com.xosokienthiet.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fiveplay.dangchienhsgs.com.xosokienthiet.ResultFragment;
import fiveplay.dangchienhsgs.com.xosokienthiet.ScheduleFragment;
import fiveplay.dangchienhsgs.com.xosokienthiet.UtilitiesFragment;
import fiveplay.dangchienhsgs.com.xosokienthiet.VanTrinhFragment;

/**
 * Created by dangchienbn on 08/11/2014.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> listFragment;

    public TabsPagerAdapter(FragmentManager fm, List<Fragment> listFragment) {
        super(fm);
        this.listFragment = listFragment;

    }

    @Override
    public Fragment getItem(int i) {
        return listFragment.get(i);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }
}
