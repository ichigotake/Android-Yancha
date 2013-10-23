package net.ichigotake.yancha.chat;

import java.util.Map;

import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;

class SelectedTagOnClickListener implements OnClickListener {

	final private Map<String, Integer> mTags;
	
	private PopupMenu mPopup;
	
	public SelectedTagOnClickListener(Map<String, Integer> tags) {
		mTags = tags;
	}
	
	@Override
	public void onClick(View view) {
		mPopup = new PopupMenu(view.getContext(), view);
		mPopup.setOnMenuItemClickListener(new PopupOnClickListener());
		for (String tag : mTags.keySet()) {
			mPopup.getMenu().add(tag);
		}
		mPopup.show();
	}
	
	private class PopupOnClickListener implements OnMenuItemClickListener {

		@Override
		public boolean onMenuItemClick(MenuItem item) {
			mPopup.dismiss();
			return false;
		}
		
	}
	
}
