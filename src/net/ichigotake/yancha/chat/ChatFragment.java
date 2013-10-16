package net.ichigotake.yancha.chat;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIOException;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.ui.actionbar.ActionBar;
import net.ichigotake.yancha.core.ChatStatus;
import net.ichigotake.yancha.core.api.ApiUri;
import net.ichigotake.yancha.core.api.Chat;
import net.ichigotake.yancha.core.api.YanchaEmitter;
import net.ichigotake.yancha.core.user.User;
import net.ichigotake.yancha.users.JoinUsersContainer;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * チャット画面
 */
public class ChatFragment extends Fragment {

	private Chat chat;

	private YanchaEmitter emitter;
	
	private User user;
	
	private Handler handler;
	
	private ChatContainer chatContainer;
	
	private JoinUsersContainer joinUsersContainer;
	
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
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean hasBeenDone = new ActionBar(getActivity()).setOnOptionsItemSelected(item);
		if (hasBeenDone) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.yc_chat_main, container, false);
		
		new ActionBar(getActivity())
			.setupWithSetDisplayHomeAsUpEnabled();
		
		chatContainer = new ChatContainer(this);
		chatContainer.initializeView(view);
		
		joinUsersContainer = new JoinUsersContainer();
		joinUsersContainer.initializeView(view);
		
		handler = new Handler();
		user = new User(getActivity());
		
		return view;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		if (chat == null) {
			ApiUri uri = new User(getActivity()).getApiUri();
			chat = new Chat(uri.getAbsoluteUrl(), new ChatCallback());
			emitter = new YanchaEmitter(chat);
			chatContainer.registerListener(emitter);
			
			chat.run();
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Log.d(getClass().getSimpleName(), "token: " + user.getToken());
				emitter.emitTokenLogin(user.getToken());
			}
		}, 1*1000);
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

	private class ChatCallback implements IOCallback {
		
		@Override
		public void onMessage(JSONObject json, IOAcknowledge ack) {
			Log.d(getClass().getSimpleName(), "onMessage json ack");
		}
		
		@Override
		public void onMessage(String data, IOAcknowledge ack) {
			Log.d(getClass().getSimpleName(), "onMessage data ack");
		}
		
		@Override
		public void onError(SocketIOException socketIOException) {
			emitter.emitTokenLogin(user.getToken());
			Log.d(getClass().getSimpleName(), "error socketIOException " + socketIOException.getMessage() + " : " + socketIOException.getLocalizedMessage().toString());
		}
		
		@Override
		public void onDisconnect() {
			Log.d(getClass().getSimpleName(), "onDisconnect");
			chatContainer.updateStatus(ChatStatus.OFFLINE);
		}
		
		@Override
		public void onConnect() {
			Log.d(getClass().getSimpleName(), "onConnect");
			emitter.emitTokenLogin(user.getToken());
			
			//TODO: タグはPreferenceで管理しましょ
			emitter.emitJoinTag("PUBLIC");
			
			chatContainer.updateStatus(ChatStatus.ONLINE);
		}
		
		@Override
		public void on(String event, IOAcknowledge ack, final Object... args) {
			Log.d(getClass().getSimpleName(), "on");
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
			} else if (event.equals(YanchaEmitter.NICKNAMES)) {
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						chatContainer.updateJoinUsers(args[0].toString());
					}
				});
			}
		}
	}
}
