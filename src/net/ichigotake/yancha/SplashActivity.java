package net.ichigotake.yancha;

import android.os.Bundle;

import net.ichigotake.colorfulsweets.lib.context.ActivityTransit;
import net.ichigotake.yancha.common.context.BaseActivity;
import net.ichigotake.yancha.common.user.AppUser;

/**
 * Created by ichigotake on 2013/11/02.
 */
public class SplashActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppUser appUser = new AppUser(this);
        if (appUser.hasToken()) {
            new ActivityTransit(this)
                    .clearTop()
                    .toNext(ChatActivity.class);
        } else {
            new ActivityTransit(this)
                    .clearTop()
                    .toNext(LoginActivity.class);
        }
    }
}
