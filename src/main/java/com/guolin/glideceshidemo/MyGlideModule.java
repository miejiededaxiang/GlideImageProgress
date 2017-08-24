package com.guolin.glideceshidemo;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

/**
 * @author Administrator
 * @describe describe
 * @time 2017/8/23 18:25
 */

@GlideModule
public class MyGlideModule extends AppGlideModule{

	@Override
	public void registerComponents(Context context, Glide glide, Registry registry) {
		super.registerComponents(context, glide, registry);
		registry.replace(GlideUrl.class, InputStream.class,
				new OkHttpUrlLoader.Factory(ProgressManager.getOkHttpClient()));
	}
}
