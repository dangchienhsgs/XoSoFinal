package com.duongnd.android.appsetting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;

import com.google.gson.annotations.Expose;

public class UpdateSetting {
	public static final int FORCE_UPDATE = 1;
	public static final int NOT_FORCE_UPDATE = 0;
	@Expose
	String version;
	@Expose
	int force_update;
	@Expose
	String store_url;
	@Expose
	String message;

	public String getVersion() {
		return version;
	}

	public int getForce_update() {
		return force_update;
	}

	public String getStore_url() {
		return store_url;
	}

	public String getMessage() {
		return message;
	}

	public static int getAppVersion(final Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			// should never happen
			e.printStackTrace();
			return 0;
		}
	}

	public String toString() {
		String v = version == null ? "null" : version;
		String s = store_url == null ? "null" : store_url;
		String m = message == null ? "null" : message;
		return "{version=" + v + " force_update=" + force_update
				+ " store_url=" + s + " message=" + m;
	}

	public void showForceUpdate(final Context context) {
		// TODO Auto-generated method stub
		System.out.println("show dialog update");
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Update")
				.setMessage(message)
				.setPositiveButton(android.R.string.yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								try {
									int i = store_url.indexOf("?id=");
									context.startActivity(new Intent(
											Intent.ACTION_VIEW,
											Uri.parse("market://details"
													+ store_url.substring(i))));
								} catch (Exception anfe) {
									context.startActivity(new Intent(
											Intent.ACTION_VIEW, Uri
													.parse(store_url)));
								}
							}
						});
		builder.create().show();
	}

	public void checkUpdate(Context context) {
		if (getForce_update() == UpdateSetting.FORCE_UPDATE) {
			String strVersion = getVersion();
			int version = 0;
			try {
				version = Integer.parseInt(strVersion);
			} catch (NumberFormatException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if (version != 0 && UpdateSetting.getAppVersion(context) < version) {
				showForceUpdate(context);
			}
		}
	}
}
