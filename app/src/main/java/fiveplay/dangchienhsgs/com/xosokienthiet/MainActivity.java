package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.TabsPagerAdapter;
import fiveplay.dangchienhsgs.com.xosokienthiet.dialogs.datepicker.MyDatePickerDialogs;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener, MyDatePickerDialogs.DatePickerListener {
    private String TAG = "Main Activity";

    private ViewPager mViewPager;
    private ActionBar actionBar;
    private TabsPagerAdapter adapter;

    private List<Fragment> listFragment;

    private int INDEX_RESULT_FRAGMENT = 0;
    private int INDEX_STATISTIC_FRAGMENT = 1;
    private int INDEX_SCHEDULE_FRAGMENT = 2;

    private int day;

    private int month;

    private int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
        initTabs();
        initDate();

    }


    public void initFragment() {
        listFragment = new ArrayList<Fragment>();

        listFragment.add(new ResultFragment());
        listFragment.add(new VanTrinhFragment());
        listFragment.add(new ScheduleFragment());
        listFragment.add(new UtilitiesFragment());

    }

    public void initDate() {
        Calendar calendar = Calendar.getInstance();

        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        onDatePickerReturn(year, month, day);
        Log.d(TAG, "Today: " + month + " " + year + " " + day);
    }


    private void initTabs() {
        actionBar = getActionBar();

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        adapter = new TabsPagerAdapter(getSupportFragmentManager(), listFragment);


        mViewPager.setAdapter(adapter);


        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setHomeButtonEnabled(false);

        String[] titles = getResources().getStringArray(R.array.tab_titles);

        for (String title : titles) {
            ActionBar.Tab tab = actionBar.newTab();

            tab.setText(title);
            tab.setTabListener(this);
            actionBar.addTab(tab);
        }

        ActionBar.Tab utilTab = actionBar.newTab();
        utilTab.setIcon(getResources().getDrawable(android.R.drawable.ic_menu_more));
        utilTab.setTabListener(this);
        actionBar.addTab(utilTab);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                getActionBar().setSelectedNavigationItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_calendar:
                MyDatePickerDialogs dialogs = new MyDatePickerDialogs();

                dialogs.setInitDate(year, month, day);
                dialogs.setDatePickerListener(this);
                dialogs.show(getSupportFragmentManager(), "DatePicker");
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        int position = tab.getPosition();
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onDatePickerReturn(int year, int month, int day) {
        this.day = day;
        this.month = month;
        this.year = year;

        ResultFragment resultFragment = (ResultFragment) listFragment.get(INDEX_RESULT_FRAGMENT);
        resultFragment.setDate(year, month, day);
    }
}
