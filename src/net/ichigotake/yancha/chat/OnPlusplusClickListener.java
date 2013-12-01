package net.ichigotake.yancha.chat;

import android.view.View;

import net.ichigotake.yancha.common.api.socketio.YanchaEmitter;
import net.ichigotake.yanchasdk.lib.model.PostMessage;

/**
 * ++をするためのクリックリスナ
 */
class OnPlusplusClickListener implements View.OnClickListener {

    final private int mMessageId;
    final private YanchaEmitter mEmitter;

    OnPlusplusClickListener(YanchaEmitter emitter, PostMessage item) {
        mEmitter = emitter;
        mMessageId = item.getId();
    }

    @Override
    public void onClick(View view) {
            mEmitter.emitPlusplus(mMessageId);
    }
}