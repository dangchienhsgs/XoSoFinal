package com.duongnd.android.appsetting;

import com.google.gson.annotations.Expose;

public class Sec {
	@Expose
	int mode;
	@Expose
	int time;
	@Expose
	String link;

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
