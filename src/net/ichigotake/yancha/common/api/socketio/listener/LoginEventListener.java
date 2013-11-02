package net.ichigotake.yancha.common.api.socketio.listener;

import net.ichigotake.yancha.common.api.socketio.response.JoinTagResponse;
import net.ichigotake.yancha.common.api.socketio.response.NicknamesResponse;
import net.ichigotake.yancha.common.api.socketio.response.NoSessionResponse;
import net.ichigotake.yancha.common.api.socketio.response.TokenLoginResponse;

/**
 * Created by ichigotake on 2013/11/02.
 */
public interface LoginEventListener extends EmitEventListener {

    public void onJoinTag(JoinTagResponse response);

    public void onNicknames(NicknamesResponse response);

    public void onNoSession(NoSessionResponse response);

    public void onTokenLogin(TokenLoginResponse response);

}
