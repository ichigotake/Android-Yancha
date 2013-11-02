package net.ichigotake.yancha.common.user;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.api.ApiUri;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * チャットにログインする自分自身
 */
public class User {

	final private String KEY_CONNECT_SERVER_AUTHORITY = "connect_server_authority";
	
	final private String KEY_TOKEN = "token";
	
	final private String KEY_NICKNAME = "nickname";
	
	final private SharedPreferences pref;
	
	final private Context mContext;
	
	public User(Context context) {
		this.pref = context.getSharedPreferences("owner", Context.MODE_PRIVATE);
		mContext = context;
	}
	
	public String getToken() {
		return pref.getString(KEY_TOKEN, "");
	}
	
	public void setToken(String token) {
		Editor editor = pref.edit();
		editor.putString(KEY_TOKEN, token);
		editor.commit();
	}
	
	public String getNickname() {
		return pref.getString(KEY_NICKNAME, "noname");
	}
	
	public void setNickname(String nickname) {
		Editor editor = pref.edit();
		editor.putString(KEY_NICKNAME, nickname);
		editor.commit();
	}
	
	public ApiUri getApiUri() {
		return new ApiUri(getConnectServerAuthority());
	}
	
	public String getConnectServerAuthority() {
		return pref.getString(
				KEY_CONNECT_SERVER_AUTHORITY,
				mContext.getString(R.string.yc_login_server_default));
	}
	
	public void setConnectServerAuthority(String authority) {
		Editor editor = pref.edit();
		editor.putString(KEY_CONNECT_SERVER_AUTHORITY, authority);
		editor.commit();
	}
	
}
