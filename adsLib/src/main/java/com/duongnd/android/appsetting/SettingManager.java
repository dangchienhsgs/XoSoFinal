package com.duongnd.android.appsetting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SettingManager {
	AppSetting appSetting;
	private final String PREFS_NAME = "2deb000b57bfac9d72c14d4ed967b572_ADS_PREFSNAME";
	private static final String PROPERTY_JSON = "json";
	private static final String TAG = "Ads SettingManager";
	private Context context;

	public SettingManager(Context context) {
		this.context = context;
	}

	/**
	 * getSettingFromServer
	 * 
	 * @param url
	 * @return AppSetting. Null if error
	 */
	public AppSetting getSettingFromServer(String url) {
		StringBuilder response = new StringBuilder();
		try {
			URL mUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) mUrl.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				return null;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String line;

			while ((line = br.readLine()) != null) {
				response.append(line);
				response.append("\n");
			}
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		AppSetting result = AppSetting.getAppSettingFromJson(response.toString());
//		System.out.println(response);
//		System.out.println(result.toJsonString());
		return result;
	}

	public boolean saveAppSetting(AppSetting appSetting) {
		if (appSetting == null) {
			return false;
		}
		this.appSetting = appSetting;
		final SharedPreferences prefs = getGCMPreferences(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(PROPERTY_JSON, appSetting.toJsonString());
		editor.commit();
		System.out.println("saved:"
				+ appSetting.toJsonString());
		return true;
	}

	public boolean loadSavedAppSetting() {
		final SharedPreferences prefs = getGCMPreferences(context);
		String jsonString = prefs.getString(PROPERTY_JSON, "");
		if (jsonString.length() == 0) {
			Log.i(TAG, "Json setting not found.");
			appSetting = null;
			return false;
		} else {
			appSetting = AppSetting.getAppSettingFromJson(jsonString);
			if (appSetting == null) {
				return false;
			} else {
				return true;
			}
		}
	}

	private SharedPreferences getGCMPreferences(final Context context) {
		// persists the registration ID in shared preferences,
		return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
	}

	public AppSetting getAppSetting() {
		return appSetting;
	}

}
