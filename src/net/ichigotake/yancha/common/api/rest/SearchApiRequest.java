package net.ichigotake.yancha.common.api.rest;

import net.ichigotake.colorfulsweets.lib.net.http.AsyncHttpAccessor;
import net.ichigotake.colorfulsweets.lib.net.http.HttpAccessRequest;
import net.ichigotake.yancha.common.model.SearchOptionBuilder.SearchOption;

import org.apache.http.client.methods.HttpRequestBase;

public class SearchApiRequest extends AsyncHttpAccessor {

	final private SearchOption mSearchOption;
	
	public SearchApiRequest(SearchOption option) {
		mSearchOption = option;
	}
	
	@Override
	public HttpRequestBase createRequest() {
		return new HttpAccessRequest().createGetRequest(mSearchOption.toUri());
	}

}
