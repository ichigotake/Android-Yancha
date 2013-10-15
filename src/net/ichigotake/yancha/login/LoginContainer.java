package net.ichigotake.yancha.login;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.context.AppContext;
import net.ichigotake.yancha.common.ui.ViewContainer;
import net.ichigotake.yancha.core.user.User;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * ログイン画面を表示するビューコンテナ
 */
class LoginContainer implements ViewContainer {

	final private FragmentActivity mActivity;
	
	final private User user;
	
	private LoginContainer(FragmentActivity activity) {
		this.mActivity = activity;
		this.user = new User(activity);
	}
	
	static LoginContainer initialize(FragmentActivity activity, View view) {
		LoginContainer container = new LoginContainer(activity);
		container.initializeView(view);
		return container;
	}
	
	@Override
	public void initializeView(View view) {
		view.findViewById(R.id.loginAuthSimpleSend).setOnClickListener(
				new SimpleLoginOnClickListener(mActivity));
		
		EditText loginSimple = (EditText) view.findViewById(R.id.loginAuthSimpleNickname);
		loginSimple.setText(user.getNickname());
		loginSimple.setSelection(user.getNickname().length());
		loginSimple.setOnEditorActionListener(new SimpleLoginOnEditorActionListener(mActivity));
		
		view.findViewById(R.id.loginAuthTwitter).setOnClickListener(new TwitterLoginOnClickListener());
		
		TextView versionView = (TextView) view.findViewById(R.id.loginVersionName);
		String versionName = new AppContext(mActivity).getFullVersionName();
		versionView.setText(versionName);
	}
}
