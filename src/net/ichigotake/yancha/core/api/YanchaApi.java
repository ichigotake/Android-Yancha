package net.ichigotake.yancha.core.api;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;

/**
 * yanchaのAPIへアクセスするクラス
 */
public class YanchaApi {

	final public static String SERVER_SCHEME = "http";
	final public static String SERVER_FQDN = "xrly.net:3333";
	//final public static String SERVER_FQDN = "yancha.hachiojipm.org";
	final public static String SERVER_URL = SERVER_SCHEME + "://" + SERVER_FQDN;
	
	public YanchaApi() {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
	}
	
	/**
	 * Return initialized API-builder
	 * 
	 * @param endpoint
	 * @return builder
	 */
	public Uri.Builder buildEndpoint(String endpoint) {
		return new Uri.Builder()
			.scheme(SERVER_SCHEME)
			.encodedAuthority(SERVER_FQDN)
			.path(endpoint)
			;
	}
	
	/**
	 * Request to Yancha API with Uri.Builder
	 * 
	 * @param builder
	 * @return response
	 */
	public HttpResponse request(Uri.Builder builder) {
		return request(builder.build().toString());		
	}
	
	/**
	 * Request to Yancha API
	 * 
	 * @param requestUri
	 * @return response
	 */
	public HttpResponse request(String requestUri) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		HttpUriRequest method = new HttpGet(requestUri);
		try {
			response = client.execute(method);
			int status = response.getStatusLine().getStatusCode();
			if (status != HttpStatus.SC_OK) {
				Log.e("ApiLogin", "status: " + status);
				Log.e("", response.getAllHeaders().toString());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return response;
	}
}
