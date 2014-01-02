package net.ichigotake.yancha.common.api.socketio.response;

import com.google.common.base.Optional;

public class TokenLoginResponse implements EmitEventResponse {

    final private Optional<String> mResponseBody;

    public TokenLoginResponse(Optional<String> response) {
        mResponseBody = response;
    }

    public Optional<String> getResponseBody() {
        return mResponseBody;
    }

}
