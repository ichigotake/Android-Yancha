package net.ichigotake.yancha.chat.socketio;

import net.ichigotake.yancha.common.api.socketio.YanchaEmitter;
import net.ichigotake.yancha.common.api.socketio.listener.ConnectionEventListener;
import net.ichigotake.yancha.common.api.socketio.response.ConnectResponse;
import net.ichigotake.yancha.common.api.socketio.response.ConnectingResponse;
import net.ichigotake.yancha.common.api.socketio.response.DisconnectResponse;
import net.ichigotake.yancha.common.api.socketio.response.ErrorResponse;
import net.ichigotake.yancha.common.api.socketio.response.ReconnectResponse;
import net.ichigotake.yancha.common.api.socketio.response.ReconnectingResponse;

public class ConnectionListener implements ConnectionEventListener {

    final private String TAG = ConnectionListener.class.getClass().getSimpleName();

    final private ChatMediator mParameter;

    public ConnectionListener(ChatMediator parameter) {
        mParameter = parameter;
    }

    @Override
    public void onEvent(ConnectResponse response) {
        YanchaEmitter emitter = mParameter.getEmitter();
        emitter.emitTokenLogin(mParameter.getUser().getToken());

        emitter.emitJoinTag(mParameter.getContainer().getTagList());

        mParameter.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                mParameter.getActivity().getActionBar()
                        .setTitle(mParameter.getUser().getConnectServerAuthority());
            }
        });
    }

    @Override
    public void onEvent(ConnectingResponse response) {

    }

    @Override
    public void onEvent(DisconnectResponse response) {

    }

    @Override
    public void onEvent(ErrorResponse response) {

    }

    @Override
    public void onEvent(ReconnectResponse response) {

    }

    @Override
    public void onEvent(ReconnectingResponse response) {

    }
}
