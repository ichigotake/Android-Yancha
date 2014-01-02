package net.ichigotake.yancha.common.context;

import android.app.Application;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        System.setProperty("http.agent", new AppContext(this).getUserAgent());
    }
}
