package net.ichigotake.yancha.chat.socketio;

import com.google.common.eventbus.Subscribe;

import net.ichigotake.colorfulsweets.lib.compat.actionbar.ActionBarSetting;
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

    @Override @Subscribe
    public void onConnect(ConnectResponse response) {
        YanchaEmitter emitter = mParameter.getEmitter();
        emitter.emitTokenLogin(mParameter.getUser().getToken());

        emitter.emitJoinTag(mParameter.getContainer().getTagList());

        mParameter.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                ActionBarSetting.from(mParameter.getActivity())
                        .setTitle(mParameter.getUser().getConnectServerAuthority());
            }
        });
    }

    @Override @Subscribe
    public void onConnecting(ConnectingResponse response) {

    }

    @Override @Subscribe
    public void onDisconnect(DisconnectResponse response) {

    }

    @Override @Subscribe
    public void onError(ErrorResponse response) {

    }

    @Override @Subscribe
    public void onReconnect(ReconnectResponse response) {

    }

    @Override @Subscribe
    public void onReconnecting(ReconnectingResponse response) {

    }
}
