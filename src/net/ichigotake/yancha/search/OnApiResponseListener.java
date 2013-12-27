package net.ichigotake.yancha.search;

import com.android.volley.VolleyError;

import net.ichigotake.colorfulsweets.lib.net.http.AfterResponseEvent;
import net.ichigotake.colorfulsweets.lib.net.http.AsyncResponseEvent;
import net.ichigotake.colorfulsweets.lib.net.http.ResponseListener;
import net.ichigotake.yancha.common.message.ChatMessageAdapter;
import net.ichigotake.yancha.sdk.model.ChatMessageFactory;
import net.ichigotake.yancha.sdk.model.ChatMessages;

import org.json.JSONArray;
import org.json.JSONException;

class OnApiResponseListener implements ResponseListener<JSONArray> {

    final private ChatMessageAdapter mAdapter;
    
    OnApiResponseListener(ChatMessageAdapter adapter) {
        mAdapter = adapter;
    }
    
    @Override
    public void onResponse(AsyncResponseEvent<JSONArray> event) {
        try {
            ChatMessages messages = new ChatMessageFactory().createList(event.getResponse());
            mAdapter.addAll(messages.toList());
            mAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            // TODO エラーイベントを投げる
            e.printStackTrace();
        }
    }

    @Override
    public void onError(VolleyError error) {
        // TODO エラーハンドリング
    }

    @Override
    public void afterResponse(AfterResponseEvent response) {
        // do nothing
    }


}
