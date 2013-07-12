package net.ichigotake.yancha.login;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.net.ApiLogin;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class LoginFragment extends Fragment implements OnClickListener {

	public LoginFragment() {
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_main, container, false);
		
		return view;
	}
	
	@Override
	public void onClick(View view) {
		Log.d("MainActivity", "" + view.getId());
		switch (view.getId()) {
			case R.id.action_about:
				LoginFragment.this.getActivity().setContentView(R.layout.activity_about);
				break;
			
			case R.id.action_hints:
				LoginFragment.this.getActivity().setContentView(R.layout.activity_hints);
				break;
			
			case R.id.action_logout:
				LoginFragment.this.getActivity().setContentView(R.layout.activity_login);
				break;
			
			case R.id.button_loginTwitter:
			case R.id.button_loginSimple:
				LoginFragment.this.getActivity().setContentView(R.layout.activity_chat_main);
				break;
		}
	}

}
