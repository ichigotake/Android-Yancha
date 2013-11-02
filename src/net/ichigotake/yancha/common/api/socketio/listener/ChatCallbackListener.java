package net.ichigotake.yancha.common.api.socketio.listener;

/**
 * Created by ichigotake on 2013/11/02.
 */
public interface ChatCallbackListener {

    public ConnectionEventListener createConnectionListener();

    public LoginEventListener createLoginListener();

    public MessageEventListener createMessageListener();
}
