package net.ichigotake.yancha.message;

import android.util.SparseArray;

/**
 * �������Ǘ����郊�X�g�N���X
 */
public class MessageList {
	
	/**
	 * ��ӂȃ��X�g�̂��߂ɔ���ID���L�[�Ƃ���SparseArray�𗘗p
	 */
	private SparseArray<MessageCell> messageList;

	public SparseArray<MessageCell> getMessageList() {
		return messageList;
	}

	public void setMessageList(SparseArray<MessageCell> messageList) {
		this.messageList = messageList;
	}

}
