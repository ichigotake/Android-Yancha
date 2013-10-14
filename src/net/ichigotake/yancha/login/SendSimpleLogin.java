package net.ichigotake.yancha.login;

import net.ichigotake.yancha.ChatActivity;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.ui.ActivityTransit;
import net.ichigotake.yancha.core.api.YanchaAuth;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;

class SendSimpleLogin {

	final private FragmentActivity mActivity;
	
	SendSimpleLogin(FragmentActivity activity) {
		mActivity = activity;
	}
	
	void send() {
		EditText nicknameTextView = (EditText) mActivity.findViewById(R.id.loginAuthSimpleNickname);
		String nickname = nicknameTextView.getText().toString();
		
		//TODO ÉçÉOÉCÉìé∏îséûÇÃï™äÚ
		new YanchaAuth(mActivity).simpleLogin(nickname);
		
		new ActivityTransit(mActivity).toNext(ChatActivity.class);
	}
}
