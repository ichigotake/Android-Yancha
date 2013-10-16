package net.ichigotake.yancha.login;

import net.ichigotake.yancha.ChatActivity;
import net.ichigotake.yancha.common.ui.ActivityTransit;
import net.ichigotake.yancha.common.ui.dialog.LoadingProgressDialogListener;
import net.ichigotake.yancha.core.api.ApiEventListener;
import net.ichigotake.yancha.core.api.ApiResponse;
import net.ichigotake.yancha.core.api.YanchaApiLogin;
import net.ichigotake.yancha.core.user.User;

import org.apache.http.ParseException;

import android.support.v4.app.FragmentActivity;

import com.google.common.base.Optional;
import com.google.common.eventbus.Subscribe;

/**
 * �V���v�����O�C�������s
 */
class SubmitSimpleLogin {

	final private FragmentActivity mActivity;
	
	final private LoginViewHolder mHolder;
	final private User mUser;
	
	SubmitSimpleLogin(FragmentActivity activity, LoginViewHolder holder) {
		mActivity = activity;
		mHolder = holder;
		mUser = new User(activity);
	}
	
	void send() {
		String nickname = mHolder.getLoginSimple().getText().toString();
		String authority = mHolder.getLoginServer().getText().toString();
		
		mUser.setNickname(nickname);
		mUser.setConnectServerAuthority(authority);
		
		//TODO ���O�C�����s���̕���
		YanchaApiLogin loginApi = new YanchaApiLogin(mUser);
		loginApi.registerListener(new LoadingProgressDialogListener(mActivity));
		loginApi.registerListener(new SimpleaApiEventListener());
		loginApi.start();
	}
	
	private class SimpleaApiEventListener implements ApiEventListener {
		
		@Subscribe
		public void onSuccess(ApiResponse response) {
			try {
				Optional<String> content = response.getContent();
				if (content.isPresent()) {
					mUser.setToken(content.get());
					new ActivityTransit(mActivity).toNext(ChatActivity.class);
				} else {
					//TODO �G���[����
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
}
