package net.ichigotake.yancha.common.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.chat.PostMessageViewConnector;
import net.ichigotake.yancha.common.ui.MessageViewConnector;
import net.ichigotake.yanchasdk.lib.model.PostMessage;

/**
 * 発言のアダプタ
 *
 * TODO 発言の削除があること、順番がID順である事を考えると {@link java.util.LinkedList} がいいのかもしれない
 */
public class PostMessageAdapter extends ArrayAdapter<PostMessage> {

    final private MessageViewConnector mConnector;
    final private LayoutInflater mInflater;
    
    public PostMessageAdapter(Context context) {
        super(context, R.layout.yc_common_message_cell);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mConnector = new PostMessageViewConnector();
    }

    public PostMessageAdapter(Context context, MessageViewConnector connector) {
        super(context, R.layout.yc_common_message_cell);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mConnector = connector;
    }

    public void update(PostMessage message) {
        int lastIndex = getCount()-1;
        for (int i=lastIndex; i>=0; i--) {
            PostMessage _message = getItem(i);
            if (message.getId() == _message.getId()) {
                remove(_message);
                insert(message, i);
                break;
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(final int position, View contentView, ViewGroup parent) {
        final PostMessageViewHolder holder;
        final PostMessage message = getItem(position);

        if (contentView == null) {
            contentView = mConnector.generatView(mInflater);
            holder = new PostMessageViewHolder(contentView);
            contentView.setTag(holder);
        } else {
            holder = (PostMessageViewHolder)contentView.getTag();
        }

        mConnector.connectView(position, holder, message);

        return contentView;
    }


}
