package net.ichigotake.android.yancha.app.activity.phone;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.AsyncHttpResponse;

import net.ichigotake.android.common.os.BundleMerger;
import net.ichigotake.android.yancha.app.ChatServer;
import net.ichigotake.android.yancha.app.R;
import net.ichigotake.android.yancha.app.chat.SocketIoClient;
import net.ichigotake.android.yancha.app.chat.SocketIoClientActivity;
import net.ichigotake.android.yancha.app.chat.SocketIoClientFragment;
import net.ichigotake.android.yancha.app.chat.SocketIoEvent;
import net.ichigotake.android.yancha.app.chat.SocketIoEventListener;
import net.ichigotake.yancha.sdk.api.ApiEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public final class ChatActivity extends Activity implements SocketIoClientActivity {

    private static final String KEY_CHAT_TOKEN = "chat_token";
    private final List<Fragment> attachedFragmentList = new ArrayList<Fragment>();
    private SocketIoClient socketIoClient;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        token = BundleMerger.merge(savedInstanceState).getString(KEY_CHAT_TOKEN, null);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        attachedFragmentList.add(fragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (token == null) {
            String requestUrl = ChatServer.getServerHost() + ApiEndpoint.LOGIN_SIMPLE
                    + "?token_only=1&nick=taro";
            AsyncHttpClient.getDefaultInstance().executeString(
                    new AsyncHttpGet(requestUrl),
                    new AsyncHttpClient.StringCallback() {
                        @Override
                        public void onCompleted(Exception e, AsyncHttpResponse asyncHttpResponse, String s) {
                            token = s;
                            connectSocket(token);
                        }
                    });
        } else {
            connectSocket(token);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (socketIoClient != null) {
            socketIoClient.disconnect();
        }
    }

    void connectSocket(final String token) {
        this.token = token;
        try {
            socketIoClient = SocketIoClient.run(ChatServer.getServerHost(), new SocketIoEventListener() {
                @Override
                public void onResponse(SocketIoEvent event, String response) {
                    try {
                        switch (event) {
                            case CONNECT:
                                socketIoClient.emit(SocketIoEvent.TOKEN_LOGIN, token);
                                break;
                            case TOKEN_LOGIN:
                                JSONObject json = new JSONObject();
                                json.put("PUBLIC", 0);
                                json.put("KANKORE", 0);
                                json.put("FROMLINGR", 0);
                                socketIoClient.emit(SocketIoEvent.JOIN_TAG, json);
                                break;
                            case DISCONNECT:
                                break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    dispatchSocketIoEvent(event, response);
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CHAT_TOKEN, token);
    }

    @Override
    public SocketIoClient getSocketIoClient() {
        return socketIoClient;
    }

    private void dispatchSocketIoEvent(final SocketIoEvent event, final String response) {
        Log.d("SocketIoEventListener", event + " => " + response);
        for (final Fragment fragment : attachedFragmentList) {
            if (fragment.isResumed() && fragment instanceof SocketIoClientFragment) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ((SocketIoClientFragment) fragment).onSocketIoEvent(event, response);
                    }
                });
            }
        }
    }

}
