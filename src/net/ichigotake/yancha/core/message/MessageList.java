package net.ichigotake.yancha.core.message;

import net.ichigotake.yanchasdk.lib.model.PostMessageBuilder.PostMessage;
import android.util.SparseArray;

/**
 * �������Ǘ����郊�X�g�N���X
 */
public class MessageList {
	
	/**
	 * ��ӂȃ��X�g�̂��߂ɔ���ID���L�[�Ƃ���SparseArray�𗘗p
	 */
	private SparseArray<PostMessage> messageList;

	public SparseArray<PostMessage> getMessageList() {
		return messageList;
	}

	public void setMessageList(SparseArray<PostMessage> messageList) {
		this.messageList = messageList;
	}

}
