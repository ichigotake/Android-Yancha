package net.ichigotake.yancha.login;

import net.ichigotake.yancha.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * ÉçÉOÉCÉìâÊñ 
 */
public class LoginFragment extends Fragment {

	public static LoginFragment newInstance() {
		return new LoginFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.yc_login, container, false);
		
		LoginContainer.initialize(getActivity(), view);
		
		return view;
	}
	
}
