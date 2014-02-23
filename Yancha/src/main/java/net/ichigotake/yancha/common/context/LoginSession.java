package net.ichigotake.yancha.common.context;

import android.app.Activity;

import net.ichigotake.colorfulsweets.common.activity.ActivityTransit;
import net.ichigotake.yancha.MainActivity;
import net.ichigotake.yancha.common.user.AppUser;

/**
 * ログインを実行する
 */
public class LoginSession {

    final private Activity mActivity;

    public LoginSession(Activity activity) {
        mActivity = activity;
    }

    public void login() {
        new ActivityTransit(mActivity, MainActivity.class)
                .clearTop()
                .transition();
    }

    public void logout() {
        new AppUser(mActivity).resetToken();
        new ActivityTransit(mActivity, MainActivity.class)
                .clearTop()
                .transition();
    }
}
