package net.ichigotake.yancha.common.context;

import android.app.Activity;

import net.ichigotake.colorfulsweets.lib.context.ActivityTransit;
import net.ichigotake.yancha.ChatActivity;
import net.ichigotake.yancha.LoginActivity;
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
        new ActivityTransit(mActivity)
                .clearTop()
                .toNext(ChatActivity.class);
    }

    public void logout() {
        new AppUser(mActivity).resetToken();
        new ActivityTransit(mActivity)
                .clearTop()
                .toNext(LoginActivity.class);
    }
}
