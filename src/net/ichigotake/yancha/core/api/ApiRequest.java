package net.ichigotake.yancha.core.api;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;

import android.net.Uri;

/**
 * APIリクエストのオブジェクトを返す
 */
public class ApiRequest {

	public HttpHead createHeadRequest(Uri uri) {
		return new HttpHead(uri.toString());
	}

	public HttpGet createGetRequest(Uri uri) {
		return new HttpGet(uri.toString());
	}

	public HttpPost createPostRequest(Uri uri) {
		return new HttpPost(uri.toString());
	}

	public HttpDelete createDeleteRequest(Uri uri) {
		return new HttpDelete(uri.toString());
	}
}