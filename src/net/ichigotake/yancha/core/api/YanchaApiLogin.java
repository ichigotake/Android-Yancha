package net.ichigotake.yancha.core.api;

import org.apache.http.client.methods.HttpRequestBase;

/**
 * yanchaのログインAPIアクセッサー
 */
public class YanchaApiLogin extends YanchaApiAccessor {

	final private ApiUriBuilder mBuilder = new ApiUriBuilder();
	
	final private String mNickname;
	
	final private ApiUri mUri;
	
	public YanchaApiLogin(ApiUri uri, String nickname) {
		mNickname = nickname;
		mUri = uri;
	}
	
	@Override
	public HttpRequestBase createRequest() {
		
		mBuilder.setPath(mUri.getSimpleLoginPath())
			.setAuthrity(mUri.getAuthority())
			.setScheme(mUri.getScheme())
			.appendQueryParameter(YanchaApiField.NICK, mNickname)
			.appendQueryParameter(YanchaApiField.TOKEN_ONLU, "1")
			;
		
		return new ApiRequest().createGetRequest(mBuilder.build());
	}

}
