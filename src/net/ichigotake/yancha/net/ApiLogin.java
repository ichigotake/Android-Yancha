package net.ichigotake.yancha.net;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;

public class ApiLogin {

	public static String simpleLogin(String nickname) {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
		//TODO: APIリクエストの基幹処理を別クラスへ
		Uri.Builder builder = new Uri.Builder()
			.scheme("http")
			.encodedAuthority("xrly.net:3333")
			.path("/login")
			.appendQueryParameter("nick", nickname)
			.appendQueryParameter("token_only", "1")
			;
		Log.d("ApiLogin", builder.build().toString());
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		HttpUriRequest method = new HttpGet(builder.build().toString());
		try {
			response = client.execute(method);
			int status = response.getStatusLine().getStatusCode();
			if (status != HttpStatus.SC_OK) {
				Log.d("ApiLogin", "status: " + status);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
