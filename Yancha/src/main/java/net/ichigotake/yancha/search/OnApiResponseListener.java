package net.ichigotake.yancha.search;

import android.widget.ArrayAdapter;

import com.android.volley.VolleyError;

import net.ichigotake.colorfulsweets.lib.net.http.AfterResponseEvent;
import net.ichigotake.colorfulsweets.lib.net.http.AsyncResponseEvent;
import net.ichigotake.colorfulsweets.lib.net.http.ResponseListener;
import net.ichigotake.yancha.sdk.model.ChatMessage;
import net.ichigotake.yancha.sdk.model.ChatMessageFactory;

import org.json.JSONArray;
import org.json.JSONException;

class OnApiResponseListener implements ResponseListener<JSONArray> {

    final private ArrayAdapter<ChatMessage> mAdapter;
    final private ChatMessageFactory mFactory;
    
    OnApiResponseListener(ArrayAdapter<ChatMessage> adapter) {
        mAdapter = adapter;
        mFactory = new ChatMessageFactory();
    }
    
    @Override
    public void onResponse(AsyncResponseEvent<JSONArray> event) {
        try {
            int length = event.getResponse().length();
            if (0 == length) {
                return ;
            }
            for (int i=0; i<length; i++) {
                String string = event.getResponse().get(i).toString();
                mAdapter.add(mFactory.create(string));
            }
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
