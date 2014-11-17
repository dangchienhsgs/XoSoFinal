package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.TabsPagerAdapter;
import fiveplay.dangchienhsgs.com.xosokienthiet.dialogs.datepicker.MyDatePickerDialogs;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener, MyDatePickerDialogs.DatePickerListener {
    private String TAG = "Main Activity";

    private ViewPager mViewPager;
    private ActionBar actionBar;
    private TabsPagerAdapter tabAdapter;

    private ResultFragment resultFragment;

    private ScheduleFragment scheduleFragment;
    private UtilitiesFragment utilitiesFragment;

    private StatisticRootFragment statisticRootFragment;
    private StatisticTypeFragment statisticTypeFragment;


    private List<Fragment> listFragment;


    private int day;

    private int month;

    private int year;

    private int indexStatisticFragment = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
        initTabs();
        initDate();

        tabAdapter.notifyDataSetChanged();


    }

    public void initFragment() {
        listFragment = new ArrayList<Fragment>();

        resultFragment = new ResultFragment();
        statisticTypeFragment = new StatisticTypeFragment();
        scheduleFragment = new ScheduleFragment();
        utilitiesFragment = new UtilitiesFragment();
        statisticRootFragment = new StatisticRootFragment();

        listFragment.add(resultFragment);
        listFragment.add(statisticRootFragment);
        listFragment.add(scheduleFragment);
        listFragment.add(utilitiesFragment);


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
        actionBar = getSupportActionBar();

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        tabAdapter = new TabsPagerAdapter(getSupportFragmentManager(), listFragment);


        mViewPager.setAdapter(tabAdapter);


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
                getSupportActionBar().setSelectedNavigationItem(i);
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
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
        int position = tab.getPosition();
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onDatePickerReturn(int year, int month, int day) {
        this.day = day;
        this.month = month;
        this.year = year;

        ResultFragment resultFragment = (ResultFragment) listFragment.get(Common.INDEX_RESULT_FRAGMENT);
        resultFragment.setDate(year, month, day);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, getSupportActionBar().getSelectedNavigationIndex() + "");
//        Toast.makeText(getApplicationContext(), getSupportActionBar().getSelectedNavigationIndex(), Toast.LENGTH_SHORT).show();
        switch (getSupportActionBar().getSelectedNavigationIndex()) {
            case Common.INDEX_RESULT_FRAGMENT:
                super.onBackPressed();
                break;
            case Common.INDEX_STATISTIC_FRAGMENT:
                if (indexStatisticFragment == -1) {
                    super.onBackPressed();
                } else {
                    indexStatisticFragment = -1;
                    replaceFragment(R.id.fragment_statistic_root, statisticTypeFragment);
                }
                break;
            case Common.INDEX_SCHEDULE_FRAGMENT:
                break;
            case Common.INDEX_UTILITIES_FRAGMENT:
                break;
        }
    }

    public void replaceFragment(int currentFragmentID, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(currentFragmentID, fragment);
        fragmentTransaction.commit();
    }

    public void setIndexStatisticFragment(int indexStatisticFragment) {
        this.indexStatisticFragment = indexStatisticFragment;
    }
}
