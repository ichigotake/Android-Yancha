package net.ichigotake.yancha.search;

import net.ichigotake.yancha.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * �`���b�g�̃��O����
 */
public class ChatLogSearchFragment extends Fragment {

	public ChatLogSearchFragment() {
	}
	
	public static ChatLogSearchFragment createInstance() {
		return new ChatLogSearchFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.yc_chat_search, container, false);
		
		return view;
	}

}
