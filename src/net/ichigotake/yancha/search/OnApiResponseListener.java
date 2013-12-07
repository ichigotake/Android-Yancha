package net.ichigotake.yancha.search;

import android.util.Log;
import android.widget.ArrayAdapter;

import net.ichigotake.colorfulsweets.lib.net.http.ResponseListener;
import net.ichigotake.yancha.sdk.model.ChatMessage;
import net.ichigotake.yancha.sdk.model.ChatMessageFactory;

import org.json.JSONArray;
import org.json.JSONException;

class OnApiResponseListener implements ResponseListener<JSONArray> {

    final private ArrayAdapter<ChatMessage> mAdapter;
    
    OnApiResponseListener(ArrayAdapter<ChatMessage> adapter) {
        mAdapter = adapter;
    }
    
    @Override
    public void onResponse(JSONArray response) {
        try {
            int length = response.length();
            if (0 == length) {
                return ;
            }
            for (int i=0; i<length; i++) {
                String string = response.get(i).toString();
                Log.d(getClass().getSimpleName(), "res: " + string);
                mAdapter.add(ChatMessageFactory.create(string));
            }
            mAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            // TODO エラーイベントを投げる
            e.printStackTrace();
        }
    }


}
