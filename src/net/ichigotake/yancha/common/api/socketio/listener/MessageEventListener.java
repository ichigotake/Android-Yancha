package net.ichigotake.yancha.common.api.socketio.listener;

import net.ichigotake.yancha.common.api.EmptyResponseException;
import net.ichigotake.yancha.common.api.socketio.response.AnnounsementResponse;
import net.ichigotake.yancha.common.api.socketio.response.DeleteUserMessageResponse;
import net.ichigotake.yancha.common.api.socketio.response.UserMessageResponse;

/**
 * Created by ichigotake on 2013/11/02.
 */
public interface MessageEventListener extends EmitEventListener {

    public void onAnnouncement(AnnounsementResponse response);

    public void onDeleteUserMessage(DeleteUserMessageResponse response) throws EmptyResponseException;

    public void onUserMessage(UserMessageResponse response) throws EmptyResponseException;

}
