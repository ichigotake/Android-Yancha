package net.ichigotake.yancha.app;

import android.app.Application;

import net.ichigotake.yancha.YanchaApp;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReporter.start(this);
        System.setProperty("http.agent", YanchaApp.getUserAgent());
    }


}
