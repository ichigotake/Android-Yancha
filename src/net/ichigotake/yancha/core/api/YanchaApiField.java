package net.ichigotake.yancha.core.api;

public enum YanchaApiField {
	NICK("nick"),
	TOKEN_ONLU("token_only"),
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
