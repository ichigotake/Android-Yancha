package net.ichigotake.yancha.common.api.socketio.listener;

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

    public void onEvent(ConnectResponse response);

    public void onEvent(ConnectingResponse response);

    public void onEvent(DisconnectResponse response);

    public void onEvent(ErrorResponse response);

    public void onEvent(ReconnectResponse response);

    public void onEvent(ReconnectingResponse response);

}
