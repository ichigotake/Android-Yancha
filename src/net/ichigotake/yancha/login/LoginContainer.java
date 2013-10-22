package net.ichigotake.yancha.login;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.context.AppContext;
import net.ichigotake.yancha.common.ui.ViewContainer;
import net.ichigotake.yancha.core.api.ApiUri;
import net.ichigotake.yancha.core.user.User;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
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
		LoginViewHolder holder = new LoginViewHolder(view);
		
		EditText serverHost = holder.getLoginServer();
		ApiUri uri = user.getApiUri();
		if (uri.isHostnameEmpty()) {
			serverHost.setText(R.string.yc_login_server_default);
		} else {
			serverHost.setText(uri.getHostname());
		}
		
		holder.getLoginSimpleSubmit().setOnClickListener(
				new SimpleLoginOnClickListener(mActivity, holder));
		
		EditText loginSimple = holder.getLoginSimple();
		loginSimple.setText(user.getNickname());
		loginSimple.setSelection(user.getNickname().length());
		loginSimple.setOnEditorActionListener(
				new SimpleLoginOnEditorActionListener(mActivity, holder));
		
		Button loginTwitter = holder.getLoginTwitter();
		loginTwitter.setOnClickListener(new TwitterLoginOnClickListener(mActivity));
		
		TextView versionView = holder.getVersionName();
		String versionName = new AppContext(mActivity).getFullVersionName();
		versionView.setText(versionName);
	}
}
