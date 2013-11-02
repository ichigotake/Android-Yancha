package net.ichigotake.yancha.chat;

import java.net.MalformedURLException;

import net.ichigotake.colorfulsweets.lib.fragment.FragmentTransit;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.chat.socketio.YanchaCallbackListener;
import net.ichigotake.yancha.common.api.rest.ApiUri;
import net.ichigotake.yancha.common.api.socketio.Chat;
import net.ichigotake.yancha.common.context.AppContext;
import net.ichigotake.yancha.common.user.User;
import net.ichigotake.yancha.login.LoginFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * チャット画面
 */
public class ChatFragment extends Fragment {

	private Chat chat;

	public static ChatFragment newInstance() {
		return new ChatFragment();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.yc_chat_main, container, false);
		
		ApiUri uri = new User(getActivity()).getApiUri();
		try {
			chat = new Chat(uri.getAbsoluteUrl());
		} catch (MalformedURLException e) {
			e.printStackTrace();
            Toast.makeText(getActivity(),
                    "無効なURLです",
                    Toast.LENGTH_SHORT);
            FragmentTransit.from(getActivity());
            new FragmentTransit(getActivity())
                    .toReplace(AppContext.FRAGMENT_ID_CONTENT, LoginFragment.newInstance());
            return view;
		}

        YanchaCallbackListener yanchaListener =
				new YanchaCallbackListener(chat.getEmitter(), getActivity(), view);
		chat.setCallbackListener(yanchaListener);
		
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		chat.run();
	}
	
	@Override
	public void onPause() {
		super.onStop();
		chat.disconnect();
	}
	
	@Override
	public void onDestroy() {
		chat.disconnect();
		super.onDestroy();
	}
	
}
