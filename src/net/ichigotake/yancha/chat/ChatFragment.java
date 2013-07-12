package net.ichigotake.yancha.chat;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.ui.FragmentTransit;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class ChatFragment extends Fragment {

	public ChatFragment() {
	}
	
	public static ChatFragment newInstance() {
		return new ChatFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_chat_main, container, false);
		
		view.findViewById(R.id.textView_linkSearch).setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View view) {
				new FragmentTransit(ChatFragment.this).toNext(ChatLogSearchFragment.createInstance());
			}
		});
		return view;
	}
}
