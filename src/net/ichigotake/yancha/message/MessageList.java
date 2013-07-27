package net.ichigotake.yancha.message;

import android.util.SparseArray;

/**
 * 発言を管理するリストクラス
 */
public class MessageList {
	
	/**
	 * 一意なリストのために発言IDをキーとしたSparseArrayを利用
	 */
	private SparseArray<MessageCell> messageList;

	public SparseArray<MessageCell> getMessageList() {
		return messageList;
	}

	public void setMessageList(SparseArray<MessageCell> messageList) {
		this.messageList = messageList;
	}

}
