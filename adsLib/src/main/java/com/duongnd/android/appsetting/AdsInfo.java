package com.duongnd.android.appsetting;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.google.gson.annotations.Expose;

public class AdsInfo {
	@Expose
	int admob;
	@Expose
	int fiveplay;
	@Expose
	int frequent;
	@Expose
	String img;
	@Expose
	String store_url;
	@Expose
	String img_vertical;
	@Expose
	String img_horizontal;

	public enum AdsType {
		ADMOB, FIVEPLAY, DISABLE
	};

	public AdsInfo(int admob, int fiveplay) {
		this.admob = admob;
		this.fiveplay = fiveplay;
	}

	/**
	 * Generate AdsType depends on admob and fiveplay
	 * 
	 * @return: AdsType: FivePlay/Admob/Disable
	 */
	public AdsType gendAds() {
		if (admob == 0 && fiveplay == 0) {
			return AdsType.DISABLE;
		}
		if (admob == 0) {
			return AdsType.FIVEPLAY;
		}
		if (fiveplay == 0) {
			return AdsType.ADMOB;
		}

		double p = (admob + fiveplay) * Math.random();
		System.out.println("50 50 " + p);
		if (p < admob) {
			return AdsType.ADMOB;
		} else {
			return AdsType.FIVEPLAY;
		}
	}

	public void showStoreApp(Context context) {
		if (store_url.startsWith("market")) {
			try {
				context.startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse(store_url)));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				context.startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse(store_url)));
			}
		} else if (store_url.startsWith("http")) {
			int i = store_url.indexOf("?id=");
			try {
				context.startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse("market://details" + store_url.substring(i))));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				context.startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse(store_url)));
			}
		}
	}

	public int getAdmob() {
		return admob;
	}

	public int getFiveplay() {
		return fiveplay;
	}

	public int getFrequent() {
		return frequent;
	}

	public String getImg() {
		return img;
	}

	public String getStore_url() {
		return store_url;
	}

	public String getImg_vertical() {
		return img_vertical;
	}

	public String getImg_horizontal() {
		return img_horizontal;
	}

	public String toString() {
		return "admob=" + admob + " m5play=" + fiveplay + " frequent="
				+ frequent + " img=" + img + " store_url=" + store_url
				+ " img_vertical=" + img_vertical + " img_horizontal="
				+ img_horizontal;
	}
}
