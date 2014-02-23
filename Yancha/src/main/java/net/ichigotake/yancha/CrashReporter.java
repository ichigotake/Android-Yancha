package net.ichigotake.yancha;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.crashlytics.android.Crashlytics;

class CrashReporter {

    static void start(Context context) {
        Crashlytics.start(context);
    }

    static boolean hasReportKey(Context context) {
        boolean hasValidKey = false;
        try {
            Context appContext = context.getApplicationContext();
            ApplicationInfo ai = appContext.getPackageManager().getApplicationInfo(
                    appContext.getPackageName(),
                    PackageManager.GET_META_DATA
            );
            Bundle bundle = ai.metaData;
            if (bundle != null) {
                String apiKey = bundle.getString("com.crashlytics.ApiKey");
                hasValidKey = apiKey != null && !apiKey.equals("0000000000000000000000000000000000000000");
            }
        } catch (Exception e) {
            Log.e(BaseApplication.LOG_TAG, "Unexpected crash report key not found.", e);
        }
        return hasValidKey;
    }
}
