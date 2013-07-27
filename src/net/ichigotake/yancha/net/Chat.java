package net.ichigotake.yancha.net;

import io.socket.IOCallback;
import io.socket.SocketIO;

import java.net.MalformedURLException;

import org.json.JSONObject;


/**
 * チャットサーバーと通信をするクラス
 */
public class Chat extends Thread {
	
	final private String serverUrl;
	
	final private IOCallback callback;

	private SocketIO socket;
	
	public Chat(String serverUrl, IOCallback callback) {
		this.callback = callback;
		this.serverUrl = serverUrl;
	}

	@Override
	public void run() {
		try {
			socket = new SocketIO(serverUrl);
			socket.connect(callback);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void emit(String event, JSONObject args) {
		socket.emit(event, args);
	}

	public void emit(String event, String args) {
		socket.emit(event, args);
	}

}
