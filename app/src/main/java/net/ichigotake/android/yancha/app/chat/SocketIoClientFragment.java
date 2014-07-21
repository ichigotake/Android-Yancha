package net.ichigotake.android.yancha.app.chat;

public interface SocketIoClientFragment {

    void onSocketIoEvent(SocketIoEvent event, String response);
}
