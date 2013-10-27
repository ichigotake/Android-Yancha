package net.ichigotake.yancha.chat;

import io.socket.SocketIOException;
import net.ichigotake.yancha.common.ChatStatus;
import net.ichigotake.yancha.common.api.ChatCallbackListener;
import net.ichigotake.yancha.common.api.YanchaEmitter;
import net.ichigotake.yancha.common.user.User;
import net.ichigotake.yanchasdk.lib.model.PostMessageFactory;
import net.ichigotake.yanchasdk.lib.model.PostMessageBuilder.PostMessage;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.View;

public class YanchaCallbackListener implements ChatCallbackListener {

	final private String TAG = YanchaCallbackListener.class.getSimpleName();
	
	final private User mUser;
	
	final private ChatContainer mChatContainer;
	
	final private YanchaEmitter mEmitter;
	
	final private Handler mHandler = new Handler();
	
	public YanchaCallbackListener(YanchaEmitter emitter, Activity activity, View view) {
		mUser = new User(activity);
		mChatContainer = new ChatContainer(activity, emitter, view);
		mEmitter = emitter;
	}
	
	@Override
	public void onConnect() {
		Log.d(TAG, "onConnect");
		mEmitter.emitTokenLogin(mUser.getToken());
		
		mEmitter.emitJoinTag(mChatContainer.getTagList());
		
		mHandler.post(new Runnable() {
			
			@Override
			public void run() {
				mChatContainer.updateStatus(ChatStatus.ONLINE);
			}
		});
	}

	@Override
	public void onDisconnect() {
		Log.d(TAG, "onDisconnect");
		mHandler.post(new Runnable() {
			
			@Override
			public void run() {
				mChatContainer.updateStatus(ChatStatus.OFFLINE);
			}
		});
	}

	@Override
	public void onError(SocketIOException socketIOException) {
		Log.d(TAG, "onError");
		socketIOException.printStackTrace();
	}

	@Override
	public void onNicknames(final String response) {
		mHandler.post(new Runnable() {
			
			@Override
			public void run() {
				mChatContainer.updateJoinUsers(response);
			}
		});
	}

	@Override
	public void onUserMessage(String response) {
		try {
			Log.d(TAG, "onUserMessage");
			final JSONObject json = new JSONObject(response);
			final PostMessage message;
			try {
				message = PostMessageFactory.create(json);
			} catch (JSONException e) {
				e.printStackTrace();
				return ;
			}
			Log.d(TAG, message.getNickname() + " : " + message.getMessage());
			
			mHandler.post(new Runnable() {
				
				@Override
				public void run() {
					mChatContainer.addMessage(message);
				}
			});
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
