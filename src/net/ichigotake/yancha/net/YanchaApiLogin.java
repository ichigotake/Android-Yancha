package net.ichigotake.yancha.net;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import android.net.Uri;

/**
 * Login API accessor
 */
public class YanchaApiLogin {

	/**
	 * Get token for login
	 * 
	 * @param nickname
	 * @return token for session
	 */
	public static String simpleLogin(String nickname) {
		YanchaApi api = new YanchaApi();
		Uri.Builder builder = api.buildEndpoint("/login")
			.appendQueryParameter("nick", nickname)
			.appendQueryParameter("token_only", "1")
			;

		HttpResponse response = api.request(builder);

		try {
			return EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}

}
