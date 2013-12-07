package net.ichigotake.yancha.common.api.rest;

import net.ichigotake.colorfulsweets.lib.net.UriBuilder;
import net.ichigotake.colorfulsweets.lib.net.http.AsyncHttpAccessor;
import net.ichigotake.colorfulsweets.lib.net.http.HttpAccessRequest;
import net.ichigotake.yancha.common.user.AppUser;

import org.apache.http.client.methods.HttpRequestBase;

/**
 * yanchaのログインAPIアクセッサー
 */
public class YanchaApiLogin extends AsyncHttpAccessor {

    final private UriBuilder mBuilder = new UriBuilder();
    
    final private AppUser mAppUser;
    
    public YanchaApiLogin(AppUser appUser) {
        mAppUser = appUser;
    }
    
    @Override
    public HttpRequestBase createRequest() {
        ApiUri uri = mAppUser.getApiUri();
        mBuilder.setPath(uri.getSimpleLoginPath())
            .setAuthrity(mAppUser.getConnectServerAuthority())
            .setScheme(uri.getScheme())
            .appendQueryParameter(YanchaApiField.NICK, mAppUser.getNickname())
            .appendQueryParameter(YanchaApiField.TOKEN_ONLY, "1")
            ;
        return new HttpAccessRequest().createGetRequest(mBuilder.build());
    }

}
