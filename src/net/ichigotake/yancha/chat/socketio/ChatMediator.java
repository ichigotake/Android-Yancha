package net.ichigotake.yancha.chat.socketio;

import android.app.Activity;
import android.os.Handler;
import android.view.View;

import net.ichigotake.yancha.chat.ChatContainer;
import net.ichigotake.yancha.common.api.socketio.YanchaEmitter;
import net.ichigotake.yancha.common.user.User;

/**
 * チャットイベントとSocketIOクライアントを繋ぐ仲介役
 */
public class ChatMediator {

    final private Handler mHandler = new Handler();
    final private User mUser;

    final private ChatContainer mChatContainer;

    final private YanchaEmitter mEmitter;

    public ChatMediator(YanchaEmitter emitter, Activity activity, View view) {
        mUser = new User(activity);
        mChatContainer = new ChatContainer(activity, emitter, view);
        mEmitter = emitter;
    }

    YanchaEmitter getEmitter() {
        return mEmitter;
    }

    ChatContainer getContainer() {
        return mChatContainer;
    }

    User getUser() {
        return mUser;
    }

    void runOnUiThread(Runnable runnable) { mHandler.post(runnable); }
}
