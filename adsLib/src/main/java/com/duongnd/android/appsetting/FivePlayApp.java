package com.duongnd.android.appsetting;

import com.google.gson.annotations.Expose;

public class FivePlayApp {
	@Expose
	String icon;
	@Expose
	String title;
	@Expose
	String description;
	@Expose
	String store_url;

	public String toString() {
		String i = icon == null ? "null" : icon;
		String t = title == null ? "null" : title;
		String d = description == null ? "null" : description;
		String s = store_url == null ? "null" : store_url;
		return "{icon=" + i + " title=" + t + " description=" + d
				+ " store_url=" + s + "}";
	}
}