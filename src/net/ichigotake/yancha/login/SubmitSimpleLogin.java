package net.ichigotake.yancha.login;

import net.ichigotake.yancha.ChatActivity;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.ui.ActivityTransit;
import net.ichigotake.yancha.common.ui.dialog.LoadingProgressDialog;
import net.ichigotake.yancha.common.ui.dialog.LoadingProgressDialogListener;
import net.ichigotake.yancha.core.api.ApiEventListener;
import net.ichigotake.yancha.core.api.ApiResponse;
import net.ichigotake.yancha.core.api.ApiUri;
import net.ichigotake.yancha.core.api.YanchaApiLogin;
import net.ichigotake.yancha.core.user.User;

import org.apache.http.ParseException;

import android.support.v4.app.FragmentActivity;
import android.widget.EditText;

import com.google.common.base.Optional;
import com.google.common.eventbus.Subscribe;

/**
 * シンプルログインを実行
 */
class SubmitSimpleLogin {

	final private FragmentActivity mActivity;
	
	final private User mUser;
	
	SubmitSimpleLogin(FragmentActivity activity) {
		mActivity = activity;
		mUser = new User(activity);
	}
	
	void send() {
		EditText nicknameTextView = (EditText) mActivity.findViewById(R.id.loginAuthSimpleNickname);
		String nickname = nicknameTextView.getText().toString();
		
		mUser.setNickname(nickname);
		
		//TODO ログイン失敗時の分岐
		YanchaApiLogin loginApi = new YanchaApiLogin(new ApiUri(), nickname);
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
					//TODO エラー処理
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
}
