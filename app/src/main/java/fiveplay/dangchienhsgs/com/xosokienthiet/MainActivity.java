package fiveplay.dangchienhsgs.com.xosokienthiet;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;
import com.google.analytics.tracking.android.EasyTracker;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fiveplay.dangchienhsgs.com.xosokienthiet.adapter.TabsPagerAdapter;
import fiveplay.dangchienhsgs.com.xosokienthiet.dialogs.datepicker.MyDatePickerDialogs;

import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import android.app.ProgressDialog;

import android.widget.Toast;

import com.duongnd.android.appsetting.AdsSetting;
import com.duongnd.android.appsetting.AppSetting;
import com.duongnd.android.appsetting.BannerViewComb;
import com.duongnd.android.appsetting.SettingManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.HttpClientImageDownloader;


public class MainActivity extends ActionBarActivity implements MyDatePickerDialogs.DatePickerListener {
    private static final String AD_UNIT_ID_BANNER = "ca-app-pub-8823501134821899/7709758362";
    private static final String AD_UNIT_ID_POPUP = "ca-app-pub-8823501134821899/9186491569";
    private final String SETTING_SERVER_URL = "http://5play.mobi/apps/batchu/batchu.setting.android.json";
    ProgressDialog progress;
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
    private AppSetting appSetting;
    private AdsSetting adsSetting;
    private BannerViewComb bannerViewComb;
    private SettingManager settingManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
        initTabs();
        initDate();
        initMenu();

        initAd();
    }

    public void initAd(){
        initImageLoader();

        settingManager = new SettingManager(MainActivity.this);

        loadAppSetting(null);

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
                menu.setSlidingEnabled(true);
                indexFragment = Common.INDEX_RESULT_FRAGMENT;
                mViewPager.setCurrentItem(Common.INDEX_RESULT_FRAGMENT);
                break;
            case R.id.image_thongke:
                menu.setSlidingEnabled(false);
                indexFragment = Common.INDEX_STATISTIC_FRAGMENT;
                mViewPager.setCurrentItem(Common.INDEX_STATISTIC_FRAGMENT);
                break;
            case R.id.image_lichquay:
                menu.setSlidingEnabled(false);
                indexFragment = Common.INDEX_SCHEDULE_FRAGMENT;
                mViewPager.setCurrentItem(Common.INDEX_SCHEDULE_FRAGMENT);
                break;
            case R.id.image_tienich:
                menu.setSlidingEnabled(false);
                indexFragment = Common.INDEX_UTILITIES_FRAGMENT;
                mViewPager.setCurrentItem(Common.INDEX_UTILITIES_FRAGMENT);
                break;
        }
        menu.toggle(true);

        menu.setSlidingEnabled(false);
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

    @Override
    protected void onStart() {
        super.onStart();

        EasyTracker.getInstance(this).activityStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        EasyTracker.getInstance(this).activityStop(this);
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

        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_topbar));


        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    menu.setSlidingEnabled(true);
                } else {
                    menu.setSlidingEnabled(false);
                }

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
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        getActionBar().setCustomView(LayoutInflater.from(this).inflate(R.layout.menu_layout, null));
        getActionBar().setDisplayShowCustomEnabled(true);
        getActionBar().setHomeButtonEnabled(false);
        //disable application icon from ActionBar
        getActionBar().setDisplayShowHomeEnabled(false);

        //disable application name from ActionBar
        getActionBar().setDisplayShowTitleEnabled(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.image_choose_day:
                MyDatePickerDialogs dialogs = new MyDatePickerDialogs();

                dialogs.setInitDate(year, month, day);
                dialogs.setDatePickerListener(this);
                dialogs.show(getSupportFragmentManager(), "DatePicker");
                break;

            case R.id.image_today:
                initDate();

                resultFragment.setDate(year, month, day);
                resultFragment.loadResult(resultFragment.getCode());

                break;
            case R.id.image_open_drawer:

                if (menu.isMenuShowing()) {
                    menu.showMenu();
                } else {
                    menu.toggle(true);
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onMenuItemClicked(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.image_choose_day:
                MyDatePickerDialogs dialogs = new MyDatePickerDialogs();

                dialogs.setInitDate(year, month, day);
                dialogs.setDatePickerListener(this);
                dialogs.show(getSupportFragmentManager(), "DatePicker");
                break;

            case R.id.image_today:
                initDate();

                resultFragment.setDate(year, month, day);
                resultFragment.loadResult(resultFragment.getCode());

                break;
            case R.id.image_open_drawer:

                if (menu.isMenuShowing()) {
                    menu.showMenu();
                } else {
                    menu.toggle(true);
                }
                break;
        }
    }


    // Ad

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
                    replaceFragment(R.id.fragment_utilities_root, utilitiesFragment);
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

    private void initImageLoader() {
        HttpParams params = new BasicHttpParams();
        // Turn off stale checking. Our connections break all the time anyway,
        // and it's not worth it to pay the penalty of checking every time.
        HttpConnectionParams.setStaleCheckingEnabled(params, false);
        // Default connection and socket timeout of 10 seconds. Tweak to taste.
        HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
        HttpConnectionParams.setSoTimeout(params, 10 * 1000);
        HttpConnectionParams.setSocketBufferSize(params, 8192);

        // Don't handle redirects -- return them to the caller. Our code
        // often wants to re-POST after a redirect, which we must do ourselves.
        HttpClientParams.setRedirecting(params, false);
        // Set the specified user agent and register standard protocols.
        HttpProtocolParams.setUserAgent(params, "some_randome_user_agent");
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory
                .getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory
                .getSocketFactory(), 443));

        ClientConnectionManager manager = new ThreadSafeClientConnManager(
                params, schemeRegistry);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this)
                // .imageDownloader(new BaseImageDownloader(this, 20000, 40000))
                .imageDownloader(
                        new HttpClientImageDownloader(this,
                                new DefaultHttpClient(manager, params)))
                .denyCacheImageMultipleSizesInMemory().build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

    public void loadAppSetting(View v) {
        // TODO Auto-generated method stub
        LoadSettingTask task = new LoadSettingTask();

        task.execute();
    }

    public void initAds() {
        // TODO Auto-generated method stub
        // init image loader
        adsSetting.initImageLoader();
        // init add id
        adsSetting.setAD_UNIT_ID_BANNER(AD_UNIT_ID_BANNER);
        adsSetting.setAD_UNIT_ID_POPUP(AD_UNIT_ID_POPUP);

        // init view
        bannerViewComb = (BannerViewComb) findViewById(R.id.bannerComb);
        bannerViewComb.setAD_UNIT_ID(adsSetting.getAD_UNIT_ID_BANNER());
        bannerViewComb.loadData(adsSetting.getBanner(), this);
        // pre load bitmap:
        adsSetting.loadBitmapPopupVer();
        adsSetting.loadInterstitial(this);
    }

    public void showBannerDef(View v) {
        if (adsSetting != null) {
            adsSetting.showBannerDef(bannerViewComb);
        } else {
            printToast("Chua load file setting");
        }
    }

    public void showAdsInterDef(View v) {
        if (adsSetting != null) {
            adsSetting.showAdsInterDef(this);
        } else {
            printToast("Chua load file setting");
        }
    }

    public void printToast(final String str) {
        this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT)
                        .show();
            }
        });

    }

    private class LoadSettingTask extends AsyncTask<Void, Void, AppSetting> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            progress = ProgressDialog.show(MainActivity.this, "Loading",
                    "Đang load setting", true);
        }

        @Override
        protected AppSetting doInBackground(Void... params) {
            // TODO Auto-generated method stub
            // Load setting from server
            AppSetting result = settingManager
                    .getSettingFromServer(SETTING_SERVER_URL);
            return result;
        }

        @Override
        protected void onPostExecute(AppSetting result) {
            // TODO Auto-generated method stub
            if (progress != null) {
                progress.dismiss();
            }
            // Error khi load tu server
            if (result == null) {
                Log.d(TAG, "Loi khi load setting tu server");
                // Dung setting dc save tu lan truoc
                settingManager.loadSavedAppSetting();
                appSetting = settingManager.getAppSetting();
                if (appSetting == null) {
                    MainActivity.this
                            .printToast("Khong the load appSetting tu local");
                } else {
                    Log.d(TAG, "Dung appSetting tu local");
                }
            } else {
                // Load tu server thanh cong
                Log.d(TAG, "load setting tu server thanh cong");
                appSetting = result;
                settingManager.saveAppSetting(appSetting);
            }
            if (appSetting != null) {
                adsSetting = appSetting.getAds();
                if (appSetting != null) {
                    initAds();
                }
            }
        }
    }
}
