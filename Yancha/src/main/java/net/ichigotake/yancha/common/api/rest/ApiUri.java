package net.ichigotake.yancha.common.api.rest;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * APIのURI
 */
public class ApiUri implements Serializable {

    final private String mHostname;
    
    public ApiUri(String hostname) {
        mHostname = hostname;
    }
    
    public String getScheme() {
        return "http";
    }
    
    public String getAuthority() {
        return mHostname;
    }
    
    public String getHostname() {
        return mHostname;
    }
    
    public boolean isHostnameEmpty() {
        return TextUtils.isEmpty(mHostname);
    }
    
    public String getAbsoluteUrl() {
        return String.format("%s://%s", getScheme(), getAuthority());
    }
    
    public String getSimpleLoginPath() {
        return "/login";
    }
    
    public String getTwitterLoginPath() {
        return "/login/twitter/start";
    }

}