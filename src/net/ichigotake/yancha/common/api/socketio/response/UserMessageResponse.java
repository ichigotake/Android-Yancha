package net.ichigotake.yancha.common.api.socketio.response;

import com.google.common.base.Optional;

/**
 * Created by ichigotake on 2013/11/02.
 */
public class UserMessageResponse implements EmitEventResponse {

    final private Optional<String> mResponseBody;

    public UserMessageResponse(Optional<String> response) {
        mResponseBody = response;
    }

    public Optional<String> getResponseBody() {
        return mResponseBody;
    }
}