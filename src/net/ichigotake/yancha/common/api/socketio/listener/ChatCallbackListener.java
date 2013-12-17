package net.ichigotake.yancha.common.api.socketio.listener;

public interface ChatCallbackListener {

    public ConnectionEventListener createConnectionListener();

    public LoginEventListener createLoginListener();

    public MessageEventListener createMessageListener();
}
