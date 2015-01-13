package com.duongnd.android.appsetting;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

import com.duongnd.android.adslib.R;
import com.duongnd.android.appsetting.AdsInfo.AdsType;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.annotations.Expose;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

public class AdsSetting {
	@Expose
	private AdsInfo banner;
	@Expose
	private AdsInfo popup;
	@Expose
	private AdsInfo app_open;
	@Expose
	private AdsInfo app_exit;
	@Expose
	private FivePlayAds fiveplay;

	// Bitmap to show
	// <Importaint> just implement popup
	Bitmap bmBanner;
	Bitmap bmPopupVer;
	Bitmap bmPopupHor;
	Bitmap bmAppOpenVer;
	Bitmap bmAppOpenHor;
	Bitmap bmAppExitVer;
	Bitmap bmAppExitHor;

	boolean isLoadedBanner = false;
	boolean isLoadedPopupVer = false;
	boolean isLoadedPopupHor = false;
	boolean isLoadedAppOpenVer = false;
	boolean isLoadedAppOpenHor = false;
	boolean isLoadedAppExitVer = false;
	boolean isLoadedAppExitHor = false;

	protected ImageLoader imageLoader;
	protected DisplayImageOptions options = new DisplayImageOptions.Builder()
			.decodingOptions(new Options())
			.imageScaleType(ImageScaleType.NONE).build();;
	// ca-app-pub-9486155718744987/4912613753
	private String AD_UNIT_ID_BANNER = "";
	private String AD_UNIT_ID_POPUP = "";
	private InterstitialAd interstitial;

	public enum Orientation {
		VERTICAL, HORIZONTAL
	};

	public AdsInfo getBanner() {
		return banner;
	}

	public void initImageLoader() {
		imageLoader = ImageLoader.getInstance();
	}

	public void loadBitmapPopupVer() {
		Log.i("AdsLib", "loadBitmapPopupVer");
		imageLoader.loadImage(popup.getImg_vertical(), options,
				new SimpleImageLoadingListener() {
					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						// TODO Auto-generated method stub
						bmPopupVer = loadedImage;
						isLoadedPopupVer = true;
						Log.i("AdsLib", "loadBitmapPopupVer Success");
					}
				});
	}

	public void loadBitmapPopupHor() {
		Log.i("AdsLib", "loadBitmapPopupHor");
		imageLoader.loadImage(popup.getImg_horizontal(), options,
				new SimpleImageLoadingListener() {
					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						// TODO Auto-generated method stub
						bmPopupHor = loadedImage;
						isLoadedPopupHor = true;
						Log.i("AdsLib", "loadBitmapPopupHor Success");
					}
				});
	}

	public void loadBitmapAppOpenVer() {
		Log.i("AdsLib", "loadBitmapAppOpenVer");
		imageLoader.loadImage(app_open.img_vertical, options,
				new SimpleImageLoadingListener() {
					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						// TODO Auto-generated method stub
						bmAppOpenVer = loadedImage;
						isLoadedAppOpenVer = true;
						Log.i("AdsLib", "loadBitmapAppOpenVer success");
					}
				});
	}

	public void loadBitmapAppOpenHor() {
		Log.i("AdsLib", "loadBitmapAppOpenHor");
		imageLoader.loadImage(app_open.img_horizontal, options,
				new SimpleImageLoadingListener() {
					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						// TODO Auto-generated method stub
						bmAppOpenHor = loadedImage;
						isLoadedAppOpenHor = true;
						Log.i("AdsLib", "loadBitmapAppOpenHor success");
					}
				});
	}

	public void loadBitmapAppExitVer() {
		Log.i("AdsLib", "loadBitmapAppExitVer");
		imageLoader.loadImage(app_exit.img_vertical, options,
				new SimpleImageLoadingListener() {
					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						// TODO Auto-generated method stub
						bmAppExitVer = loadedImage;
						isLoadedAppExitVer = true;
						Log.i("AdsLib", "loadBitmapAppExitVer success");
					}
				});
	}

	public void loadBitmapAppExitHor() {
		Log.i("AdsLib", "loadBitmapAppExitHor");
		imageLoader.loadImage(app_exit.img_horizontal, options,
				new SimpleImageLoadingListener() {
					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						// TODO Auto-generated method stub
						bmAppExitHor = loadedImage;
						isLoadedAppExitHor = true;
						Log.i("AdsLib", "loadBitmapAppExitHor success");
					}
				});
	}

	public void showPopup(Orientation orientation, final Context context,
			AdsListener adsListener) {
		if (popup == null) {
			return;
		}
		if (orientation == Orientation.VERTICAL) {
			if (bmPopupVer == null) {
				return;
			}
			showImageDialog(bmPopupVer, context, adsListener);
		} else {
			if (bmPopupHor == null) {
				return;
			}
			showImageDialog(bmPopupHor, context, adsListener);
		}
	}

	public void showAppOpen(Orientation orientation, final Context context,
			AdsListener adsListener) {
		if (app_open == null) {
			return;
		}
		if (orientation == Orientation.VERTICAL) {
			if (bmAppOpenVer == null) {
				return;
			}
			showImageDialog(bmAppOpenVer, context, adsListener);
		} else {
			if (bmAppOpenHor == null) {
				return;
			}
			showImageDialog(bmAppOpenHor, context, adsListener);
		}
	}

	public void showAppExit(Orientation orientation, final Context context,
			AdsListener adsListener) {
		if (app_exit == null) {
			return;
		}
		if (orientation == Orientation.VERTICAL) {
			if (bmAppExitVer == null) {
				return;
			}
			showImageDialog(bmAppExitVer, context, adsListener);
		} else {
			if (bmAppExitHor == null) {
				return;
			}
			showImageDialog(bmAppExitHor, context, adsListener);
		}
	}

	private void showImageDialog(Bitmap bitmap, final Context context,
			final AdsListener adsListener) {
		Log.i("AdsLib", "show image dialog");
		final Dialog ad = new Dialog(context);
		ad.requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Get the layout inflater
		LayoutInflater inflater = LayoutInflater.from(context);

		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		View mainView = inflater.inflate(R.layout.diaglog_image, null);
		ImageView img = (ImageView) mainView.findViewById(R.id.image);
		// imageLoader.displayImage(imgUrl, img, options);

		ImageButton btn = (ImageButton) mainView.findViewById(R.id.btn_close);

		ad.setContentView(mainView);
		ad.getWindow().setLayout(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		ad.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		ad.setCancelable(false);
		ad.setCanceledOnTouchOutside(false);
		BitmapDrawable ob = new BitmapDrawable(context.getResources(), bitmap);
		img.setImageDrawable(ob);

		// Add action buttons
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					adsListener.onClickClose();
					ad.dismiss();
					;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					adsListener.onClickAds(popup.store_url);
					popup.showStoreApp(context);
					ad.dismiss();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		ad.show();
	}

	@SuppressWarnings("unused")
	private void showImageDialog(String imgUrl, final Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		// Get the layout inflater
		LayoutInflater inflater = LayoutInflater.from(context);

		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		View mainView = inflater.inflate(R.layout.diaglog_image, null);
		ImageView img = (ImageView) mainView.findViewById(R.id.image);
		// imageLoader.displayImage(imgUrl, img, options);

		ImageButton btn = (ImageButton) mainView.findViewById(R.id.btn_close);

		builder.setView(mainView);
		// Add action buttons
		final AlertDialog dialog = builder.create();
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				popup.showStoreApp(context);
			}
		});
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		imageLoader.displayImage(imgUrl, img, options,
				new SimpleImageLoadingListener() {

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						// TODO Auto-generated method stub
						dialog.show();
					}
				});
	}

	public void loadInterstitial(Context context) {
		System.out.println("load interstitial");
		if (interstitial == null) {
			interstitial = new InterstitialAd(context);
			interstitial.setAdUnitId(AD_UNIT_ID_POPUP);
		}
		// Create ad request.
		AdRequest adRequest2 = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("3C79EE1409D65F092E588C4A0DFA23AD").build();

		// Begin loading your interstitial.
		interstitial.loadAd(adRequest2);
	}

	public void showInterstitial(Context context) {
		interstitial.show();
	}

	public void forceShowInterstitial(Context context) {
		if (interstitial == null) {
			interstitial = new InterstitialAd(context);
			interstitial.setAdUnitId(AD_UNIT_ID_POPUP);
		}
		if (interstitial.isLoaded()) {
			interstitial.show();
		} else {
			// Create ad request.
			AdRequest adRequest2 = new AdRequest.Builder()
					.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
					.addTestDevice("3C79EE1409D65F092E588C4A0DFA23AD").build();

			// Begin loading your interstitial.
			interstitial.setAdListener(new AdListener() {
				public void onAdLoaded() {
					interstitial.show();
				}
			});
			interstitial.loadAd(adRequest2);
		}
	}
	
	public void showBannerDef(BannerViewComb bannerViewComb) {
		AdsInfo banner = getBanner();
		if (banner != null) {
			switch (banner.gendAds()) {
			case ADMOB:
				// show Admob
				bannerViewComb.showAdmob();
				printDebug("Banner Admob");
				break;
			case FIVEPLAY:
				// show FivePlay
				bannerViewComb.showFivePlay();
				printDebug("Banner Fiveplay");
				break;
			case DISABLE:
				// disable
				bannerViewComb.setVisibility(View.INVISIBLE);
				break;
			}
		}
	}
	
	private void printDebug(String str) {
		Log.d("AdsLib", str);
	}
	
	public void showAdsInterDef(Context context) {
		AdsInfo popup = getPopup();
		if (popup != null) {
			int frequent = popup.getFrequent();
			// disable
			if (frequent == 0) {
				return;
			}
			AdsType adsType = popup.gendAds();
			switch (adsType) {
			case ADMOB:
				// show Admob
				if (getInterstitial() == null
						|| !getInterstitial().isLoaded()) {
					loadInterstitial(context);
					printDebug("Popup Admob chua load");
				} else {
					getInterstitial().setAdListener(
							new AdListener() {
							});
					printDebug("Popup Admob");
					showInterstitial(context);
					loadInterstitial(context);
				}
				break;
			case FIVEPLAY:
				// show FivePlay
				if (isLoadedPopupVer()) {
					showPopup(Orientation.VERTICAL, context,
							new SimpleAdsListener());
					printDebug("Popup FIVEPLAY");
				} else {
					loadBitmapPopupVer();
					printDebug("Popup FIVEPLAY chua load");
				}
				break;
			case DISABLE:
				;
				break;
			}

		}
	}

	public boolean isLoadedInterstitial() {
		return interstitial.isLoaded();
	}

	public InterstitialAd getInterstitial() {
		return interstitial;
	}

	public void setBanner(AdsInfo banner) {
		this.banner = banner;
	}

	public AdsInfo getPopup() {
		return popup;
	}

	public void setPopup(AdsInfo popup) {
		this.popup = popup;
	}

	public AdsInfo getApp_open() {
		return app_open;
	}

	public void setApp_open(AdsInfo app_open) {
		this.app_open = app_open;
	}

	public AdsInfo getApp_exit() {
		return app_exit;
	}

	public void setApp_exit(AdsInfo app_exit) {
		this.app_exit = app_exit;
	}

	public FivePlayAds getFiveplay() {
		return fiveplay;
	}

	public void setFiveplay(FivePlayAds fiveplay) {
		this.fiveplay = fiveplay;
	}

	public String getAD_UNIT_ID_BANNER() {
		return AD_UNIT_ID_BANNER;
	}

	public void setAD_UNIT_ID_BANNER(String aD_UNIT_ID_BANNER) {
		AD_UNIT_ID_BANNER = aD_UNIT_ID_BANNER;
	}

	public String getAD_UNIT_ID_POPUP() {
		return AD_UNIT_ID_POPUP;
	}

	public void setAD_UNIT_ID_POPUP(String aD_UNIT_ID_POPUP) {
		AD_UNIT_ID_POPUP = aD_UNIT_ID_POPUP;
	}

	public ImageLoader getImageLoader() {
		return imageLoader;
	}

	public DisplayImageOptions getOptions() {
		return options;
	}

	public boolean isLoadedBanner() {
		return isLoadedBanner;
	}

	public boolean isLoadedPopupVer() {
		return isLoadedPopupVer;
	}

	public boolean isLoadedPopupHor() {
		return isLoadedPopupHor;
	}

	public boolean isLoadedAppOpenVer() {
		return isLoadedAppOpenVer;
	}

	public boolean isLoadedAppOpenHor() {
		return isLoadedAppOpenHor;
	}

	public boolean isLoadedAppExitVer() {
		return isLoadedAppExitVer;
	}

	public boolean isLoadedAppExitHor() {
		return isLoadedAppExitHor;
	}

}
