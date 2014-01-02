package net.ichigotake.yancha.common.context;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import net.ichigotake.yancha.R;

public class AppContext {

    final public static String URI_SCHEME_LOGIN_CALLBACK = "yancha://login/twitter/callback";
    
    final public static int FRAGMENT_ID_CONTENT = R.id.wrap_fragment;
    
    final private Context mContext;
    
    public AppContext(Context context) {
        mContext = context;
    }

    public String getUserAgent() {
        return new StringBuilder()
                .append("yancha for Android")
                .append(" / ")
                .append(getVersionName())
                .toString();
    }
    
    /**
     * "x.x.x" のフォーマットでバージョン名を返す
     * @return
     */
    public String getVersionName() {
        String versionName;
        try {
            PackageInfo info = mContext.getPackageManager()
                    .getPackageInfo(mContext.getPackageName(), PackageManager.GET_META_DATA);
            versionName = info.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            versionName = "";
        }
        return versionName;
    }
    
    /**
     * "v x.x.x" のフォーマットでバージョン名を返す
     * @return
     */
    public String getFullVersionName() {
        String versionName = getVersionName();
        
        final String fullName;
        if (! "".equals(versionName)) {
            fullName = "v " + versionName;
        } else {
            fullName = "";
        }
        
        return fullName;
    }
    
}
