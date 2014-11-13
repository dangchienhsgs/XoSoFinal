package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.TabsPagerAdapter;
import fiveplay.dangchienhsgs.com.xosokienthiet.dialogs.datepicker.MyDatePickerDialogs;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener, MyDatePickerDialogs.DatePickerListener {
    private String TAG = "Main Activity";

    private ViewPager mViewPager;
    private ActionBar actionBar;
    private TabsPagerAdapter adapter;

    private ResultFragment resultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTabs();
    }

    private void initTabs() {
        actionBar = getActionBar();

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        adapter = new TabsPagerAdapter(getSupportFragmentManager());


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
                dialogs.setDatePickerListener((ResultFragment) adapter.getItem(0));
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

    }
}
