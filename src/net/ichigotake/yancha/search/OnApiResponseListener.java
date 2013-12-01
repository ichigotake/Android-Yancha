package net.ichigotake.yancha.search;

import net.ichigotake.colorfulsweets.lib.net.http.HttpAccessEventListener;
import net.ichigotake.colorfulsweets.lib.net.http.HttpAccessResponse;
import net.ichigotake.yanchasdk.lib.model.PostMessage;
import net.ichigotake.yanchasdk.lib.model.PostMessageFactory;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.common.base.Optional;
import com.google.common.eventbus.Subscribe;

class OnApiResponseListener implements HttpAccessEventListener {

    final private ArrayAdapter<PostMessage> mAdapter;
    
    OnApiResponseListener(ArrayAdapter<PostMessage> adapter) {
        mAdapter = adapter;
    }
    
    @Subscribe
    public void onSuccess(HttpAccessResponse response) {
        Optional<String> json = response.getContent();
        if (json.isPresent()) {
            try {
                JSONArray jsonArray = new JSONArray(json.get());
                int length = jsonArray.length();
                if (0 == length) {
                    return ;
                }
                for (int i=0; i<length; i++) {
                    String string = jsonArray.get(i).toString();
                    Log.d(getClass().getSimpleName(), "res: " + string);
                    mAdapter.add(PostMessageFactory.create(string));
                }
                mAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                // TODO エラーイベントを投げる
                e.printStackTrace();
            }
        } else {
            // TODO エラーイベントを投げる
        }
    }
}
