package net.ichigotake.yancha.core.api;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ParseException;

import com.google.common.base.Optional;

/**
 * APIƒŒƒXƒ|ƒ“ƒX
 */
public class ApiResponse {

	final private Optional<JSONObject> mJson;

	final private HttpResponse mResponse;

	final private int mStatusCode;
	
	final private Optional<String> mContent;

	public ApiResponse(HttpResponse response) {
		mResponse = response;
		mContent = parseContent(mResponse);
		mJson = parseJson(mContent);
		mStatusCode = mResponse.getStatusLine().getStatusCode();
	}

	public boolean isSuccess() {
		return HttpURLConnection.HTTP_OK == mStatusCode;
	}

	public Optional<JSONObject> getJSONObject() {
		return mJson;
	}

	public HttpResponse getResponse() {
		return mResponse;
	}
	
	public Optional<String> getContent() {
		return mContent;
	}
	
	private Optional<String> parseContent(HttpResponse response) {
		Optional<String> content;
		try {
			content = Optional.of(EntityUtils.toString(response.getEntity(), "UTF-8"));
		} catch (org.apache.http.ParseException e) {
			content = Optional.absent();
			e.printStackTrace();
		} catch (IOException e) {
			content = Optional.absent();
			e.printStackTrace();
		}
		return content;
	}
	
	private Optional<JSONObject> parseJson(Optional<String> content) {
		try {
			if (content.isPresent()) {
				return Optional.of(new JSONObject(content.get()));
			} else {
				return Optional.absent();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Optional.absent();
	}
}