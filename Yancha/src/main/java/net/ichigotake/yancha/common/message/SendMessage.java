package net.ichigotake.yancha.common.message;

/**
 * 送信するメッセージを保持する
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
