package net.ichigotake.yancha.chat.socketio;

import com.google.common.eventbus.Subscribe;

import net.ichigotake.colorfulsweets.lib.context.ActivityTransit;
import net.ichigotake.yancha.ChatActivity;
import net.ichigotake.yancha.common.api.socketio.listener.LoginEventListener;
import net.ichigotake.yancha.common.api.socketio.response.JoinTagResponse;
import net.ichigotake.yancha.common.api.socketio.response.NicknamesResponse;
import net.ichigotake.yancha.common.api.socketio.response.NoSessionResponse;
import net.ichigotake.yancha.common.api.socketio.response.TokenLoginResponse;
import net.ichigotake.yanchasdk.lib.model.ChatUser;
import net.ichigotake.yanchasdk.lib.model.JoinUserFactory;
import net.ichigotake.yanchasdk.lib.model.JoinUsers;

import org.json.JSONException;

/**
 * ログイン関連のイベントリスナ
 */
public class LoginListener implements LoginEventListener {

    final private ChatMediator mParameter;
    final private JoinUserFactory mUserFactory;

    public LoginListener(ChatMediator parameter) {
        mParameter = parameter;
        mUserFactory = new JoinUserFactory();
    }

    @Override @Subscribe
    public void onJoinTag(JoinTagResponse response) {

    }

    @Override @Subscribe
    public void onNicknames(final NicknamesResponse response) {
        try {
            String rawJson = response.getResponseBody().or("{}");
            JoinUsers users = mUserFactory.fromNicknameEvent(rawJson);
            updateJoinUsers(users);
        } catch (JSONException e) {
            e.printStackTrace();
            updateJoinUsers(new JoinUsers());
        }
    }

    private void updateJoinUsers(final JoinUsers users) {
        mParameter.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                mParameter.getContainer().updateJoinUsers(users);
            }
        });
        mParameter.getContainer().updateMyself(users);
    }

    @Override @Subscribe
    public void onNoSession(NoSessionResponse response) {
        new ActivityTransit(mParameter.getActivity())
                .clearTop()
                .toNext(ChatActivity.class);
    }

    @Override @Subscribe
    public void onTokenLogin(TokenLoginResponse response) {
        try {
            ChatUser myself = mUserFactory.fromTokenLoginEvent(response.getResponseBody().get());
            mParameter.getContainer().updateMyself(myself);
        } catch (JSONException e) {
            e.printStackTrace();
            // TODO エラーイベント
        }
    }
}
