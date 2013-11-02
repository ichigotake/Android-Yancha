package net.ichigotake.yancha.common.message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jp.sharakova.android.urlimageview.UrlImageView;
import net.ichigotake.yancha.R;
import net.ichigotake.yanchasdk.lib.model.PostMessageBuilder.PostMessage;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
			TextView viewNickname = (TextView) contentView.findViewById(R.id.messageCellNickname);
			viewNickname.setText(message.getNickname());
			
			TextView viewMessage = (TextView) contentView.findViewById(R.id.messageCellMessage);
			viewMessage.setText(message.getMessage());
			
			UrlImageView viewProfileImageUrl = (UrlImageView) contentView.findViewById(R.id.messageCellProfileImageUrl);
			String profileImageUrl = message.getProfileImageUrl();
			if (!profileImageUrl.isEmpty()) {
				viewProfileImageUrl.setImageUrl(profileImageUrl);
			} else {
				viewProfileImageUrl.setImageUrl(message.getDefaultProfileImageUrl());
			}
			
			int plusplus = message.getPlusplus();
			TextView viewPlusplus = (TextView) contentView.findViewById(R.id.messageCellPlusplus);
			if (plusplus > 0) {
				String plusplusText;
				if (plusplus >= 50) {
					plusplusText = "★ x " + plusplus;
				} else {
					StringBuilder builder = new StringBuilder();
					for (int i=0; i<plusplus; i++) {
						builder.append("★");
					}
					plusplusText = builder.toString();
				}
				viewPlusplus.setVisibility(View.VISIBLE);
				viewPlusplus.setText(plusplusText);
			} else {
				viewPlusplus.setVisibility(View.GONE);
			}
			
			TextView viewTimestamp = (TextView) contentView.findViewById(R.id.messageCellTimestamp);
			viewTimestamp.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(message.getCreatedTime()/100));
		}
		
		
		return contentView;
	}

}
