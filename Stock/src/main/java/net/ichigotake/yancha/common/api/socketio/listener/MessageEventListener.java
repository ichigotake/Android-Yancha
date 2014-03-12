package net.ichigotake.yancha.common.api.socketio.listener;

import net.ichigotake.yancha.common.api.EmptyResponseException;
import net.ichigotake.yancha.common.api.socketio.response.AnnounsementResponse;
import net.ichigotake.yancha.common.api.socketio.response.DeleteUserMessageResponse;
import net.ichigotake.yancha.common.api.socketio.response.UserMessageResponse;

public interface MessageEventListener extends EmitEventListener {

    public void onEvent(AnnounsementResponse response);

    public void onEvent(DeleteUserMessageResponse response) throws EmptyResponseException;

    public void onEvent(UserMessageResponse response) throws EmptyResponseException;

}
