package net.ichigotake.yancha.core.api;

import net.ichigotake.colorfulsweets.lib.net.http.AsyncHttpAccessor;
import net.ichigotake.colorfulsweets.lib.net.http.HttpAccessRequest;
import net.ichigotake.yancha.core.user.User;

import org.apache.http.client.methods.HttpRequestBase;

/**
 * yancha�̃��O�C��API�A�N�Z�b�T�[
 * 
 * TODO: LoaderCallbacks�ւ̒u���������Ƀ��O�C�����[�h�̐؂�ւ�I/F��������
 */
public class YanchaApiLogin extends AsyncHttpAccessor {

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
		return new HttpAccessRequest().createGetRequest(mBuilder.build());
	}

}
