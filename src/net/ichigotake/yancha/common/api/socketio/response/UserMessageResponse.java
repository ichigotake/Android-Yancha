package net.ichigotake.yancha.common.api.socketio.response;

import com.google.common.base.Optional;

public class UserMessageResponse implements EmitEventResponse {

    final private Optional<String> mResponseBody;

    public UserMessageResponse(Optional<String> response) {
        mResponseBody = response;
    }

    public Optional<String> getResponseBody() {
        return mResponseBody;
    }
}
