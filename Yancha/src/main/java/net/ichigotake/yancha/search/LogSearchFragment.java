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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import net.ichigotake.colorfulsweets.common.widget.paging.PagingState;
import net.ichigotake.colorfulsweets.ics.fragment.AutoPagingFragment;
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
        queue.add(createRequest(getPagingState()));
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
        SearchOptionBuilder.SearchOption option = getSearchOptionBuilder()
                .setOffset(state.getOffset())
                .build();
        return new JsonArrayRequest(option.toUri().toString(),
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
                                adapter.add(new ChatMessageFactory().create(string));
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
