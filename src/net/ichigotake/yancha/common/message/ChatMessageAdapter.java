package net.ichigotake.yancha.common.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.ui.MessageViewConnector;
import net.ichigotake.yancha.sdk.model.ChatMessage;

/**
 * 発言のアダプタ
 *
 * TODO 発言の削除があること、順番がID順である事を考えると {@link java.util.LinkedList} がいいのかもしれない
 */
public class ChatMessageAdapter extends ArrayAdapter<ChatMessage> {

    final private MessageViewConnector mConnector;
    final private LayoutInflater mInflater;
    
    public ChatMessageAdapter(Context context, MessageViewConnector connector) {
        super(context, R.layout.yc_common_message_cell);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mConnector = connector;
    }

    @Override
    public ChatMessage getItem(int position) {
        return super.getItem(mConnector.getItemPosition(position));
    }

    @Override
    public boolean isEnabled(int position) {
        return mConnector.isEnabled(position, getItem(position));
    }

    public void update(ChatMessage message) {
        int lastIndex = getCount()-1;
        for (int i=lastIndex; i>=0; i--) {
            ChatMessage _message = getItem(i);
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
        final ChatMessageViewHolder holder;
        final ChatMessage message = getItem(position);

        if (contentView == null) {
            contentView = mConnector.generatView(mInflater, position, message);
            holder = new ChatMessageViewHolder(contentView);
            contentView.setTag(holder);
        } else {
            holder = (ChatMessageViewHolder)contentView.getTag();
        }

        mConnector.connectView(position, holder, message);

        return contentView;
    }


}
