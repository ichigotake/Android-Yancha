package net.ichigotake.android.yancha.app.chat;

public interface SocketIoEventListener {

    void onResponse(SocketIoEvent event, String response);
}
