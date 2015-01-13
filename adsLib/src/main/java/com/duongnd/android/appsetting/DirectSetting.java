package com.duongnd.android.appsetting;

import com.google.gson.annotations.Expose;

public class DirectSetting {
	@Expose
	int enable;
	@Expose
	String store_url;

	public int getEnable() {
		return enable;
	}

	public String getStore_url() {
		return store_url;
	}

	public String toString() {
		if (store_url == null) {
			return "enable=" + enable + "store_url=null";
		}
		return "{enable=" + enable + "store_url=" + store_url + "}";
	}
}