package net.ichigotake.yancha.search;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import net.ichigotake.colorfulsweets.lib.fragment.AutoPagingFragment;
import net.ichigotake.colorfulsweets.lib.net.http.AfterResponseEvent;
import net.ichigotake.colorfulsweets.lib.net.http.AsyncResponseEvent;
import net.ichigotake.colorfulsweets.lib.net.http.AutoPagingJsonArrayRequest;
import net.ichigotake.colorfulsweets.lib.net.http.ResponseListener;
import net.ichigotake.colorfulsweets.lib.widget.paging.PagingState;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.model.SearchOptionBuilder;
import net.ichigotake.yancha.common.user.AppUser;
import net.ichigotake.yancha.sdk.model.ChatMessage;
import net.ichigotake.yancha.sdk.model.ChatMessageFactory;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * 過去ログ検索
 */
public class LogSearchFragment extends AutoPagingFragment {

    private static SearchOptionBuilder sBuilder;

    public static LogSearchFragment newInstance() {
        return new LogSearchFragment();
    }

    private SearchOptionBuilder getSearchOptionBuilder() {
        if (sBuilder == null) {
            sBuilder = new SearchOptionBuilder(new AppUser(getActivity()).getConnectServerAuthority())
                    .setLimit(getPerPage());
        }
        return sBuilder;
    }

    @Override
    protected void onPaging() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(new SearchApiRequest(getPagingState()).createRequest());
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        return inflater.inflate(R.layout.yc_chat_search, null, false);
    }

    @Override
    protected ListView getListView(View view) {
        return (ListView)view.findViewById(R.id.messageList);
    }

    @Override
    protected BaseAdapter createAdapter() {
        return new LogSearchAdapter(getActivity());
    }

    @Override
    protected int getPerPage() {
        return 100;
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
            SearchOptionBuilder.SearchOption option = getSearchOptionBuilder()
                    .setOffset(parameter.getOffset())
                    .build();
            return option.toUri();
        }

        @Override
        protected ResponseListener<JSONArray> createResponse() {
            return new ResponseListener<JSONArray>() {
                @Override
                public void onResponse(AsyncResponseEvent<JSONArray> event) {
                    try {
                        final ArrayAdapter<ChatMessage> adapter = (ArrayAdapter<ChatMessage>)getAdapter();
                        int length = event.getResponse().length();
                        if (0 == length) {
                            return ;
                        }
                        for (int i=0; i<length; i++) {
                            String string = event.getResponse().get(i).toString();
                            adapter.add(new ChatMessageFactory().create(string));
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        // TODO エラーイベントを投げる
                        e.printStackTrace();
                    }

                    finish();
                }

                @Override
                public void onError(VolleyError volleyError) {
                    finish();
                }

                @Override
                public void afterResponse(AfterResponseEvent afterResponseEvent) {
                    finish();
                }
            };
        }

    }

}
