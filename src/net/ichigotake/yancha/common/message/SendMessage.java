package net.ichigotake.yancha.common.message;

/**
 * ���M���b�Z�[�W�̃I�u�W�F�N�g
 */
public class SendMessage {

	final private String message;
	
	public SendMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
