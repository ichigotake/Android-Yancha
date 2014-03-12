package net.ichigotake.yancha.login;

import android.app.Activity;

import net.ichigotake.colorfulsweets.common.intent.ActionViewStarter;
import net.ichigotake.yancha.R;

public class TwitterLogin {

    final private Activity mActivity;
    
    public TwitterLogin(Activity activity) {
        mActivity = activity;
    }
    
    public void start(String host) {
        final String scheme = mActivity.getString(
                R.string.yc_login_server_callback, mActivity.getString(R.string.yc_app_name));
        new ActionViewStarter(mActivity)
                .start(mActivity.getString(R.string.yc_login_login_twitter_start, host, scheme));
    }
    
}
