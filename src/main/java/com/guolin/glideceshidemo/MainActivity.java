package com.guolin.glideceshidemo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

public class MainActivity extends AppCompatActivity {

	private MyLayout mMyLayout,mMyLayout2;
	private String mUrl = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/screenshot/cat.jpg";
	String url1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497688355699&di=ea69a930b82ce88561c635089995e124&imgtype=0&src=http%3A%2F%2Fcms-bucket.nosdn.127.net%2Ff84e566bcf654b3698363409fbd676ef20161119091503.jpg";
	private static final String TAG = "GlideActivitty";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();

		final OnProgressListener listener1 = new OnProgressListener() {
			@Override
			public void onProgress(final String imageUrl, final long bytesRead, final long totalBytes, final boolean isDone, GlideException exception) {
				if(!imageUrl.equals(mUrl))return;

				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mMyLayout.getCircleProgressView().setVisibility(View.VISIBLE);
						int percent = (int) (bytesRead*100/totalBytes);
						mMyLayout.getCircleProgressView().setProgress(percent);
						mMyLayout.getCircleProgressView().setVisibility(isDone?View.GONE:View.VISIBLE);
						Log.d(TAG, percent+"=========1 "+imageUrl+"==="+mMyLayout.getCircleProgressView().toString());

					}
				});
			}
		};

		final OnProgressListener listener2 = new OnProgressListener() {
			@Override
			public void onProgress(final String imageUrl, final long bytesRead, final long totalBytes, final boolean isDone, GlideException exception) {
				if(!url1.equals(imageUrl))return;

				runOnUiThread(new Runnable() {
					@Override
					public void run() {

						mMyLayout2.getCircleProgressView().setVisibility(View.VISIBLE);
						int percent = (int) (bytesRead*100/totalBytes);
						mMyLayout2.getCircleProgressView().setProgress(percent);
						mMyLayout2.getCircleProgressView().setVisibility(isDone?View.GONE:View.VISIBLE);
						Log.d(TAG, percent+"=========2 "+imageUrl);
					}
				});
			}
		};
		ProgressManager.addProgressListener(listener1);

		ProgressManager.addProgressListener(listener2);

		RequestOptions options = new RequestOptions();
		options.diskCacheStrategy(DiskCacheStrategy.RESOURCE).skipMemoryCache(false);
		RequestOptions options2 = new RequestOptions();
		options.diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true);

		Glide.with(this).load(mUrl).apply(options).listener(new RequestListener<Drawable>() {
			@Override
			public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
				mMyLayout.getCircleProgressView().setVisibility(View.GONE);
				ProgressManager.removeProgressListener(listener1);
					return false;
			}

			@Override
			public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
				mMyLayout.getCircleProgressView().setVisibility(View.GONE);
				ProgressManager.removeProgressListener(listener1);
				return false;
			}
		}).into(mMyLayout.getImageView());
		Glide.with(this).load(url1).apply(options2).listener(new RequestListener<Drawable>() {
			@Override
			public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
				mMyLayout2.getCircleProgressView().setVisibility(View.GONE);
				ProgressManager.removeProgressListener(listener2);
					return false;
			}

			@Override
			public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
				mMyLayout2.getCircleProgressView().setVisibility(View.GONE);
				ProgressManager.removeProgressListener(listener2);

				return false;
			}
		}).into(mMyLayout2.getImageView());
	}

	private void initView() {
		mMyLayout = (MyLayout) findViewById(R.id.layout);
		mMyLayout2 = (MyLayout) findViewById(R.id.layout2);
	}
}
