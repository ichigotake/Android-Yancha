package net.ichigotake.yancha;

import com.android.volley.toolbox.ImageLoader;

import net.ichigotake.yancha.common.api.LruImageCache;
import net.ichigotake.yancha.common.api.RequestManager;

import java.util.Locale;

public class YanchaApp {

    final public static int FRAGMENT_ID_CONTENT = R.id.wrap_fragment;

    final public static String LOG_TAG = "Yancha";
    final public static java.util.Locale LOCALE = Locale.JAPAN;

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

    public static ImageLoader getImageLoader() {
        return new ImageLoader(RequestManager.get(), new LruImageCache());
    }
}
