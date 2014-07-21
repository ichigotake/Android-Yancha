package net.ichigotake.android.yancha.app.chat;

import android.util.Log;

import org.json.JSONObject;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIOException;

public class SocketIoCallback implements IOCallback {

    private final String LOG_TAG = "SocketIoCallback";
    private final SocketIoEventListener listener;

    public SocketIoCallback(SocketIoEventListener listener) {
        this.listener = listener;
    }

    @Override
    public void onDisconnect() {
        Log.d(LOG_TAG, "disconnect");
        listener.onResponse(SocketIoEvent.DISCONNECT, "");
    }

    @Override
    public void onConnect() {
        Log.d(LOG_TAG, "onConnect");
        listener.onResponse(SocketIoEvent.CONNECT, "");
    }

    @Override
    public void onMessage(String s, IOAcknowledge ioAcknowledge) {
        Log.d(LOG_TAG, "onMessage: " + s);
    }

    @Override
    public void onMessage(JSONObject jsonObject, IOAcknowledge ioAcknowledge) {
        Log.d(LOG_TAG, "onMessage: " + jsonObject);
    }

    @Override
    public void on(String s, IOAcknowledge ioAcknowledge, Object... objects) {
        Log.d(LOG_TAG, "on" + s);
        String response = objects != null ? objects[0].toString() : "";
        listener.onResponse(SocketIoEvent.dispatch(s), response);
    }

    @Override
    public void onError(SocketIOException e) {
        Log.e(LOG_TAG, "error", e);
    }

}
