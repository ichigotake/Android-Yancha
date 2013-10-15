package net.ichigotake.yancha.common.api;

import java.io.IOException;

import net.ichigotake.yancha.core.api.ApiErrorEventListener;
import net.ichigotake.yancha.core.api.ApiEventListener;
import net.ichigotake.yancha.core.api.ApiResponse;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

import com.google.common.eventbus.EventBus;

public class ApiAsyncTask extends AsyncTask<HttpRequestBase, Integer, Long> {

	final private EventBus mEventBus;

	public ApiAsyncTask() {
		super();
		mEventBus = new EventBus();
	}

	public void registerListener(ApiEventListener listener) {
		mEventBus.register(listener);
	}

	@Override
	protected Long doInBackground(HttpRequestBase... params) {
		HttpClient client = new DefaultHttpClient();
		for (HttpRequestBase request : params) {
			try {
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
