package net.ichigotake.yancha.chat;

import android.view.LayoutInflater;
import android.view.View;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.message.PostMessageViewCell;
import net.ichigotake.yancha.common.message.PostMessageViewHolder;
import net.ichigotake.yancha.common.ui.MessageViewConnector;
import net.ichigotake.yanchasdk.lib.model.PostMessage;

/**
 * 発言とビューを繫ぐ
 */
public class PostMessageViewConnector implements MessageViewConnector {

    @Override
    public View generatView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.yc_common_message_cell, null);
    }

    @Override
    public void connectView(int position, PostMessageViewHolder holder, PostMessage item) {
        PostMessageViewCell.initialize(holder, item);
    }

}
