package com.duongnd.android.appsetting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;

public class Utils {
	public static String readFile(String path) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(path));

			while ((sCurrentLine = br.readLine()) != null) {
				// System.out.println(sCurrentLine);
				sb.append(sCurrentLine);
				sb.append("\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static void showStoreApp(Context context, String store_url) {
		if (store_url.startsWith("market")) {
			try {
				context.startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse(store_url)));
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				context.startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse(store_url)));
			}
		} else if (store_url.startsWith("http://")
				|| store_url.startsWith("https://")) {
			int i = store_url.indexOf("?id=");
			// simple web url
			if (i == -1) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW,
						Uri.parse(store_url));
				context.startActivity(browserIntent);
				return;
			}
			// play store link:
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

	public static float dpFromPx(float px) {
		return px / Resources.getSystem().getDisplayMetrics().density;
	}

	public static float pxFromDp(float dp) {
		return dp * Resources.getSystem().getDisplayMetrics().density;
	}
}
