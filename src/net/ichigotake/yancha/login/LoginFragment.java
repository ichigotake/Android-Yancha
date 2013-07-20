package net.ichigotake.yancha.login;

import net.ichigotake.yancha.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LoginFragment extends Fragment {

	public LoginFragment () {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_login, container, false);
		
		LoginContainer loginContainer = new LoginContainer(this);
		loginContainer.initializeView(view);
		
		return view;
	}
	
}