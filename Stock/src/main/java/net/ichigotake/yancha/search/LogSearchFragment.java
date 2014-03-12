package net.ichigotake.yancha.search;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import net.ichigotake.colorfulsweets.common.widget.paging.PagingState;
import net.ichigotake.yancha.BaseAutoPagingFragment;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.api.RequestManager;
import net.ichigotake.yancha.common.chat.AppUser;
import net.ichigotake.yancha.common.chat.SearchOptionBuilder;
import net.ichigotake.yancha.sdk.model.ChatMessage;
import net.ichigotake.yancha.sdk.model.ChatMessageFactory;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * 過去ログ検索
 */
public class LogSearchFragment extends BaseAutoPagingFragment {

    private static SearchOptionBuilder sBuilder;

    public static LogSearchFragment newInstance() {
        return new LogSearchFragment();
    }

    private SearchOptionBuilder getSearchOptionBuilder() {
        if (sBuilder == null) {
            sBuilder = new SearchOptionBuilder(new AppUser().getConnectServerAuthority())
                    .setLimit(getPerPage());
        }
        return sBuilder;
    }

    @Override
    protected void onPaging() {
        super.cancelRequest();
        RequestManager.get().add(createRequest(getPagingState()));
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
    private Request createRequest(final PagingState state) {
        final Uri uri = getSearchOptionBuilder()
                .setOffset(state.getOffset())
                .build();
        return new JsonArrayRequest(uri.toString(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            final ArrayAdapter<ChatMessage> adapter = (ArrayAdapter<ChatMessage>)getAdapter();
                            int length = response.length();
                            if (0 == length) {
                                return ;
                            }
                            for (int i=0; i<length; i++) {
                                String string = response.get(i).toString();
                                adapter.add(ChatMessageFactory.create(string));
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            // TODO エラーイベントを投げる
                            e.printStackTrace();
                        }

                        finish();
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        finish();
                    }
                });

    }

}
