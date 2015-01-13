package com.duongnd.android.appsetting;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.duongnd.android.adslib.R;
import com.google.gson.annotations.Expose;

public class FivePlayAds {
	@Expose
	String title;
	@Expose
	DirectSetting direct;
	@Expose
	FivePlayApp[] apps;

//	protected ImageLoader imageLoader;
//	protected DisplayImageOptions options;

	public void showMoreGame(Context context) {
		if (direct != null && direct.getEnable() == 1) {
			if (direct.getStore_url() != null) {
				Utils.showStoreApp(context, direct.getStore_url());
				return;
			}
		} else {
			showMoreGameList(context);
		}
	}

	public void showMoreGameList(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		// Get the layout inflater
		LayoutInflater inflater = LayoutInflater.from(context);

		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		View mainView = inflater.inflate(R.layout.diaglog_moregame, null);
		ListView listView = (ListView) mainView
				.findViewById(R.id.dialog_moregame_list);

		// set list
		ArrayList<FivePlayApp> listApp = new ArrayList<FivePlayApp>(
				Arrays.asList(apps));

		MoreGameAdapter moreGameAdapter = new MoreGameAdapter(context,
				listApp);
		for (FivePlayApp app : listApp) {
			System.out.println(app.toString());
		}

		listView.setAdapter(moreGameAdapter);

		builder.setView(mainView);
		// Add action buttons
		builder.setPositiveButton("Close",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						arg0.dismiss();
					}
				});
		final AlertDialog dialog = builder.create();
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.show();
	}

	public String getTitle() {
		return title;
	}

	public DirectSetting getDirect() {
		return direct;
	}

	public FivePlayApp[] getApps() {
		return apps;
	}

	public String toString() {
		String t = title == null ? "null" : title;
		String strDirect = "";
		StringBuilder strBuilerdApps = new StringBuilder();
		if (direct == null) {
			strDirect = "null";
		} else {
			strDirect = direct.toString();
		}
		if (apps == null) {
			strBuilerdApps.append("null");
		} else {
			strBuilerdApps.append("[");
			for (FivePlayApp app : apps) {
				strBuilerdApps.append("  e=");
				if (apps == null) {
					strBuilerdApps.append("null");
				} else {
					strBuilerdApps.append(app.toString());
				}
			}
			strBuilerdApps.append("]");
		}
		return "title=" + t + " direct={" + strDirect + "} apps="
				+ strBuilerdApps.toString();
	}

}