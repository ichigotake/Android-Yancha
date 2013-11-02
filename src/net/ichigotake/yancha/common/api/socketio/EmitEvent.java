package net.ichigotake.yancha.common.api.socketio;

public enum EmitEvent {

    ANNONCEMENT("announcement"),
    CONNECT("connect"),
    CONNECTIONG("connecting"),
    DISCONNECT("disconnect"),
    DELETE_USER_MESSAGE("delete_user_message"),
    ERROR("error"),
    JOIN_TAG("join tag"),
    NICKNAMES("nicknames"),
    NO_SESSION("no session"),
    RECONNECT("reconnect"),
    RECONNECTING("reconnecting"),
    TOKEN_LOGIN("token login"),
    USER_MESSAGE("user message"),
    UNKNOWN("unknown"),
    ;

    final private String mName;

    private EmitEvent(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public static EmitEvent get(String eventName) {
        for (EmitEvent event : values()) {
            if (event.getName().equals(eventName)) {
                return event;
            }
        }
        return EmitEvent.UNKNOWN;
    }
}
