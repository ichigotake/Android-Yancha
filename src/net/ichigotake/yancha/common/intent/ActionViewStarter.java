package net.ichigotake.yancha.common.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * API level 1
 * 
 * Start intent with {@link Intent#ACTION_VIEW}.
 */
public class ActionViewStarter {

	final private Activity mActivity;
	
	/**
	 * API level 1
	 * 
	 * Constructor
	 * @param activity
	 */
	public ActionViewStarter(Activity activity) {
		mActivity = activity;
	}
	
	/**
	 * API level 1
	 * 
	 * Open URL with string.
	 * @param url
	 */
	public void start(String url) {
		start(Uri.parse(url));
	}
	
	/**
	 * API level 1
	 * 
	 * Open URL with {@link Uri}.
	 * @param url
	 */
	public void start(Uri uri) {
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		mActivity.startActivity(intent);
	}
}
