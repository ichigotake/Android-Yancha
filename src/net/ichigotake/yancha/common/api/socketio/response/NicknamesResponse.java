package net.ichigotake.yancha.common.api.socketio.response;

import com.google.common.base.Optional;

public class NicknamesResponse implements EmitEventResponse {

    final private Optional<String> mResponseBody;

    public NicknamesResponse(Optional<String> response) {
        mResponseBody = response;
    }

    public Optional<String> getResponseBody() {
        return mResponseBody;
    }

}
