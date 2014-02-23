package net.ichigotake.yancha;

import android.app.Application;

public class BaseApplication extends Application {

    final public static String LOG_TAG = "Yancha";

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG && CrashReporter.hasReportKey(this)) {
            CrashReporter.start(this);
        }
        System.setProperty("http.agent", AppContext.getUserAgent());
    }


}
