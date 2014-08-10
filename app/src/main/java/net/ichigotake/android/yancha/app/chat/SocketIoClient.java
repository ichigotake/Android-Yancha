package net.ichigotake.android.yancha.app.chat;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;

import io.socket.IOCallback;
import io.socket.SocketIO;

public final class SocketIoClient {

    private static final String LOG_TAG = "SocketIOClient";
    private final SocketIO socket;

    public static SocketIoClient run(String serverUrl, SocketIoEventListener listener)
            throws MalformedURLException {
        final SocketIoClient client = new SocketIoClient(new SocketIO(serverUrl));
        client.connect(new SocketIoCallback(listener));
        return client;
    }

    private SocketIoClient(SocketIO socket) {
        this.socket = socket;
    }

    public void connect(IOCallback callback) {
        Log.d(LOG_TAG, "connect");
        socket.connect(callback);
    }

    public void emit(SocketIoEvent event, String value) {
        Log.d(LOG_TAG, "emit: " + event + " => " + value);
        socket.emit(event.getEventName(), value);
    }

    public void emit(SocketIoEvent event, JSONObject json) {
        Log.d(LOG_TAG, "emit: " + event + " => " + json);
        socket.emit(event.getEventName(), json);
    }

    public void emit(SocketIoEvent event, JSONArray json) {
        Log.d(LOG_TAG, "emit: " + event + " => " + json);
        socket.emit(event.getEventName(), json);
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    public void disconnect() {
        Log.d(LOG_TAG, "disconnect");
        socket.disconnect();
    }

}
