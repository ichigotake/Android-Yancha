package net.ichigotake.yancha.login;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.intent.ActionViewStarter;
import android.app.Activity;

public class TwitterLogin {

	final private Activity mActivity;
	
	public TwitterLogin(Activity activity) {
		mActivity = activity;
	}
	
	public void start(String host) {
		new ActionViewStarter(mActivity)
			.start(mActivity.getString(R.string.yc_login_login_twitter_start, host));
	}
	
}
