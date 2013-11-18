package net.ichigotake.yancha.common.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import net.ichigotake.yancha.R;
import net.ichigotake.yanchasdk.lib.model.PostMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * メッセージ一覧を表示するアダプタ
 */
public class MessageListAdapter extends ArrayAdapter<PostMessage> {

	private LayoutInflater mInflater;
	
	public MessageListAdapter(Context context) {
		this(context, new ArrayList<PostMessage>());
	}
	
	public MessageListAdapter(Context context, List<PostMessage> messageList) {
		super(context, R.layout.yc_common_message_cell, messageList);
		this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		if (contentView == null) {
			contentView = mInflater.inflate(R.layout.yc_common_message_cell, null);
		}
		
		PostMessage message = getItem(position);
		if (message != null) {
            PostMessageViewCell.initialize(contentView, message);
		}
		
		
		return contentView;
	}


}
