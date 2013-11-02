package net.ichigotake.yancha.chat.socketio;

import com.google.common.eventbus.Subscribe;

import net.ichigotake.colorfulsweets.lib.context.ActivityTransit;
import net.ichigotake.yancha.ChatActivity;
import net.ichigotake.yancha.common.api.socketio.listener.LoginEventListener;
import net.ichigotake.yancha.common.api.socketio.response.JoinTagResponse;
import net.ichigotake.yancha.common.api.socketio.response.NicknamesResponse;
import net.ichigotake.yancha.common.api.socketio.response.NoSessionResponse;
import net.ichigotake.yancha.common.api.socketio.response.TokenLoginResponse;

/**
 * Created by ichigotake on 2013/11/02.
 */
public class LoginListener implements LoginEventListener {

    final private ChatMediator mParameter;

    public LoginListener(ChatMediator parameter) {
        mParameter = parameter;
    }

    @Override @Subscribe
    public void onJoinTag(JoinTagResponse response) {

    }

    @Override @Subscribe
    public void onNicknames(final NicknamesResponse response) {
        if (response.getResponseBody().isPresent()) {
            mParameter.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    mParameter.getContainer().updateJoinUsers(response.getResponseBody().get());
                }
            });
        } else {
            // TODO デバッグ用に例外を用意しよう
        }
    }

    @Override @Subscribe
    public void onNoSession(NoSessionResponse response) {
        new ActivityTransit(mParameter.getActivity())
                .clearTop()
                .toNext(ChatActivity.class);
    }

    @Override @Subscribe
    public void onTokenLogin(TokenLoginResponse response) {

    }
}
