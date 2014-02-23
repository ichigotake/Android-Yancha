package net.ichigotake.yancha.common.context;

import net.ichigotake.yancha.BuildConfig;
import net.ichigotake.yancha.R;

public class AppContext {

    final public static String URI_SCHEME_LOGIN_CALLBACK = "yancha://login/twitter/callback";
    
    final public static int FRAGMENT_ID_CONTENT = R.id.wrap_fragment;
    
    public static String getUserAgent() {
        return "yancha for Android / " + getVersionName();
    }
    
    /**
     * "x.x.x" のフォーマットでバージョン名を返す
     * @return
     */
    public static String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }
    
    /**
     * "v x.x.x" のフォーマットでバージョン名を返す
     * @return
     */
    public static String getFullVersionName() {
        return "v " + getVersionName();
    }
    
}
