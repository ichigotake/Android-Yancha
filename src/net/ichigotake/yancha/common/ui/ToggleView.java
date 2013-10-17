package net.ichigotake.yancha.common.ui;

import android.view.View;

/**
 * ビューの表示・非表示を切り替える
 */
public class ToggleView {

	public static void toggle(View targetView) {
		toggle(targetView, View.GONE);
	}
	
	public static void toggle(View targetView, int invisibleMode) {
		switch (targetView.getVisibility()) {
		case View.VISIBLE:
			targetView.setVisibility(invisibleMode);
			break;
		case View.GONE:
		case View.INVISIBLE:
			targetView.setVisibility(View.VISIBLE);
			break;
		}
	}
}
