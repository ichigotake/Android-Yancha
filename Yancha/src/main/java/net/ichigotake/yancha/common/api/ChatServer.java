package net.ichigotake.yancha.common.api;

import net.ichigotake.yancha.BaseActivity;
import net.ichigotake.yancha.R;

public class ChatServer {

    private static String sServerHost;

    public static String getDefaultServerHost() {
        if (sServerHost == null) {
            sServerHost = BaseActivity.getContext().getString(R.string.yc_login_server_default);
        }
        return sServerHost;
    }

}
