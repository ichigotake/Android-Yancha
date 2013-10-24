package net.ichigotake.yancha;

import net.ichigotake.colorfulsweets.lib.fragment.FragmentTransit;
import net.ichigotake.colorfulsweets.lib.intent.UriScheme;
import net.ichigotake.yancha.common.context.AppContext;
import net.ichigotake.yancha.common.context.BaseFragmentActivity;
import net.ichigotake.yancha.common.ui.ActivityTransit;
import net.ichigotake.yancha.core.user.User;
import net.ichigotake.yancha.login.LoginFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class LoginActivity extends BaseFragmentActivity {
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		if (UriScheme.isCallback(intent)) {
			Uri uri= intent.getData();
			User user = new User(this);
			user.setToken(uri.getQueryParameter("token"));
			//TODO 不正なパラメータの場合の処理
			new ActivityTransit(this)
				.clearTop()
				.toNext(ChatActivity.class);
		} else {
			new FragmentTransit(this)
				.setAddBackStack(false)
				.toReplace(AppContext.FRAGMENT_ID_CONTENT, LoginFragment.newInstance());
		}
	}
	
}
