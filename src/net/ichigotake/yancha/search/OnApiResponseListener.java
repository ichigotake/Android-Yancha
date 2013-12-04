package net.ichigotake.yancha.search;

import android.util.Log;
import android.widget.ArrayAdapter;

import net.ichigotake.colorfulsweets.lib.net.http.ResponseListener;
import net.ichigotake.yanchasdk.lib.model.PostMessage;
import net.ichigotake.yanchasdk.lib.model.PostMessageFactory;

import org.json.JSONArray;
import org.json.JSONException;

class OnApiResponseListener implements ResponseListener<JSONArray> {

    final private ArrayAdapter<PostMessage> mAdapter;
    
    OnApiResponseListener(ArrayAdapter<PostMessage> adapter) {
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
                mAdapter.add(PostMessageFactory.create(string));
            }
            mAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            // TODO エラーイベントを投げる
            e.printStackTrace();
        }
    }


}
