package net.ichigotake.yancha.common.api;



public class ChatEventDispatcher {

	public void dispatch(String eventName, String response, ChatCallbackListener listener) {
		EmitEvent event = EmitEvent.get(eventName);
		if (null == event) {
			//TODO onError”­‰Î
			return ;
		}
		switch (event) {
		case ANNONCEMENT:
			break;
		case CONNECT:
			listener.onConnect();
			break;
		case CONNECTIONG:
			break;
		case DELETE_USER_MESSAGE:
			break;
		case DISCONNECT:
			listener.onDisconnect();
			break;
		case ERROR:
			break;
		case JOIN_TAG:
			break;
		case NICKNAMES:
			listener.onNicknames(response);
			break;
		case NO_SESSION:
			break;
		case RECONNECT:
			break;
		case RECONNECTING:
			break;
		case TOKEN_LOGIN:
			break;
		case USER_MESSAGE:
			listener.onUserMessage(response);
			break;
		default:
			break;
		
		}
	}

}
