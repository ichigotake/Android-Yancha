package net.ichigotake.yancha.common.message;

import android.util.SparseArray;

import net.ichigotake.yanchasdk.lib.model.PostMessage;

/**
 * メッセージ一覧
 */
public class MessageList {
	
	/**
     * TODO 一意なメッセージ一覧を保持する方法を考える
	 */
	private SparseArray<PostMessage> messageList;

	public SparseArray<PostMessage> getMessageList() {
		return messageList;
	}

	public void setMessageList(SparseArray<PostMessage> messageList) {
		this.messageList = messageList;
	}

}
