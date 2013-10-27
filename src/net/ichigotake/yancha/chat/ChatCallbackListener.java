package net.ichigotake.yancha.chat;

import io.socket.SocketIOException;
import net.ichigotake.yancha.common.api.YanchaEmitter;

public interface ChatCallbackListener {

	public void onConnect(YanchaEmitter emitter);
	
	public void onDisconnect();
	
	public void onError(SocketIOException socketIOException);
	
	public void onNicknames(YanchaEmitter emitter, String response);
	
	public void onUserMessage(YanchaEmitter emitter, String response);
	
}
