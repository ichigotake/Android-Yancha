package net.ichigotake.yancha.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.api.socketio.YanchaEmitter;
import net.ichigotake.yancha.common.message.ChatMessageViewCell;
import net.ichigotake.yancha.common.message.ChatMessageViewHolder;
import net.ichigotake.yancha.common.ui.MessageViewConnector;
import net.ichigotake.yancha.sdk.model.ChatMessage;

/**
 * チャットの発言とビューを繫ぐ
 */
public class ChatPostMessageViewConnector implements MessageViewConnector {

    final private YanchaEmitter mEmitter;
    final private ChatMessageViewCell mCell;

    public ChatPostMessageViewConnector(Context context, YanchaEmitter emitter) {
        mEmitter = emitter;
        mCell = new ChatMessageViewCell(context);
    }

    @Override
    public boolean isEnabled(int position, ChatMessage item) {
        return true;
    }

    @Override
    public int getItemPosition(int position) {
        return position;
    }

    @Override
    public View generateView(LayoutInflater inflater, int position, ChatMessage message) {
        return inflater.inflate(R.layout.yc_common_message_cell, null);
    }

    @Override
    public void connectView(int position, ChatMessageViewHolder holder, ChatMessage item) {
        mCell.initializeMessage(holder, item);
        holder.getContentView().setOnClickListener(new OnPlusplusClickListener(mEmitter, item));
    }

}
