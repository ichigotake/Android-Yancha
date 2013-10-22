package net.ichigotake.yancha.common.intent;

import android.content.Intent;

/**
 * API level 1
 * 
 * 
 */
public class UriScheme {

	public static boolean isCallback(Intent intent) {
		final boolean isCallback;
		if (intent != null) {
			isCallback = (Intent.ACTION_VIEW.equals(intent.getAction()));
		} else {
			isCallback = false;
		}
		return isCallback;
	}
}
