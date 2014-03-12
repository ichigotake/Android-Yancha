package net.ichigotake.yancha.common.api.rest;


import net.ichigotake.colorfulsweets.common.os.Key;

public enum YanchaApiField implements Key {
    NICK("nick"),
    TOKEN_ONLY("token_only"),
    CALBACK_URL("callback_url"),
    ;

    final private String mKey;

    private YanchaApiField(String key) {
        mKey = key;
    }

    @Override
    public String getKey() {
        return mKey;
    }

}
