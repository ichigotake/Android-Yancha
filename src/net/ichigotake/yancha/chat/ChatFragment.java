package net.ichigotake.yancha.chat;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIOException;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.data.User;
import net.ichigotake.yancha.net.Chat;
import net.ichigotake.yancha.net.YanchaApi;
import net.ichigotake.yancha.net.YanchaEmitter;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChatFragment extends Fragment {

	private Chat chat;

	private YanchaEmitter emitter;
	
	private User user;
	
	private Handler handler;
	
	private ChatContainer chatContainer;
	
	public ChatFragment() {
	}
	
	public static ChatFragment newInstance() {
		return new ChatFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.yc_chat_main, container, false);
		
		chatContainer = new ChatContainer(this);
		chatContainer.initializeView(view);
		
		handler = new Handler();
		
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				emitter.emitTokenLogin(user.getToken());
			}
		}, 1*1000);
	}

	@Override
	public void onStart() {
		super.onStart();
		user = new User(getActivity());

		//TODO コールバックを別クラスへ
		if (chat == null) {
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
					emitter.emitTokenLogin(user.getToken());
					
					//TODO: タグはPreferenceで管理しましょ
					emitter.emitJoinTag("PUBLIC");
				}
				
				@Override
				public void on(String event, IOAcknowledge ack, Object... args) {
					if (event.equals(YanchaEmitter.CONNECTING)) {
						chat.emit("connecting", ack.toString());
					} else if (event.equals(YanchaEmitter.USER_MESSAGE)) {
						try {
							final JSONObject a = new JSONObject(args[0].toString());
							handler.post(new Runnable() {
								
								@Override
								public void run() {
									chatContainer.addMessage(a);
								}
							});
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}
			});
			emitter = new YanchaEmitter(chat);
			chatContainer.registerListener(emitter);
			
			chat.run();
		}
	}
	
	@Override
	public void onStop() {
		super.onStop();
		emitter.emitDisconnect();
	}

	@Override
	public void onPause() {
		super.onStop();
		emitter.emitDisconnect();
	}

}
