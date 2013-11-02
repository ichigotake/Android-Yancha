package net.ichigotake.yancha.common.api;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

import java.net.MalformedURLException;


import org.json.JSONObject;


/**
 * サーバーへ接続するチャットクライアント
 */
public class Chat extends Thread implements IOCallback {
	
	final private String mServerUrl;
	
	final private ChatEventDispatcher mDispatcher;
	
	private ChatCallbackListener mListener;
	
	private YanchaEmitter mEmitter;
	
	private SocketIO mSocket;
	
	public Chat(String serverUrl) throws MalformedURLException {
		mServerUrl = serverUrl;
		mDispatcher = new ChatEventDispatcher();
		mSocket = new SocketIO(mServerUrl);
		mEmitter = new YanchaEmitter(mSocket);
	}
	
	public void setCallbackListener(ChatCallbackListener listener) {
		mListener = listener;
	}

	@Override
	public void run() {
		connect();
	}
	
	public YanchaEmitter getEmitter() {
		return mEmitter;
	}

	public void setListener(ChatCallbackListener listener) {
		mListener = listener;
	}
	
	public void connect() {
		if (! mSocket.isConnected()) {
			mSocket.connect(this);
			mEmitter.emitConnect();
		}
	}
	
	public void disconnect() {
		if (mSocket.isConnected()) {
			mSocket.disconnect();
			mEmitter.emitDisconnect();
		}
	}
	
	@Override
	public void onDisconnect() {
		mListener.onDisconnect();
	}

	@Override
	public void onConnect() {
		mListener.onConnect();
	}

	@Override
	public void onMessage(String data, IOAcknowledge ack) {
		// no event
	}

	@Override
	public void onMessage(JSONObject json, IOAcknowledge ack) {
		// no event
	}

	@Override
	public void on(String event, IOAcknowledge ack, Object... args) {
		// TODO ArrayIndexOutOfBoundsException対策
		mDispatcher.dispatch(event, args[0].toString(), mListener);
	}

	@Override
	public void onError(SocketIOException socketIOException) {
		mListener.onError(socketIOException);
	}
	
}
