package net.ichigotake.yancha.core.api;

public enum YanchaApiField {
	NICK("nick"),
	TOKEN_ONLU("token_only"),
	;

	final private String mKey;

	private YanchaApiField(String key) {
		mKey = key;
	}

	public String getKey() {
		return mKey;
	}

}
