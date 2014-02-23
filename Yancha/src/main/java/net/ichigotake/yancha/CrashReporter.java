package net.ichigotake.yancha;

import android.content.Context;

import com.crittercism.app.Crittercism;
import com.crittercism.app.CrittercismConfig;

import net.ichigotake.yancha.common.chat.AppUser;

class CrashReporter {

    public static void start(Context context) {
        final CrittercismConfig config = new CrittercismConfig();
        config.setCustomVersionName(BuildConfig.VERSION_NAME);
        config.setDelaySendingAppLoad(true);

        final String nickname = new AppUser(context).getNickname();
        final boolean hasName = nickname.trim().length() > 0;
        Crittercism.setUsername(hasName ? nickname : "*nanashi*");
        Crittercism.initialize(context, BuildConfig.CRASH_REPORTER_KEY, config);
    }

}
