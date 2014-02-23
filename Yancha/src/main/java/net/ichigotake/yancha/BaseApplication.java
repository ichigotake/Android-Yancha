package net.ichigotake.yancha;

import android.app.Application;

import net.ichigotake.yancha.common.context.AppContext;

public class BaseApplication extends Application {

    final public static String LOG_TAG = "Yancha";

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG && CrashReporter.hasReportKey(this)) {
            CrashReporter.start(this);
        }
        System.setProperty("http.agent", new AppContext(this).getUserAgent());
    }


}
