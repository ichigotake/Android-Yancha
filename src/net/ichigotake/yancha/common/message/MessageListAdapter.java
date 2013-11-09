package net.ichigotake.yancha.common.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import net.ichigotake.yancha.R;
import net.ichigotake.yanchasdk.lib.model.PostMessageBuilder.PostMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * メッセージ一覧を表示するアダプタ
 */
public class MessageListAdapter extends ArrayAdapter<PostMessage> {

	private LayoutInflater inflater;
	
	private List<PostMessage> messageList;
	
	public MessageListAdapter(Context context) {
		this(context, new ArrayList<PostMessage>());
	}
	
	public MessageListAdapter(Context context, List<PostMessage> messageList) {
		super(context, R.layout.yc_common_message_cell, messageList);
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.messageList = messageList;
	}
	
	@Override
	public PostMessage getItem(int position) {
		return messageList.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(final int position, View contentView, ViewGroup parent) {
		if (contentView == null) {
			contentView = inflater.inflate(R.layout.yc_common_message_cell, null);
		}
		
		PostMessage message = getItem(position);
		if (message != null) {
            PostMessageViewCell.initialize(contentView, message);
		}
		
		
		return contentView;
	}


}
