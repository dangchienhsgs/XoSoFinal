package fiveplay.dangchienhsgs.com.xosokienthiet.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import fiveplay.dangchienhsgs.com.xosokienthiet.ResultFragment;
import fiveplay.dangchienhsgs.com.xosokienthiet.ScheduleFragment;
import fiveplay.dangchienhsgs.com.xosokienthiet.VanTrinhFragment;

/**
 * Created by dangchienbn on 08/11/2014.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new ResultFragment();
            //break;
            case 1:
                return new VanTrinhFragment();
            //break;
            case 2:
                Log.d("Chien", "Schedule");
                return new ScheduleFragment();
            //break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
