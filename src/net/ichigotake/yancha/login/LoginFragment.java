package net.ichigotake.yancha.login;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.chat.ChatFragment;
import net.ichigotake.yancha.net.YanchaApiLogin;
import net.ichigotake.yancha.ui.FragmentTransit;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;


public class LoginFragment extends Fragment {

	public LoginFragment () {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_login, container, false);
		Log.d("yancha-MainActivity", "onCreateView");

		view.findViewById(R.id.button_loginSimple).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				EditText nicknameTextView = (EditText) getActivity().findViewById(R.id.editText_loginNickname);
				String nickname = nicknameTextView.getText().toString();
				
				simpleLogin(nickname);

				new FragmentTransit(LoginFragment.this).toNext(ChatFragment.newInstance());

			}
		});
		
		EditText loginSimple = (EditText) view.findViewById(R.id.editText_loginNickname);
		loginSimple.setOnEditorActionListener(new OnEditorActionListener() {	
			
			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				EditText nicknameTextView = (EditText) getActivity().findViewById(R.id.editText_loginNickname);
				String nickname = nicknameTextView.getText().toString();
				
				simpleLogin(nickname);

				new FragmentTransit(LoginFragment.this).toNext(ChatFragment.newInstance());

				return false;
			}
		});

		view.findViewById(R.id.button_loginTwitter).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new FragmentTransit(LoginFragment.this).toNext(ChatFragment.newInstance());
			}
		});


		return view;
	}
	
	private void simpleLogin(String nickname) {

		SharedPreferences pref = getActivity().getSharedPreferences("owner", Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		String token = YanchaApiLogin.simpleLogin(nickname);

		editor.putString("token", token);
		
		editor.commit();
		
		pref.getString("token", "none");
		pref.getString("nickname", nickname);
		
		Log.d("yancha-MainFragment", "logined: " + nickname + " => " + token);
	}
	
}
