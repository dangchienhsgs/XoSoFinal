package com.duongnd.android.appsetting;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class BannerViewComb extends RelativeLayout {
	private float widthHeighRatio = 6.4f;

	private AdView adView;
	private FivePlayBannerView fivePlayBannerView;
	private String AD_UNIT_ID = "";
	private boolean isLoaded = false;

	public BannerViewComb(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public BannerViewComb(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public BannerViewComb(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {
		// setBackgroundColor(Color.BLACK);
	}

	public void loadData(AdsInfo banner, Context context) {
		if (isLoaded) {
			return;
		}
		isLoaded = true;
		addAdmob(context);
		addFivePlay(banner, context);
		showFivePlay();
	}

	private void addAdmob(Context context) {
		// Create an ad.
		adView = new AdView(context);
		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId(AD_UNIT_ID);

		// Add the AdView to the view hierarchy. The view will have no size
		// until the ad is loaded.
		addView(adView);

		// Create an ad request. Check logcat output for the hashed device ID to
		// get test ads on a physical device.
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("3C79EE1409D65F092E588C4A0DFA23AD").build();

		// Start loading the ad in the background.
		adView.loadAd(adRequest);
	}

	private void addFivePlay(AdsInfo banner, Context context) {
		if (banner == null) {
			fivePlayBannerView = null;
			return;
		}
		fivePlayBannerView = new FivePlayBannerView(context);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		fivePlayBannerView.setScaleType(ScaleType.FIT_XY);
		layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

		// scaleImage(fivePlayBannerView);
		fivePlayBannerView.setLayoutParams(layoutParams);
		addView(fivePlayBannerView);
		fivePlayBannerView.loadData(banner);
	}

	public void showAdmob() {
		adView.setVisibility(View.VISIBLE);
		if (fivePlayBannerView != null) {
			fivePlayBannerView.setVisibility(View.INVISIBLE);
		}
	}

	public void showFivePlay() {
		if (fivePlayBannerView != null) {
			fivePlayBannerView.setVisibility(View.VISIBLE);
			adView.setVisibility(View.INVISIBLE);
		}
	}

	public String getAD_UNIT_ID() {
		return AD_UNIT_ID;
	}

	public void setAD_UNIT_ID(String aD_UNIT_ID) {
		AD_UNIT_ID = aD_UNIT_ID;
	}

	// private void scaleImage(ImageView view) {
	// int width = 450;
	// System.out.println("WIDTHHHHH =" + width);
	// RelativeLayout.LayoutParams layoutParams = new
	// RelativeLayout.LayoutParams(
	// width, (int) (width / widthHeighRatio));
	// layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
	// }

}
