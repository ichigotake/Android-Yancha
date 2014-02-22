package net.ichigotake.yancha;

import android.os.Bundle;

import net.ichigotake.colorfulsweets.common.activity.ActivityTransit;
import net.ichigotake.yancha.common.context.BaseActivity;
import net.ichigotake.yancha.common.user.AppUser;

public class SplashActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppUser appUser = new AppUser(this);
        if (appUser.hasToken()) {
            new ActivityTransit(this, ChatActivity.class)
                    .clearTop()
                    .setWithFinish()
                    .transition();
        } else {
            new ActivityTransit(this, LoginActivity.class)
                    .clearTop()
                    .setWithFinish()
                    .transition();
        }
    }
}
