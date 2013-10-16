package net.ichigotake.yancha.core.api;

import net.ichigotake.yancha.core.user.User;

import org.apache.http.client.methods.HttpRequestBase;

/**
 * yanchaのログインAPIアクセッサー
 */
public class YanchaApiLogin extends YanchaApiAccessor {

	final private ApiUriBuilder mBuilder = new ApiUriBuilder();
	
	final private User mUser;
	
	public YanchaApiLogin(User user) {
		mUser = user;
	}
	
	@Override
	public HttpRequestBase createRequest() {
		ApiUri uri = mUser.getApiUri();
		mBuilder.setPath(uri.getSimpleLoginPath())
			.setAuthrity(mUser.getConnectServerAuthority())
			.setScheme(uri.getScheme())
			.appendQueryParameter(YanchaApiField.NICK, mUser.getNickname())
			.appendQueryParameter(YanchaApiField.TOKEN_ONLU, "1")
			;
		return new ApiRequest().createGetRequest(mBuilder.build());
	}

}
