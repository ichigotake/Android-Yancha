package net.ichigotake.yancha.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.message.PostMessageViewCell;
import net.ichigotake.yancha.common.message.PostMessageViewHolder;
import net.ichigotake.yancha.common.ui.MessageViewConnector;
import net.ichigotake.yanchasdk.lib.model.PostMessage;

/**
 * Created by ichigotake on 2013/12/05.
 */
class SearchMessageViewConnector implements MessageViewConnector {

    final private PostMessageViewCell mCell;

    SearchMessageViewConnector(Context context) {
        mCell = new PostMessageViewCell(context);
    }

    @Override
    public boolean isEnabled(int position, PostMessage item) {
        return (position == 0) || (position%50 != 0);
    }

    @Override
    public int getItemPosition(int position) {
        return position - (position/50);
    }

    @Override
    public View generatView(LayoutInflater inflater, int position, PostMessage message) {
        return inflater.inflate(R.layout.yc_common_message_cell, null);
    }

    @Override
    public void connectView(int position, PostMessageViewHolder holder, PostMessage item) {
        if (isEnabled(position, item)) {
            mCell.initializeMessage(holder, item);
        } else {
            mCell.initializeSeparator(holder, position);
        }
    }

}
