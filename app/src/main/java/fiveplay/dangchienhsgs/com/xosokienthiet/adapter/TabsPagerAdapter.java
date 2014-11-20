package fiveplay.dangchienhsgs.com.xosokienthiet.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


public class TabsPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> listFragment;
    FragmentManager fragmentManager;


    public TabsPagerAdapter(FragmentManager fm, List<Fragment> listFragment) {
        super(fm);
        this.listFragment = listFragment;
        fragmentManager = fm;
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
