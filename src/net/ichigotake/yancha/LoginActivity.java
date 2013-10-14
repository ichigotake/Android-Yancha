package net.ichigotake.yancha;

import net.ichigotake.yancha.common.context.BaseFragmentActivity;
import net.ichigotake.yancha.common.ui.FragmentTransit;
import net.ichigotake.yancha.login.LoginFragment;
import android.os.Bundle;

public class LoginActivity extends BaseFragmentActivity {
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		new FragmentTransit(this)
			.setIsAddBackStack(false)
			.toNext(LoginFragment.newInstance());
	}
	
}
