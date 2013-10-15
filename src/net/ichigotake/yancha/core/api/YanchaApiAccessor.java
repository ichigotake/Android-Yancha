package net.ichigotake.yancha.core.api;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

import com.google.common.eventbus.EventBus;

/**
 * yanchaサーバーのAPIアクセッサー
 */
public abstract class YanchaApiAccessor extends AsyncTask<HttpRequestBase, Integer, Long> {

	final private EventBus mEventBus = new EventBus();

	public abstract HttpRequestBase createRequest();
	
	public void registerListener(ApiEventListener listener) {
		mEventBus.register(listener);
	}
	
	public void start() {
		execute(createRequest());
	}
	
	@Override
	protected Long doInBackground(HttpRequestBase... params) {
		for (HttpRequestBase request : params) {
			try {
				HttpClient client = new DefaultHttpClient();
				ApiResponse response = new ApiResponse(client.execute(request));
				if (response.isSuccess()) {
					mEventBus.post(response);
				} else {
					mEventBus.post(new ApiErrorEventListener());
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
				mEventBus.post(new ApiErrorEventListener());
			} catch (IOException e) {
				mEventBus.post(new ApiErrorEventListener());
				e.printStackTrace();
			}
		}
		return null;
	}

}
