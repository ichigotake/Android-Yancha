package net.ichigotake.yancha.common.ui;

import android.app.Activity;
import android.content.Intent;

/**
 * �A�N�e�B�r�e�B�̑J�ڂ�����
 */
public class ActivityTransit {

	final private Activity mCurrentActivity;
	
	public ActivityTransit(Activity activity) {
		mCurrentActivity = activity;
	}
	
	public void toNext(Class<? extends Activity> nextActivity) {
		Intent intent = new Intent(mCurrentActivity, nextActivity);
		mCurrentActivity.startActivity(intent);
	}
	
}
