package net.ichigotake.yancha.common.api;

import io.socket.SocketIOException;

public interface ChatCallbackListener {

	public void onConnect();
	
	public void onDisconnect();
	
	public void onError(SocketIOException socketIOException);
	
	public void onNicknames(String response);
	
	public void onUserMessage(String response);
	
}
