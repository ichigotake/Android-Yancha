package net.ichigotake.yancha.common.api.socketio;

import android.util.Log;

import com.google.common.base.Optional;
import com.google.common.eventbus.EventBus;

import net.ichigotake.yancha.common.api.socketio.listener.EmitEventListener;
import net.ichigotake.yancha.common.api.socketio.response.AnnounsementResponse;
import net.ichigotake.yancha.common.api.socketio.response.ConnectResponse;
import net.ichigotake.yancha.common.api.socketio.response.ConnectingResponse;
import net.ichigotake.yancha.common.api.socketio.response.DeleteUserMessageResponse;
import net.ichigotake.yancha.common.api.socketio.response.DisconnectResponse;
import net.ichigotake.yancha.common.api.socketio.response.ErrorResponse;
import net.ichigotake.yancha.common.api.socketio.response.JoinTagResponse;
import net.ichigotake.yancha.common.api.socketio.response.NicknamesResponse;
import net.ichigotake.yancha.common.api.socketio.response.NoSessionResponse;
import net.ichigotake.yancha.common.api.socketio.response.ReconnectResponse;
import net.ichigotake.yancha.common.api.socketio.response.ReconnectingResponse;
import net.ichigotake.yancha.common.api.socketio.response.TokenLoginResponse;
import net.ichigotake.yancha.common.api.socketio.response.UnknownResponse;
import net.ichigotake.yancha.common.api.socketio.response.UserMessageResponse;

/**
 * Created by ichigotake on 2013/11/02.
 */
public class EmitEventDispatcher {

    final private EventBus mEventBus = new EventBus();

    public void registerListener(EmitEventListener listener) {
        mEventBus.register(listener);
    }

    public void dispatch(String eventName) {
        dispatch(eventName, null);
    }

    public void dispatch(String eventName, String response) {
        dispatch(EmitEvent.get(eventName), Optional.fromNullable(response));
    }

    public void dispatch(EmitEvent event) {
        dispatch(event, Optional.<String>absent());
    }

    public void dispatch(EmitEvent event, Optional<String> response) {
        switch (event) {

            case ANNONCEMENT:
                mEventBus.post(new AnnounsementResponse());
                break;
            case CONNECT:
                mEventBus.post(new ConnectResponse());
                break;
            case CONNECTIONG:
                mEventBus.post(new ConnectingResponse());
                break;
            case DISCONNECT:
                mEventBus.post(new DisconnectResponse());
                break;
            case DELETE_USER_MESSAGE:
                mEventBus.post(new DeleteUserMessageResponse(response));
                break;
            case ERROR:
                mEventBus.post(new ErrorResponse());
                break;
            case JOIN_TAG:
                mEventBus.post(new JoinTagResponse());
                break;
            case NICKNAMES:
                mEventBus.post(new NicknamesResponse(response));
                break;
            case NO_SESSION:
                mEventBus.post(new NoSessionResponse());
                break;
            case RECONNECT:
                mEventBus.post(new ReconnectResponse());
                break;
            case RECONNECTING:
                mEventBus.post(new ReconnectingResponse());
                break;
            case TOKEN_LOGIN:
                mEventBus.post(new TokenLoginResponse());
                break;
            case USER_MESSAGE:
                mEventBus.post(new UserMessageResponse(response));
                break;
            case UNKNOWN:
                mEventBus.post(new UnknownResponse());
                break;
        }
    }
}
