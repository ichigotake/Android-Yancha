package net.ichigotake.yancha.chat;

import java.net.MalformedURLException;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.api.ApiUri;
import net.ichigotake.yancha.common.api.Chat;
import net.ichigotake.yancha.common.user.User;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * É`ÉÉÉbÉgâÊñ 
 */
public class ChatFragment extends Fragment {

	private Chat chat;

	private ChatContainer chatContainer;
	
	public ChatFragment() {
	}
	
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
		
		chatContainer = new ChatContainer(this);
		chatContainer.initializeView(view);
		
		User user = new User(getActivity());
		ApiUri uri = user.getApiUri();
		YanchaCallbackListener yanchaListener = new YanchaCallbackListener(user, chatContainer);
		try {
			chat = new Chat(uri.getAbsoluteUrl());
			chat.setCallbackListener(yanchaListener);
			chatContainer.setEmitter(chat.getEmitter());
		} catch (MalformedURLException e) {
			//TODO âΩÇ©ÇµÇÁëŒçÙÇ
			e.printStackTrace();
		}
		
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		chat.run();
	}

	@Override
	public void onPause() {
		super.onStop();
		chat.disconnect();
	}
	
}
