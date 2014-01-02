package net.ichigotake.yancha.common.api.socketio;

import com.google.common.eventbus.Subscribe;

import net.ichigotake.yancha.common.message.SendMessage;
import net.ichigotake.yancha.common.message.SendMessageListener;
import net.ichigotake.yancha.sdk.model.ChatTags;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.SocketIO;


/**
 * yanchaサーバーに対してSocketIOのイベントを送る
 */
public class YanchaEmitter implements SendMessageListener {

    private SocketIO chat;
    
    public YanchaEmitter(SocketIO chat) {
        this.chat = chat;
    }
    
    public void emitUserMessage(String message) {
        chat.emit(EmitEvent.USER_MESSAGE.getName(), message);
    }
    
    /**
     * Emit to "token login"
     * 
     * @param token
     */
    public void emitTokenLogin(String token) {
        chat.emit(EmitEvent.TOKEN_LOGIN.getName(), token);
    }
    
    /**
     * Emit to "join tag"
     * 
     * @param tags
     */
    public void emitJoinTag(ChatTags tags) {
        try {
            JSONObject json = new JSONObject();
            for (String tag : tags.toMap().keySet()) {
                json.put(tag, tag);
            }
            chat.emit(EmitEvent.JOIN_TAG.getName(), json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    
    public void emitConnect() {
        chat.emit(EmitEvent.CONNECT.getName(), "");
    }
    
    public void emitDisconnect() {
        chat.emit(EmitEvent.DISCONNECT.getName(), "bye");
    }
    
    @Subscribe
    public void sendMessage(SendMessage message) {
        emitUserMessage(message.getMessage());
    }

    public void emitPlusplus(int messageId) {
        chat.emit(EmitEvent.PLUSPLUS.getName(), String.valueOf(messageId));
    }
}
