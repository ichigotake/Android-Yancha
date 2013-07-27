package net.ichigotake.yancha.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 自分自身の接続情報等を扱うデータクラス
 */
public class User {

	final SharedPreferences pref;

	public User(Activity activity) {
		this.pref = activity.getSharedPreferences("owner", Context.MODE_PRIVATE);
	}
	
	public String getToken() {
		return pref.getString("token", "");
	}
	
	public void setToken(String token) {
		Editor editor = pref.edit();
		editor.putString("token", token);
		editor.commit();
	}
	
	public String getNickname() {
		return pref.getString("nickname", "noname");
	}
	
	public void setNickname(String nickname) {
		Editor editor = pref.edit();
		editor.putString("nickname", nickname);
		editor.commit();
	}
	
}
