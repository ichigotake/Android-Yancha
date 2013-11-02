package net.ichigotake.yancha.chat;

import net.ichigotake.yanchasdk.lib.model.JoinTagList;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;

class SelectedTagOnClickListener implements OnClickListener {

	final private JoinTagList mTags;
	
	private PopupMenu mPopup;
	
	public SelectedTagOnClickListener(JoinTagList tags) {
		mTags = tags;
	}
	
	@Override
	public void onClick(View view) {
		mPopup = new PopupMenu(view.getContext(), view);
		mPopup.setOnMenuItemClickListener(new PopupOnClickListener());
		for (String tag : mTags.getAll().keySet()) {
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