package net.ichigotake.yancha.common.api.rest;

import net.ichigotake.colorfulsweets.lib.net.UriBuilder;
import net.ichigotake.colorfulsweets.lib.net.http.AsyncHttpAccessor;
import net.ichigotake.colorfulsweets.lib.net.http.HttpAccessRequest;
import net.ichigotake.yancha.common.user.User;

import org.apache.http.client.methods.HttpRequestBase;

/**
 * yanchaのログインAPIアクセッサー
 */
public class YanchaApiLogin extends AsyncHttpAccessor {

	final private UriBuilder mBuilder = new UriBuilder();
	
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
			.appendQueryParameter(YanchaApiField.TOKEN_ONLY, "1")
			;
		return new HttpAccessRequest().createGetRequest(mBuilder.build());
	}

}
