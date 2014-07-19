package net.ichigotake.android.yancha.app;

import android.text.TextUtils;

public class ChatServer {

    public static String getServerHost() {
        return TextUtils.equals(BuildConfig.BUILD_TYPE, "release")
                ? "http://yancha.hachiojipm.org" : "http://192.168.11.2:3000";
    }
}
