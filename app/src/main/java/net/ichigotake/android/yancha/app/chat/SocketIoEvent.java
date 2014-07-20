package net.ichigotake.android.yancha.app.chat;

import android.text.TextUtils;

public enum SocketIoEvent {

    ANNOUNCEMENT("announcement"),
    CONNECT("connect"),
    DISCONNECT("disconnect"),
    NO_SESSION("no session"),

    DELETE_USER_MESSAGE("delete user message"),
    JOIN_TAG("join tag"),
    NICKNAMES("nicknames"),
    TOKEN_LOGIN("token login"),
    USER_MESSAGE("user message"),

    UNKNOWN("unknown"),
    ;

    private final String event;

    private SocketIoEvent(String event) {
        this.event = event;
    }

    public String getEventName() {
        return event;
    }

    @Override
    public String toString() {
        return event;
    }

    public static SocketIoEvent dispatch(String rawName) {
        for (SocketIoEvent event : values()) {
            if (TextUtils.equals(event.getEventName(), rawName)) {
                return event;
            }
        }
        return UNKNOWN;
    }

}
