package net.ichigotake.yancha.common.ui;

import android.view.View;

/**
 * �r���[�̂��߂֗̕��N���X
 */
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
