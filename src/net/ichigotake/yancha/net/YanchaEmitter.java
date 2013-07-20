package net.ichigotake.yancha.net;

import net.ichigotake.yancha.ui.SendMessage;
import net.ichigotake.yancha.ui.SendMessageListener;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.common.eventbus.Subscribe;


/**
 * Event emitter
 */
public class YanchaEmitter implements SendMessageListener {

	final public static String ANNOUNCEMENT 		= "announcement";
	final public static String CONNECT		 		= "connect";
	final public static String CONNECTING		 	= "connecting";
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
	
	@Subscribe
	public void sendMessage(SendMessage message) {
		emitUserMessage(message.getMessage());
	}
}
