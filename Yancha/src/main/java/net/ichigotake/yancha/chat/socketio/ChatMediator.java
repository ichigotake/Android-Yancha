package net.ichigotake.yancha.chat.socketio;

import android.app.Activity;
import android.os.Handler;
import android.view.View;

import net.ichigotake.yancha.chat.ChatContainer;
import net.ichigotake.yancha.common.api.socketio.YanchaEmitter;
import net.ichigotake.yancha.common.user.AppUser;

/**
 * チャットイベントとSocketIOクライアントを繋ぐ仲介役
 *
 * TODO デザインパターンそのままの名前やめたい
 */
public class ChatMediator {

    final private Handler mHandler = new Handler();

    final private Activity mActivity;

    final private AppUser mAppUser;

    final private ChatContainer mChatContainer;

    final private YanchaEmitter mEmitter;

    public ChatMediator(YanchaEmitter emitter, Activity activity, View view) {
        mActivity = activity;
        mAppUser = new AppUser(activity);
        mChatContainer = new ChatContainer(activity, emitter, view);
        mEmitter = emitter;
    }

    Activity getActivity() {
       return mActivity;
    }

    YanchaEmitter getEmitter() {
        return mEmitter;
    }

    ChatContainer getContainer() {
        return mChatContainer;
    }

    AppUser getUser() {
        return mAppUser;
    }

    void runOnUiThread(Runnable runnable) { mHandler.post(runnable); }
}
