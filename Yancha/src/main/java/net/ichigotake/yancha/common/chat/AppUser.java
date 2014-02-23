package net.ichigotake.yancha.common.chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import net.ichigotake.yancha.BaseActivity;
import net.ichigotake.yancha.common.api.ChatServer;
import net.ichigotake.yancha.common.api.rest.ApiUri;
import net.ichigotake.yancha.sdk.model.ChatUser;

/**
 * チャットにログインする自分自身
 */
public class AppUser implements ChatUser {



    final private String KEY_CONNECT_SERVER_AUTHORITY = "connect_server_authority";
    final private String KEY_TOKEN = "token";
    final private String KEY_NICKNAME = "nickname";
    final private String KEY_PROFILE_URL = "profile_url";
    final private String KEY_PROFILE_IMAGE_URL = "profile_image_url";
    final private SharedPreferences pref;

    public AppUser() {
        this.pref = BaseActivity.getContext().getSharedPreferences("myself", Context.MODE_PRIVATE);
    }

    public void resetToken() {
        setToken("");
    }

    public boolean hasToken() {
        return ! "".equals(getToken());
    }

    public String getToken() {
        return pref.getString(KEY_TOKEN, "");
    }
    
    public void setToken(String token) {
        Editor editor = pref.edit();
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    @Override
    public String getProfileUrl() {
        return pref.getString(KEY_PROFILE_URL, "");
    }

    @Override
    public String getProfileImageUrl() {
        return pref.getString(KEY_PROFILE_IMAGE_URL, "");
    }

    @Override
    public String getNickname() {
        return pref.getString(KEY_NICKNAME, "noname");
    }
    
    public void setNickname(String nickname) {
        Editor editor = pref.edit();
        editor.putString(KEY_NICKNAME, nickname);
        editor.commit();
    }

    public boolean isMyself(ChatUser user) {
        return getNickname().equals(user.getNickname());
    }

    public void update(ChatUser user) {
        Editor editor = pref.edit();
        editor.putString(KEY_NICKNAME, user.getNickname());
        editor.putString(KEY_PROFILE_URL, user.getProfileUrl());
        editor.putString(KEY_PROFILE_IMAGE_URL, user.getProfileImageUrl());
        editor.commit();
    }
    
    public ApiUri getApiUri() {
        return new ApiUri(getConnectServerAuthority());
    }
    
    public String getConnectServerAuthority() {
        return pref.getString(
                KEY_CONNECT_SERVER_AUTHORITY,
                ChatServer.getDefaultServerHost());
    }
    
    public void setConnectServer(String authority) {
        Editor editor = pref.edit();
        editor.putString(KEY_CONNECT_SERVER_AUTHORITY, authority);
        editor.commit();
    }
    
}
