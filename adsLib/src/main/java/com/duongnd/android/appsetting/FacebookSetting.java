package com.duongnd.android.appsetting;

import com.google.gson.annotations.Expose;

public class FacebookSetting {
	@Expose
	String page;
	@Expose
	String app_id;
	
	public String getPage() {
		return page;
	}

	public String getApp_id() {
		return app_id;
	}

	public String toString() {
		String p = page == null ? "null" : page;
		String a = app_id == null ? "null" : app_id;

		return "{page=" + p + " app_id=" + a + "}";
	}
}
