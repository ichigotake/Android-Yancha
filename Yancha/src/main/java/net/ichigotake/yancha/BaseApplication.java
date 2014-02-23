package net.ichigotake.yancha;

import android.app.Application;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReporter.start(this);
        System.setProperty("http.agent", YanchaApp.getUserAgent());
    }


}
