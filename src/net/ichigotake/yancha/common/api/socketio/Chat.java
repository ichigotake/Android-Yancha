package net.ichigotake.yancha.common.api.socketio;

import net.ichigotake.yancha.chat.socketio.YanchaCallbackListener;

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
	
	final private EmitEventDispatcher mDispatcher;
	
	private YanchaEmitter mEmitter;
	
	private SocketIO mSocket;

    public Chat(String serverUrl) throws MalformedURLException {
		mServerUrl = serverUrl;
		mDispatcher = new EmitEventDispatcher();
		mSocket = createSocketIO(mServerUrl);
		mEmitter = new YanchaEmitter(mSocket);
	}

    private SocketIO createSocketIO(String serverUrl) throws MalformedURLException {
        return new SocketIO(serverUrl);
    }

	@Override
	public void run() {
		connect();
	}
	
	public YanchaEmitter getEmitter() {
		return mEmitter;
	}

	public void connect() {
		if (! mSocket.isConnected()) {
			mSocket.connect(this);
			mEmitter.emitConnect();
		}
	}
	
	public void disconnect() {
		if (mSocket.isConnected()) {
            mEmitter.emitDisconnect();
			mSocket.disconnect();
            mSocket = null;
		}
	}
	
	@Override
	public void onDisconnect() {
		mDispatcher.dispatch(EmitEvent.DISCONNECT);
	}

	@Override
	public void onConnect() {
        mDispatcher.dispatch(EmitEvent.CONNECT);
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
		mDispatcher.dispatch(event, args[0].toString());
	}

	@Override
	public void onError(SocketIOException socketIOException) {
        mDispatcher.dispatch(EmitEvent.ERROR);
	}

    public void setCallbackListener(YanchaCallbackListener listener) {
        mDispatcher.registerListener(listener.createConnectionListener());
        mDispatcher.registerListener(listener.createLoginListener());
        mDispatcher.registerListener(listener.createMessageListener());
    }

    public void reconnect() throws MalformedURLException {
        if (null != mSocket) {
            mSocket = null;
        }

        mSocket = createSocketIO(mServerUrl);
        connect();
    }

}
