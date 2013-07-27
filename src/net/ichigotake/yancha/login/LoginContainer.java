package net.ichigotake.yancha.login;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.chat.ChatFragment;
import net.ichigotake.yancha.data.User;
import net.ichigotake.yancha.net.YanchaAuth;
import net.ichigotake.yancha.ui.FragmentTransit;
import net.ichigotake.yancha.ui.ViewContainer;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * ログイン画面を表示するビューコンテナ
 */
public class LoginContainer implements ViewContainer {

	final private Fragment fragment;
	
	final private YanchaAuth auth;
	
	final private User user;
	
	public LoginContainer(Fragment fragment) {
		this.fragment = fragment;
		this.auth = new YanchaAuth(fragment.getActivity());
		this.user = new User(fragment.getActivity());
	}
	
	@Override
	public void initializeView(View view) {
		view.findViewById(R.id.loginAuthSimpleSend).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				EditText nicknameTextView = (EditText) fragment.getActivity().findViewById(R.id.loginAuthSimpleNickname);
				String nickname = nicknameTextView.getText().toString();
				
				//TODO ログイン失敗時の分岐
				auth.simpleLogin(nickname);

				new FragmentTransit(fragment).toNext(ChatFragment.newInstance());

			}
		});
		
		EditText loginSimple = (EditText) view.findViewById(R.id.loginAuthSimpleNickname);
		loginSimple.setText(user.getNickname());
		loginSimple.setOnEditorActionListener(new OnEditorActionListener() {	
			
			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				EditText nicknameTextView = (EditText) fragment.getActivity().findViewById(R.id.loginAuthSimpleNickname);
				String nickname = nicknameTextView.getText().toString();
				
				//TODO ログイン失敗時の分岐
				auth.simpleLogin(nickname);

				new FragmentTransit(fragment).toNext(ChatFragment.newInstance());

				return false;
			}
		});

		view.findViewById(R.id.loginAuthTwitter).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new FragmentTransit(fragment).toNext(ChatFragment.newInstance());
			}
		});

	}

}
