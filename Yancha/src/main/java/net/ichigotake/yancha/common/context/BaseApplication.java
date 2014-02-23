package net.ichigotake.yancha.common.context;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import net.ichigotake.yancha.BuildConfig;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Crashlytics.start(this);
        }
        System.setProperty("http.agent", new AppContext(this).getUserAgent());
    }
}
