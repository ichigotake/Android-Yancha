package net.ichigotake.yancha.login;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

class SimpleLoginOnClickListener implements OnClickListener {

	final private SendSimpleLogin mLogin;
	
	SimpleLoginOnClickListener(FragmentActivity activity) {
		mLogin = new SendSimpleLogin(activity);
	}
	
	@Override
	public void onClick(View arg0) {
		mLogin.send();
	}
}
