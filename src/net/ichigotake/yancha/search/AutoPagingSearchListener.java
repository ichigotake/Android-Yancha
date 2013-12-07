package net.ichigotake.yancha.search;

import android.content.Context;
import android.net.Uri;
import android.widget.ArrayAdapter;

import com.android.volley.VolleyError;

import net.ichigotake.colorfulsweets.lib.model.PagingParameter;
import net.ichigotake.colorfulsweets.lib.net.http.AsyncRequest;
import net.ichigotake.colorfulsweets.lib.net.http.AutoPagingJsonArrayRequest;
import net.ichigotake.colorfulsweets.lib.net.http.AutoPagingListener;
import net.ichigotake.colorfulsweets.lib.net.http.ResponseErrorListener;
import net.ichigotake.colorfulsweets.lib.net.http.ResponseListener;
import net.ichigotake.yancha.common.api.rest.ApiUri;
import net.ichigotake.yancha.common.message.ChatMessageAdapter;
import net.ichigotake.yancha.common.model.SearchOptionBuilder;
import net.ichigotake.yancha.sdk.model.ChatMessage;

import org.json.JSONArray;

/**
 * オートページングで検索する
 */
class AutoPagingSearchListener extends AutoPagingListener<ChatMessage, JSONArray> {

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
    protected AsyncRequest<JSONArray> createRequest(PagingParameter parameter) {
        return new SearchApiRequest(parameter);
    }

    /**
     * 検索APIを叩く
     */
    private class SearchApiRequest extends AutoPagingJsonArrayRequest {

        public SearchApiRequest(PagingParameter parameter) {
            super(parameter);
        }

        @Override
        protected Uri getRequestUri(PagingParameter parameter) {
            mBuilder.setOffset(parameter.getOffset());
            return mBuilder.build().toUri();
        }

        @Override
        protected ResponseListener<JSONArray> createResponse() {
            return new OnApiResponseListener(getAdapter());
        }

        @Override
        protected ResponseErrorListener createErrorResponse() {
            return new ResponseErrorListener() {
                @Override
                public void onError(VolleyError error) {
                    // TODO 何か、エラーイベントを
                }
            };
        }
    }

}
