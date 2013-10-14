package net.ichigotake.yancha.login;

import net.ichigotake.yancha.ChatActivity;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.ui.ActivityTransit;
import net.ichigotake.yancha.common.ui.ViewContainer;
import net.ichigotake.yancha.core.api.YanchaAuth;
import net.ichigotake.yancha.core.user.User;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * ログイン画面を表示するビューコンテナ
 */
public class LoginContainer implements ViewContainer {

	final private FragmentActivity mActivity;
	
	final private YanchaAuth auth;
	
	final private User user;
	
	public LoginContainer(FragmentActivity activity) {
		this.mActivity = activity;
		this.auth = new YanchaAuth(activity);
		this.user = new User(activity);
	}
	
	@Override
	public void initializeView(View view) {
		view.findViewById(R.id.loginAuthSimpleSend).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				EditText nicknameTextView = (EditText) mActivity.findViewById(R.id.loginAuthSimpleNickname);
				String nickname = nicknameTextView.getText().toString();
				
				//TODO ログイン失敗時の分岐
				auth.simpleLogin(nickname);
				
				toChat();
				
			}
		});
		
		EditText loginSimple = (EditText) view.findViewById(R.id.loginAuthSimpleNickname);
		loginSimple.setText(user.getNickname());
		loginSimple.setOnEditorActionListener(new OnEditorActionListener() {	
			
			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				EditText nicknameTextView = (EditText) mActivity.findViewById(R.id.loginAuthSimpleNickname);
				String nickname = nicknameTextView.getText().toString();
				
				//TODO ログイン失敗時の分岐
				auth.simpleLogin(nickname);
				
				toChat();
				
				return false;
			}
		});

		view.findViewById(R.id.loginAuthTwitter).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toChat();
			}
		});
		
	}
	
	private void toChat() {
		new ActivityTransit(mActivity).toNext(ChatActivity.class);
	}

}
