package net.ichigotake.yancha.chat;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIOException;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.net.Chat;
import net.ichigotake.yancha.net.YanchaApi;
import net.ichigotake.yancha.net.YanchaEmitter;
import net.ichigotake.yancha.ui.FragmentTransit;

import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class ChatFragment extends Fragment {

	private static Chat chat;

	private YanchaEmitter emitter;
	
	private String token;
	
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
	
	@Override
	public void onResume() {
		super.onResume();
		
		final SharedPreferences pref = getActivity().getSharedPreferences("owner", Context.MODE_PRIVATE);
		token = pref.getString("token", "");

		chat = new Chat(YanchaApi.SERVER_URL, new IOCallback() {
			
			@Override
			public void onMessage(JSONObject json, IOAcknowledge ack) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onMessage(String data, IOAcknowledge ack) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onError(SocketIOException socketIOException) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDisconnect() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onConnect() {
				Log.d("yancha-ChatFragment", "onconnect");
				
				emitter.emitTokenLogin(token);
				
				//TODO: default tag is NOT HARD CODING
				emitter.emitJoinTag("PUBLIC");
			}
			
			@Override
			public void on(String event, IOAcknowledge ack, Object... args) {
				Log.d("yancha-ChatFragment", "on: " + event);
			}
		});
		emitter = new YanchaEmitter(chat);
		
		chat.start();
	}
	
}
