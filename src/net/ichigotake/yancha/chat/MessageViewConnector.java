package net.ichigotake.yancha.chat;

import android.view.LayoutInflater;
import android.view.View;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.message.PostMessageViewCell;
import net.ichigotake.yancha.common.message.PostMessageViewHolder;
import net.ichigotake.yancha.common.model.ViewConnector;
import net.ichigotake.yancha.common.model.ViewHolder;
import net.ichigotake.yanchasdk.lib.model.PostMessage;

/**
 * 発言とビューを繫ぐ
 */
public class MessageViewConnector implements ViewConnector<PostMessageViewHolder, PostMessage> {

    @Override
    public View generatView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.yc_common_message_cell, null);
    }

    @Override
    public void connectView(int position, PostMessageViewHolder holder, PostMessage item) {
        PostMessageViewCell.initialize(holder, item);
    }

}
