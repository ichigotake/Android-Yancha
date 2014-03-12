package net.ichigotake.yancha.app;

import android.os.Bundle;

import net.ichigotake.colorfulsweets.common.activity.ActivityTransit;
import net.ichigotake.yancha.BaseActivity;
import net.ichigotake.yancha.MainActivity;

public class TopActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ActivityTransit(this, MainActivity.class).transition();
    }

}
