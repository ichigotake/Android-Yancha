package net.ichigotake.yancha.common.context;

import android.app.Activity;

import net.ichigotake.colorfulsweets.lib.context.ActivityTransit;
import net.ichigotake.yancha.ChatActivity;

/**
 * ログインを実行する
 */
public class LoginExecuter {

    final private Activity mActivity;

    public LoginExecuter(Activity activity) {
        mActivity = activity;
    }

    public void login() {
        new ActivityTransit(mActivity)
                .clearTop()
                .toNext(ChatActivity.class);
    }
}
