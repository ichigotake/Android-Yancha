package net.ichigotake.yancha.login;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.ui.actionbar.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * ���O�C�����
 */
public class LoginFragment extends Fragment {

	public LoginFragment () {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.yc_login, container, false);
		
		LoginContainer loginContainer = new LoginContainer(this);
		loginContainer.initializeView(view);
		
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		new ActionBar(getActivity())
			.setupWithSetDisplayHomeAsUpDisabled();
	}
}
