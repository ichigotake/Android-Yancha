package net.ichigotake.yancha.common.ui;

import android.app.Activity;
import android.util.DisplayMetrics;

public class Display {

	public static float calcDensity(Activity activity, int pixel) {
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return pixel * metrics.density;
	}
}
