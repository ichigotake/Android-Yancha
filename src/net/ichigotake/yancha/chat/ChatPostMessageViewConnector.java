package net.ichigotake.yancha.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.api.socketio.YanchaEmitter;
import net.ichigotake.yancha.common.message.PostMessageViewCell;
import net.ichigotake.yancha.common.message.PostMessageViewHolder;
import net.ichigotake.yancha.common.ui.MessageViewConnector;
import net.ichigotake.yanchasdk.lib.model.PostMessage;

/**
 * チャットの発言とビューを繫ぐ
 */
public class ChatPostMessageViewConnector implements MessageViewConnector {

    final private YanchaEmitter mEmitter;
    final private PostMessageViewCell mCell;

    public ChatPostMessageViewConnector(Context context, YanchaEmitter emitter) {
        mEmitter = emitter;
        mCell = new PostMessageViewCell(context);
    }

    @Override
    public boolean isEnabled(int position, PostMessage item) {
        return true;
    }

    @Override
    public int getItemPosition(int position) {
        return position;
    }

    @Override
    public View generatView(LayoutInflater inflater, int position, PostMessage message) {
        return inflater.inflate(R.layout.yc_common_message_cell, null);
    }

    @Override
    public void connectView(int position, PostMessageViewHolder holder, PostMessage item) {
        mCell.initializeMessage(holder, item);
        holder.getContentView().setOnClickListener(new OnPlusplusClickListener(mEmitter, item));
    }

}
