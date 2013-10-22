package net.ichigotake.yancha.common.ui;

import android.app.Activity;
import android.content.Intent;

/**
 * アクティビティの遷移をする
 */
public class ActivityTransit {

	final private Activity mCurrentActivity;
	
	private boolean mClearTop;
	
	public ActivityTransit(Activity activity) {
		mCurrentActivity = activity;
	}
	
	public ActivityTransit clearTop() {
		mClearTop = true;
		return this;
	}

	
	public void toNext(Class<? extends Activity> nextActivity) {
		Intent intent = new Intent(mCurrentActivity, nextActivity);
		if (mClearTop) {
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		}
		mCurrentActivity.startActivity(intent);
	}
	
}
