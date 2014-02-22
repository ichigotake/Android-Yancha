package net.ichigotake.yancha.common.api.socketio.listener;

import net.ichigotake.yancha.common.api.socketio.response.JoinTagResponse;
import net.ichigotake.yancha.common.api.socketio.response.NicknamesResponse;
import net.ichigotake.yancha.common.api.socketio.response.NoSessionResponse;
import net.ichigotake.yancha.common.api.socketio.response.TokenLoginResponse;

public interface LoginEventListener extends EmitEventListener {

    public void onEvent(JoinTagResponse response);

    public void onEvent(NicknamesResponse response);

    public void onEvent(NoSessionResponse response);

    public void onEvent(TokenLoginResponse response);

}
