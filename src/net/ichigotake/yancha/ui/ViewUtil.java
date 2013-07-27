package net.ichigotake.yancha.ui;

import android.view.View;

public class ViewUtil {

	public void toggle(View view) {
		switch (view.getVisibility()) {
		case View.VISIBLE:
			view.setVisibility(View.GONE);
			break;
		case View.GONE:
			view.setVisibility(View.VISIBLE);
			break;
		}
	}
}
