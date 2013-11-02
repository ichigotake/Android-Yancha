package net.ichigotake.yancha.chat;

import java.util.ArrayList;
import java.util.List;

import net.ichigotake.colorfulsweets.lib.ui.Display;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ListPopupWindow;

class JoinUsersPopupListener {

	final private ListPopupWindow mPopup;
	
	final private JoinUserListAdapter mAdapter;
	
	final private Activity mActivity;
	
	JoinUsersPopupListener(Activity activity, View anchor) {
		mActivity = activity;
		mAdapter = new JoinUserListAdapter(anchor.getContext(), new ArrayList<String>());
		mPopup = new ListPopupWindow(anchor.getContext());
		mPopup.setAnchorView(anchor);
		mPopup.setAdapter(mAdapter);
	}
	
	void setUsers(List<String> users) {
		mAdapter.clear();
		mAdapter.addAll(users);
		mAdapter.notifyDataSetChanged();
		mPopup.setWidth((int)Display.calcDensity(mActivity, 240));
	}
	
	void show() {
		mPopup.show();
	}
	
	void dismiss() {
		mPopup.dismiss();
	}

}