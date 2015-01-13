package com.duongnd.android.appsetting;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.Expose;

public class AppSetting {
	@Expose
	private AdsSetting ads;
	@Expose
	private UpdateSetting update;
	@Expose
	private FacebookSetting fb;
	@Expose
	private String ip;
	@Expose
	private Sec sec;

	public AdsSetting getAds() {
		return ads;
	}

	public UpdateSetting getUpdate() {
		return update;
	}

	public FacebookSetting getFb() {
		return fb;
	}

	public String getIp() {
		return ip;
	}
	
	public Sec getSec() {
		return sec;
	}

	public static AppSetting getAppSettingFromJson(String json) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		AppSetting appSetting = null;
		try {
			appSetting = (AppSetting) gson.fromJson(json, AppSetting.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
			return null;
		}
		return appSetting;
	}

	public String toJsonString() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		return gson.toJson(this);
	}
}
