package net.ichigotake.yancha.chat;

import android.view.View;

import net.ichigotake.colorfulsweets.lib.os.AsyncRunnableTask;
import net.ichigotake.yancha.common.api.socketio.YanchaEmitter;
import net.ichigotake.yancha.sdk.model.ChatMessage;

/**
 * ++をするためのクリックリスナ
 */
class OnPlusplusClickListener implements View.OnClickListener {

    final private int mMessageId;
    final private YanchaEmitter mEmitter;

    OnPlusplusClickListener(YanchaEmitter emitter, ChatMessage item) {
        mEmitter = emitter;
        mMessageId = item.getId();
    }

    @Override
    public void onClick(View view) {
        new AsyncRunnableTask().execute(sendPlusplus());
    }

    private Runnable sendPlusplus() {
        return new Runnable() {
            @Override
            public void run() {
                mEmitter.emitPlusplus(mMessageId);
            }
        };
    }
}