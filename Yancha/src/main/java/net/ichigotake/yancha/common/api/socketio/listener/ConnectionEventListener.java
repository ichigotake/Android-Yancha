package net.ichigotake.yancha.common.api.socketio.listener;

import com.google.common.eventbus.Subscribe;

import net.ichigotake.yancha.common.api.socketio.response.ConnectResponse;
import net.ichigotake.yancha.common.api.socketio.response.ConnectingResponse;
import net.ichigotake.yancha.common.api.socketio.response.DisconnectResponse;
import net.ichigotake.yancha.common.api.socketio.response.ErrorResponse;
import net.ichigotake.yancha.common.api.socketio.response.ReconnectResponse;
import net.ichigotake.yancha.common.api.socketio.response.ReconnectingResponse;

/**
 * SocketIOの接続に関するコールバックリスナ
 */
public interface ConnectionEventListener extends EmitEventListener {

    @Subscribe
    public void onConnect(ConnectResponse response);

    @Subscribe
    public void onConnecting(ConnectingResponse response);

    @Subscribe
    public void onDisconnect(DisconnectResponse response);

    @Subscribe
    public void onError(ErrorResponse response);

    @Subscribe
    public void onReconnect(ReconnectResponse response);

    @Subscribe
    public void onReconnecting(ReconnectingResponse response);

}
