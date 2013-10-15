package net.ichigotake.yancha.core.api;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.os.Handler;

import com.google.common.eventbus.EventBus;

/**
 * yanchaサーバーのAPIアクセッサー
 */
public abstract class YanchaApiAccessor extends AsyncTask<HttpRequestBase, Integer, Long> {

	final private EventBus mEventBus = new EventBus();
	
	final private Handler mHandler = new Handler();

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
			
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					mEventBus.post(new BeforeApiRequestEvent());
				}
			});
			
			try {
				HttpClient client = new DefaultHttpClient();
				final ApiResponse response = new ApiResponse(client.execute(request));
				if (response.isSuccess()) {
					mHandler.post(new Runnable() {
						
						@Override
						public void run() {
							mEventBus.post(response);
						}
					});
				} else {
					mHandler.post(new Runnable() {
						
						@Override
						public void run() {
							mEventBus.post(new ApiErrorEventListener());
						}
					});
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
				mHandler.post(new Runnable() {
					
					@Override
					public void run() {
						mEventBus.post(new ApiErrorEventListener());
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
				mHandler.post(new Runnable() {
					
					@Override
					public void run() {
						mEventBus.post(new ApiErrorEventListener());
					}
				});
			}
		}
		return null;
	}

}
