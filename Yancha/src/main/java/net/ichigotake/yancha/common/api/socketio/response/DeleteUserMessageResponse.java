package net.ichigotake.yancha.common.api.socketio.response;

import com.google.common.base.Optional;

/**
 * Created by ichigotake on 2013/11/02.
 */
public class DeleteUserMessageResponse implements EmitEventResponse {

    final private Optional<String> mResponseBody;

    public DeleteUserMessageResponse(Optional<String> response) {
        mResponseBody = response;
    }

    public Optional<String> getResponseBody() {
        return mResponseBody;
    }
}
