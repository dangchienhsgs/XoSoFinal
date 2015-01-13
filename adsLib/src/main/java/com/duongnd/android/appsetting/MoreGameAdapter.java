package com.duongnd.android.appsetting;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.duongnd.android.adslib.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MoreGameAdapter extends BaseAdapter {
	Context context;
	ArrayList<FivePlayApp> listGame;
	private ImageLoader imageLoader;
	private DisplayImageOptions options;

	public MoreGameAdapter(Context context, ArrayList<FivePlayApp> listItem) {
		this.context = context;
		// this.listGame = listItem;
		this.listGame = new ArrayList<FivePlayApp>();
		for (FivePlayApp app : listItem) {
			if (app.title != null && app.title.length() > 0) {
				this.listGame.add(app);
			}
		}
		this.imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.imgloading)
				.showImageForEmptyUri(R.drawable.imgerror)
				.showImageOnFail(R.drawable.imgerror).cacheInMemory()
				.cacheOnDisc()// .displayer(new
								// RoundedBitmapDisplayer(20))
				.build();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listGame.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listGame.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View rowView = convertView;
		FivePlayApp app = listGame.get(position);
		// reuse views
		if (rowView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			rowView = inflater.inflate(R.layout.moregame_item, null);
			// configure view holder
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.image = (ImageView) rowView
					.findViewById(R.id.moregame_item_img);
			viewHolder.title = (TextView) rowView
					.findViewById(R.id.moregame_item_title);
			viewHolder.des = (TextView) rowView
					.findViewById(R.id.moregame_item_des);
			rowView.setTag(viewHolder);
		}

		// fill data
		ViewHolder holder = (ViewHolder) rowView.getTag();
		imageLoader.displayImage(app.icon, holder.image, options);
		holder.title.setText(app.title);
		holder.title.setText(app.description);
		return rowView;
	}

	static class ViewHolder {
		public TextView title;
		public TextView des;
		public ImageView image;
	}
}
