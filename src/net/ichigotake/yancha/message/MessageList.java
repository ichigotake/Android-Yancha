package net.ichigotake.yancha.message;

import android.util.SparseArray;

public class MessageList {
	
	private SparseArray<MessageCell> messageList;

	public SparseArray<MessageCell> getMessageList() {
		return messageList;
	}

	public void setMessageList(SparseArray<MessageCell> messageList) {
		this.messageList = messageList;
	}

}
