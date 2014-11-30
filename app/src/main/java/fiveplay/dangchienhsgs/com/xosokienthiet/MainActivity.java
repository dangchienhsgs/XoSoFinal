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
import android.view.View;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.TabsPagerAdapter;
import fiveplay.dangchienhsgs.com.xosokienthiet.dialogs.datepicker.MyDatePickerDialogs;


public class MainActivity extends ActionBarActivity implements MyDatePickerDialogs.DatePickerListener {
    private String TAG = "Main Activity";

    private ViewPager mViewPager;
    private ActionBar actionBar;
    private TabsPagerAdapter tabAdapter;

    private ResultFragment resultFragment;

    private ScheduleFragment scheduleFragment;

    private UtilitiesRootFragment utilitiesRootFragment;
    private UtilitiesFragment utilitiesFragment;

    private StatisticRootFragment statisticRootFragment;
    private StatisticTypeFragment statisticTypeFragment;

    private NguHanhRootFragment nguHanhRootFragment;
    private NguHanhResultFragment nguHanhFragment;
    private NguHanhDayPickerFragment nguHanhDayPickerFragment;

    private VanTrinhRootFragment vanTrinhRootFragment;
    private VanTrinhResultFragment vanTrinhFragment;

    private List<Fragment> listFragment;

    private SlidingMenu menu;


    private int day;

    private int month;

    private int year;

    private int indexFragment;

    private int indexStatisticFragment = -1;
    private int indexNguHanhFragment = -1;
    private int indexVanTrinhFragment = -1;
    private int indexFunStoryFragment = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
        initTabs();
        initDate();
        initMenu();

    }

    public void initMenu() {
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        //menu.setShadowDrawable(R.drawable.bg_grey);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.sliding_menu_drawer);

    }

    public void onDrawerItemClick(View view) {
        switch (view.getId()) {
            case R.id.image_ketqua:
                indexFragment = Common.INDEX_RESULT_FRAGMENT;
                mViewPager.setCurrentItem(Common.INDEX_RESULT_FRAGMENT);
                break;
            case R.id.image_thongke:
                indexFragment = Common.INDEX_STATISTIC_FRAGMENT;
                mViewPager.setCurrentItem(Common.INDEX_STATISTIC_FRAGMENT);
                break;
            case R.id.image_lichquay:
                indexFragment = Common.INDEX_SCHEDULE_FRAGMENT;
                mViewPager.setCurrentItem(Common.INDEX_SCHEDULE_FRAGMENT);
                break;
            case R.id.image_tienich:
                indexFragment = Common.INDEX_UTILITIES_FRAGMENT;
                mViewPager.setCurrentItem(Common.INDEX_UTILITIES_FRAGMENT);
                break;
        }
        menu.toggle(true);
    }

    public void initFragment() {
        listFragment = new ArrayList<Fragment>();

        resultFragment = new ResultFragment();

        statisticTypeFragment = new StatisticTypeFragment();
        statisticRootFragment = new StatisticRootFragment();

        scheduleFragment = new ScheduleFragment();

        utilitiesRootFragment = new UtilitiesRootFragment();
        utilitiesFragment = new UtilitiesFragment();


        listFragment.add(resultFragment);
        listFragment.add(statisticRootFragment);
        listFragment.add(scheduleFragment);
        listFragment.add(utilitiesRootFragment);


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


        actionBar.setHomeButtonEnabled(false);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.topbar));


        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                indexFragment = i;
                mViewPager.setCurrentItem(i);
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

            case R.id.action_today:
                initDate();

                resultFragment.setDate(year, month, day);
                resultFragment.loadResult(resultFragment.getCode());
        }

        return super.onOptionsItemSelected(item);
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

        switch (indexFragment) {
            case Common.INDEX_RESULT_FRAGMENT:
                // Currently in ResultFragment

                super.onBackPressed();
                break;


            case Common.INDEX_STATISTIC_FRAGMENT:
                // Currently in Statistic Fragment

                if (indexStatisticFragment == -1) {
                    super.onBackPressed();
                } else {
                    indexStatisticFragment = -1;
                    replaceFragment(R.id.fragment_statistic_root, statisticTypeFragment);
                }
                break;

            case Common.INDEX_SCHEDULE_FRAGMENT:
                // Currently in Schedule Fragment

                super.onBackPressed();
                break;

            case Common.INDEX_UTILITIES_FRAGMENT:
                // Currently in Utilities Fragment
                super.onBackPressed();
                break;

            case Common.INDEX_DREAM_BOOK_FRAGMENT:
                // Currently in DreamBook Fragment
                replaceFragment(R.id.fragment_utilities_root, utilitiesFragment);
                indexFragment = Common.INDEX_UTILITIES_FRAGMENT;
                break;

            case Common.INDEX_TRY_PLAY_FRAGMENT:
                replaceFragment(R.id.fragment_utilities_root, utilitiesFragment);
                indexFragment = Common.INDEX_UTILITIES_FRAGMENT;
                break;

            case Common.INDEX_GOLD_PRICE_FRAGMENT:
                replaceFragment(R.id.fragment_utilities_root, utilitiesFragment);
                indexFragment = Common.INDEX_UTILITIES_FRAGMENT;
                break;

            case Common.INDEX_VAN_TRINH_FRAGMENT:
                if (indexVanTrinhFragment == -1) {
                    replaceFragment(R.id.fragment_utilities_root, utilitiesFragment);
                    indexFragment = Common.INDEX_UTILITIES_FRAGMENT;
                    break;
                } else {
                    replaceFragment(R.id.fragment_utilities_root, new VanTrinhRootFragment());
                    indexVanTrinhFragment = -1;
                }
                break;

            case Common.INDEX_NGU_HANH_FRAGMENT:
                if (indexNguHanhFragment == -1) {
                    replaceFragment(R.id.fragment_utilities_root, utilitiesFragment);
                    indexFragment = Common.INDEX_UTILITIES_FRAGMENT;
                } else {
                    replaceFragment(R.id.fragment_utilities_root, new NguHanhRootFragment());
                    indexNguHanhFragment = -1;
                }
                break;

            case Common.INDEX_FUN_STORY_FRAGMENT:

                if (indexFunStoryFragment == -1) {
                    replaceFragment(R.id.fragment_statistic_root, utilitiesFragment);
                    indexFragment = Common.INDEX_UTILITIES_FRAGMENT;

                } else {
                    replaceFragment(R.id.fragment_utilities_root, new FunStoryRootFragment());
                    indexFunStoryFragment = -1;
                }

                break;
        }
    }


    public void replaceFragment(int currentFragmentID, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(currentFragmentID, fragment);
        fragmentTransaction.commit();
    }


    public void setIndexFragment(int indexFragment) {
        this.indexFragment = indexFragment;
    }

    public void setIndexStatisticFragment(int indexStatisticFragment) {
        this.indexStatisticFragment = indexStatisticFragment;
    }

    public void setIndexVanTrinhFragment(int indexVanTrinhFragment) {
        this.indexVanTrinhFragment = indexVanTrinhFragment;
    }

    public void setIndexNguHanhFragment(int indexNguHanhFragment) {
        this.indexNguHanhFragment = indexNguHanhFragment;
    }

    public void setIndexFunStoryFragment(int indexFunStoryFragment) {
        this.indexFunStoryFragment = indexFunStoryFragment;
    }
}
