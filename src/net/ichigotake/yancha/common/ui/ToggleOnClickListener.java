package net.ichigotake.yancha.common.ui;

import android.view.View;
import android.view.View.OnClickListener;

import com.google.common.base.Optional;

/**
 * クリックでビューの表示・非表示を切り替える
 */
public class ToggleOnClickListener implements OnClickListener {

	final private static int sDefaultInvisibleMode = View.GONE;
	
	final private int mInvisibleMode;
	
	private Optional<View> mTargetView = Optional.absent();
	
	public ToggleOnClickListener() {
		this(sDefaultInvisibleMode, null);
	}
	
	public ToggleOnClickListener(View targetView) {
		this(sDefaultInvisibleMode, targetView);
	}
	
	public ToggleOnClickListener(int invisibleMode) {
		this(invisibleMode, null);
	}
	
	public ToggleOnClickListener(int invisibleMode, View targetView) {
		mInvisibleMode = invisibleMode;
		mTargetView = Optional.fromNullable(targetView);
	}
	
	@Override
	public void onClick(View view) {
		final View targetView = (mTargetView.isPresent()) ? mTargetView.get() : view;
		ToggleView.toggle(targetView, mInvisibleMode);
	}

}
