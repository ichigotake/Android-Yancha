package net.ichigotake.yancha.login;

import net.ichigotake.yancha.common.user.User;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

class TwitterLoginOnClickListener implements OnClickListener {

	final private Activity mActivity;
	
	final private User mUser;
	
	TwitterLoginOnClickListener(FragmentActivity activity) {
		mActivity = activity;
		mUser = new User(activity);
	}
	
	@Override
	public void onClick(View v) {
		new TwitterLogin(mActivity).start(mUser.getConnectServerAuthority());
	}

}
