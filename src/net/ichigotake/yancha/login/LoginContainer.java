package net.ichigotake.yancha.login;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.ui.ViewContainer;
import net.ichigotake.yancha.core.user.User;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

/**
 * ログイン画面を表示するビューコンテナ
 */
public class LoginContainer implements ViewContainer {

	final private FragmentActivity mActivity;
	
	final private User user;
	
	public LoginContainer(FragmentActivity activity) {
		this.mActivity = activity;
		this.user = new User(activity);
	}
	
	@Override
	public void initializeView(View view) {
		view.findViewById(R.id.loginAuthSimpleSend).setOnClickListener(
				new SimpleLoginOnClickListener(mActivity));
		
		EditText loginSimple = (EditText) view.findViewById(R.id.loginAuthSimpleNickname);
		loginSimple.setText(user.getNickname());
		loginSimple.setOnEditorActionListener(new SimpleLoginOnEditorActionListener(mActivity));
		
		view.findViewById(R.id.loginAuthTwitter).setOnClickListener(new TwitterLoginOnClickListener());
		
	}
}
