package net.ichigotake.yancha.search;

import android.content.Context;
import android.net.Uri;
import android.widget.ArrayAdapter;

import net.ichigotake.colorfulsweets.lib.model.PagingState;
import net.ichigotake.colorfulsweets.lib.net.http.AsyncRequest;
import net.ichigotake.colorfulsweets.lib.net.http.AutoPagingJsonArrayRequest;
import net.ichigotake.colorfulsweets.lib.net.http.AutoPagingRequestListener;
import net.ichigotake.colorfulsweets.lib.net.http.ResponseListener;
import net.ichigotake.yancha.common.api.rest.ApiUri;
import net.ichigotake.yancha.common.message.ChatMessageAdapter;
import net.ichigotake.yancha.common.model.SearchOptionBuilder;
import net.ichigotake.yancha.sdk.model.ChatMessage;

import org.json.JSONArray;

/**
 * オートページングで検索する
 */
class AutoPagingSearchListener extends AutoPagingRequestListener<ChatMessage, JSONArray> {

    final private SearchOptionBuilder mBuilder;
    final private ChatMessageAdapter mAdapter;
    
    public AutoPagingSearchListener(Context context, ApiUri uri) {
        super(context);
        mBuilder = new SearchOptionBuilder(uri.getAuthority())
            .setLimit(getPerPage());

        SearchMessageViewConnector connector = new SearchMessageViewConnector(context);
        mAdapter = new ChatMessageAdapter(context, connector);
    }

    @Override
    protected int getPerPage() {
        return 100;
    }

    @Override
    protected ArrayAdapter<ChatMessage> getAdapter() {
        return mAdapter;
    }

    @Override
    protected AsyncRequest<JSONArray> createRequest(PagingState parameter) {
        return new SearchApiRequest(parameter);
    }

    /**
     * 検索APIを叩く
     */
    private class SearchApiRequest extends AutoPagingJsonArrayRequest {

        public SearchApiRequest(PagingState parameter) {
            super(parameter);
        }

        @Override
        protected Uri getRequestUri(PagingState parameter) {
            mBuilder.setOffset(parameter.getOffset());
            return mBuilder.build().toUri();
        }

        @Override
        protected ResponseListener<JSONArray> createResponse() {
            return new OnApiResponseListener(getAdapter());
        }

    }

}
