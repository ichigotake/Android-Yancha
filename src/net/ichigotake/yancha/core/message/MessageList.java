package net.ichigotake.yancha.core.message;

import net.ichigotake.yanchasdk.lib.model.PostMessageBuilder.PostMessage;
import android.util.SparseArray;

/**
 * 発言を管理するリストクラス
 */
public class MessageList {
	
	/**
	 * 一意なリストのために発言IDをキーとしたSparseArrayを利用
	 */
	private SparseArray<PostMessage> messageList;

	public SparseArray<PostMessage> getMessageList() {
		return messageList;
	}

	public void setMessageList(SparseArray<PostMessage> messageList) {
		this.messageList = messageList;
	}

}
