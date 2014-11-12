package fiveplay.dangchienhsgs.com.xosokienthiet.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fiveplay.dangchienhsgs.com.xosokienthiet.ResultFragment;

/**
 * Created by dangchienbn on 08/11/2014.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter{

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new ResultFragment();
                //break;
            case 1:
                return new ResultFragment();
                //break;
            case 2:
                return new ResultFragment();
                //break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
