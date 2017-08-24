package com.guolin.glideceshidemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * @author Administrator
 * @describe describe
 * @time 2017/8/24 10:50
 */

public class MyLayout extends RelativeLayout {

	private RelativeLayout mRelativeLayout;
	private CircleProgressView mCircleProgressView;
	private ImageView mImageView;
	private View view;

	public MyLayout(Context context) {
		this(context, null);
	}

	public MyLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MyLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyLayout);
		int resource = typedArray.getInt(R.styleable.MyLayout_back_ground_resouce, 0);
		int color = typedArray.getInt(R.styleable.MyLayout_back_ground_color, 0);
		int scaleType = typedArray.getInt(R.styleable.MyLayout_scale_type, 1);

		View view = LayoutInflater.from(context).inflate(R.layout.ceshi, this, true);
		mRelativeLayout = (RelativeLayout) view.findViewById(R.id.activity_main);
		mImageView = (ImageView) view.findViewById(R.id.icon1);
		mCircleProgressView = (CircleProgressView) view.findViewById(R.id.progressView2);

		mRelativeLayout.setBackgroundColor(color);
		mImageView.setBackgroundResource(resource);
		switch (scaleType) {
			case 1:
				mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
				break;
			case 6:
				mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				break;

			default:
				mImageView.setScaleType(ImageView.ScaleType.CENTER);
				break;
		}

		typedArray.recycle();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);

		/**
		 * 宽高为wrap_content
		 */
		int measuredHeight = mRelativeLayout.getMeasuredHeight();
		int measuredWidth = mRelativeLayout.getMeasuredWidth();
		Log.d("htl", "onMeasure: "+measuredHeight+"---"+measuredWidth);
		if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
			setMeasuredDimension(measuredWidth, measuredHeight);
		} else if (widthMode == MeasureSpec.AT_MOST) {
			setMeasuredDimension(measuredWidth, heightSize);
		} else if (heightMode == MeasureSpec.AT_MOST) {
			setMeasuredDimension(widthSize, measuredHeight);
		}

		/**
		 * 如果宽高都指定
		 */
		ViewGroup.LayoutParams layoutParams = mImageView.getLayoutParams();
		if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
			layoutParams.width = widthSize;
			layoutParams.height = heightSize;
			mImageView.setLayoutParams(layoutParams);
		} else if (widthMode == MeasureSpec.EXACTLY) {
			layoutParams.width = widthSize;
			mImageView.setLayoutParams(layoutParams);
		} else if (heightMode == MeasureSpec.EXACTLY) {
			layoutParams.height = heightSize;
			mImageView.setLayoutParams(layoutParams);
		}

	}

	public ImageView getImageView() {
		if (mImageView != null) {
			Log.d("tag", "getImageView: " + mImageView.toString());
			return mImageView;
		}
		return null;
	}


	public CircleProgressView getCircleProgressView() {
		if (mCircleProgressView != null) {
			return mCircleProgressView;
		}
		return null;
	}
}
