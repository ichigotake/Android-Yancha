package net.ichigotake.yancha.chat;

import io.socket.SocketIOException;
import net.ichigotake.yancha.common.ChatStatus;
import net.ichigotake.yancha.common.api.YanchaEmitter;
import net.ichigotake.yancha.common.user.User;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.util.Log;

public class YanchaCallbackListener implements ChatCallbackListener {

	final private String TAG = YanchaCallbackListener.class.getSimpleName();
	
	final private User mUser;
	
	final private ChatContainer mChatContainer;
	
	final private Handler mHandler = new Handler();
	
	public YanchaCallbackListener(User user, ChatContainer container) {
		mUser = user;
		mChatContainer = container;
	}
	
	@Override
	public void onConnect(YanchaEmitter emitter) {
		Log.d(TAG, "onConnect");
		emitter.emitTokenLogin(mUser.getToken());
		
		emitter.emitJoinTag(mChatContainer.getTagList());
		
		mChatContainer.updateStatus(ChatStatus.ONLINE);
	}

	@Override
	public void onDisconnect() {
		Log.d(TAG, "onDisconnect");
		mChatContainer.updateStatus(ChatStatus.OFFLINE);
	}

	@Override
	public void onError(SocketIOException socketIOException) {
		Log.d(TAG, "onError");
		socketIOException.printStackTrace();
	}

	@Override
	public void onNicknames(YanchaEmitter emitter, final String response) {
		mHandler.post(new Runnable() {
			
			@Override
			public void run() {
				mChatContainer.updateJoinUsers(response);
			}
		});
	}

	@Override
	public void onUserMessage(YanchaEmitter emitter, String response) {
		try {
			Log.d(TAG, "onUserMessage");
			final JSONObject a = new JSONObject(response);
			mHandler.post(new Runnable() {
				
				@Override
				public void run() {
					mChatContainer.addMessage(a);
				}
			});
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
