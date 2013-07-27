package net.ichigotake.yancha.ui;

/**
 * 送信メッセージのオブジェクト
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
