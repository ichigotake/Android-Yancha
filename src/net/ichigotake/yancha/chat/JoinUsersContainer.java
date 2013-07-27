package net.ichigotake.yancha.chat;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.ui.ViewContainer;
import net.ichigotake.yancha.ui.ViewUtil;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class JoinUsersContainer implements ViewContainer {

	private RelativeLayout viewJoinUsers;
	
	final private ViewUtil viewUtil = new ViewUtil();
	
	@Override
	public void initializeView(View view) {
		viewJoinUsers = (RelativeLayout) view.findViewById(R.id.joinUsers);
		
		view.findViewById(R.id.chatJoinUsersIcon)
		.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				viewUtil.toggle(viewJoinUsers);
			}
		});
	}

}
