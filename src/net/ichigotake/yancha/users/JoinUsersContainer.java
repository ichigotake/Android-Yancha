package net.ichigotake.yancha.users;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.ui.ToggleOnClickListener;
import net.ichigotake.yancha.common.ui.ViewContainer;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * �Q�����[�U�[�ꗗ��\������r���[�R���e�i
 */
public class JoinUsersContainer implements ViewContainer {

	private RelativeLayout viewJoinUsers;
	
	@Override
	public void initializeView(View view) {
		viewJoinUsers = (RelativeLayout) view.findViewById(R.id.joinUsers);
		
		view.findViewById(R.id.chatJoinUsersIcon)
			.setOnClickListener(new ToggleOnClickListener(viewJoinUsers));
	}

}
