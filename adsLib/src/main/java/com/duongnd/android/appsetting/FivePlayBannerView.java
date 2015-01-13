package com.duongnd.android.appsetting;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.duongnd.android.adslib.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class FivePlayBannerView extends ImageView {
    AdsInfo banner;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    public FivePlayBannerView(Context context) {
	super(context);
	// TODO Auto-generated constructor stub
	init();
    }

    public FivePlayBannerView(Context context, AttributeSet attrs) {
	super(context, attrs);
	init();
    }

    public FivePlayBannerView(Context context, AttributeSet attrs, int defStyle) {
	super(context, attrs, defStyle);
	init();
    }

    public void init() {
	imageLoader = ImageLoader.getInstance();
	options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.imgloading)
		.showImageForEmptyUri(R.drawable.imgerror)
		.showImageOnFail(R.drawable.imgerror).cacheInMemory()
		.cacheOnDisc()// .displayer(new
			      // RoundedBitmapDisplayer(20))
		.build();
    }

    /**
     * display banner
     * 
     * @param banner
     *            : Banner to show
     */
    public void loadData(AdsInfo banner) {
	this.banner = banner;
	showBanner();
	// setImageResource(R.drawable.imgloading);
    }

    private void showBanner() {
	if (imageLoader == null || options == null) {
	    Log.e("Ads", "must call initImageloader before using ads function");
	    return;
	}
	System.out.println(banner.toString());
	imageLoader.displayImage(banner.img, this, options);
	setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View arg0) {
		// TODO Auto-generated method stub
		banner.showStoreApp(FivePlayBannerView.this.getContext());
	    }
	});
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	// TODO Auto-generated method stub
	int widthSize = MeasureSpec.getSize(widthMeasureSpec);
	int heighSize = widthSize * 5 / 32;
	// super.onMeasure(widthSize, heighSize);
	setMeasuredDimension(widthSize, heighSize);
    }
}
