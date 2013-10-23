package net.ichigotake.yancha.chat;

import java.util.List;

import net.ichigotake.yancha.R;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 参加ユーザー一覧を表示するビューコンテナ
 */
public class JoinUsersContainer {

	final private JoinUsersPopupListener mPopup;
	
	final private View mUsersIcon;
	
	public JoinUsersContainer(Activity activity, View view) {
		mUsersIcon = view.findViewById(R.id.chatJoinUsersIcon);
		mUsersIcon.setOnClickListener(new IconClickListener());
		mPopup = new JoinUsersPopupListener(activity, mUsersIcon);
	}
	
	public void setUsers(List<String> users) {
		mPopup.setUsers(users);
	}
	
	private class IconClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			mPopup.show();
		}
		
	}
}
