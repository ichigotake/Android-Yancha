package net.ichigotake.yancha.core.api;

import java.util.Map;

import net.ichigotake.yancha.core.message.SendMessage;
import net.ichigotake.yancha.core.message.SendMessageListener;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.common.eventbus.Subscribe;


/**
 * yanchaサーバーとのイベントの送受信をするためのクラス
 */
public class YanchaEmitter implements SendMessageListener {

	final public static String ANNOUNCEMENT 		= "announcement";
	final public static String CONNECT		 		= "connect";
	final public static String CONNECTING		 	= "connecting";
	final public static String DISCONNECT		 	= "disconnect";
	final public static String DELETE_USER_MESSAGE	= "nicknames";
	final public static String ERROR 				= "error";
	final public static String JOIN_TAG 			= "join tag";
	final public static String NICKNAMES 			= "nicknames";
	final public static String NO_SESSION 			= "no session";
	final public static String RECONNECT 			= "reconnect";
	final public static String RECONNECTING			= "reconnecting";
	final public static String TOKEN_LOGIN		 	= "token login";
	final public static String USER_MESSAGE 		= "user message";

	private Chat chat;
	
	public YanchaEmitter(Chat chat) {
		this.chat = chat;
	}
	
	public void emitUserMessage(String message) {
		chat.emit(USER_MESSAGE, message);
	}
	
	/**
	 * Emit to "token login"
	 * 
	 * @param token
	 */
	public void emitTokenLogin(String token) {
		Log.d(getClass().getSimpleName(), "token:: " + token);
		chat.emit(TOKEN_LOGIN, token);
	}
	
	/**
	 * Emit to "join tag"
	 * 
	 * @param tag
	 */
	public void emitJoinTag(String tag) {
		try {
			JSONObject tags = new JSONObject();
			tags.put(tag, tag);
			chat.emit(JOIN_TAG, tags);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public void emitJoinTag(Map<String, Integer> tags) {
		try {
			JSONObject postTags = new JSONObject();
			for (String tag : tags.keySet()) {
				postTags.put(tag, tag);
			}
			chat.emit(JOIN_TAG, postTags);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public void emitConnect() {
		chat.emit(CONNECT, "");
	}
	
	public void emitDisconnect() {
		chat.emit(DISCONNECT, "bye");
	}
	
	@Subscribe
	public void sendMessage(SendMessage message) {
		emitUserMessage(message.getMessage());
	}
}
