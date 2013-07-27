package net.ichigotake.yancha.net;

import net.ichigotake.yancha.data.User;
import android.app.Activity;


/**
 * yancha�T�[�o�[�֔F�؂���N���X
 */
public class YanchaAuth {

	private User user;
	
	public YanchaAuth(Activity activity) {
		user = new User(activity);
	}
	
	public boolean simpleLogin(String nickname) {

		String token = YanchaApiLogin.simpleLogin(nickname);
		
		user.setToken(token);
		user.setNickname(nickname);
		
		return !token.isEmpty();
	}
	

}
