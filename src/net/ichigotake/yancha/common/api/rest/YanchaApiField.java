package net.ichigotake.yancha.common.api.rest;

import net.ichigotake.colorfulsweets.lib.net.QueryParameterKey;

public enum YanchaApiField implements QueryParameterKey {
	NICK("nick"),
	TOKEN_ONLY("token_only"),
	CALBACK_URL("callback_url"),
	;

	final private String mKey;

	private YanchaApiField(String key) {
		mKey = key;
	}

	public String getKey() {
		return mKey;
	}

}
