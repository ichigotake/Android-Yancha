package net.ichigotake.yancha.chat;

import java.net.MalformedURLException;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.chat.socketio.YanchaCallbackListener;
import net.ichigotake.yancha.common.api.rest.ApiUri;
import net.ichigotake.yancha.common.api.socketio.Chat;
import net.ichigotake.yancha.common.user.User;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
            //TODO 何かしら対策を
			e.printStackTrace();
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
