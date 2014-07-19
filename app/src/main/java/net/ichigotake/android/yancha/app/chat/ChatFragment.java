package net.ichigotake.android.yancha.app.chat;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.AsyncHttpResponse;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

import net.ichigotake.android.common.os.BundleMerger;
import net.ichigotake.android.yancha.app.ChatServer;
import net.ichigotake.android.yancha.app.R;
import net.ichigotake.yancha.sdk.api.ApiEndpoint;
import net.ichigotake.yancha.sdk.chat.ChatMessage;
import net.ichigotake.yancha.sdk.chat.ChatMessageFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public final class ChatFragment extends Fragment {

    private static final String KEY_CHAT_TOKEN = "chat_token";
    private List<ChatMessage> messages = new ArrayList<ChatMessage>();
    private ChatMessageAdapter adapter;
    private SocketIoClient client;
    //TODO: トークンのセッション切れ対応
    private String token;

    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        token = BundleMerger.merge(savedInstanceState).getString(KEY_CHAT_TOKEN, null);
        View view = inflater.inflate(R.layout.fragment_chat, parent, false);
        ListView messagesView = (ListView) view.findViewById(R.id.fragmet_chat_message_list);
        adapter = new ChatMessageAdapter(getActivity(), messages);
        SwingBottomInAnimationAdapter swingAdapter = new SwingBottomInAnimationAdapter(adapter);
        swingAdapter.setAbsListView(messagesView);
        messagesView.setAdapter(swingAdapter);
        return view;
    }

    @Override
    public void onResume() {
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

    void connectSocket(final String token) {
        try {
            client = SocketIoClient.run(ChatServer.getServerHost(), new SocketIoEventListener() {
                @Override
                public void onResponse(SocketIoEvent event, String response) {
                    try {
                        Log.d("SocketIoEventListener", event + " => " + response);
                        switch (event) {
                            case CONNECT:
                                client.emit(SocketIoEvent.TOKEN_LOGIN, token);
                                break;
                            case DISCONNECT:
                                break;
                            case JOIN_TAG:
                                break;
                            case TOKEN_LOGIN:
                                JSONObject json = new JSONObject();
                                json.put("PUBLIC", 0);
                                client.emit(SocketIoEvent.JOIN_TAG, json);
                                break;
                            case USER_MESSAGE:
                                Log.d("ChatLogging", "response: " + response);
                                messages.add(ChatMessageFactory.create(new JSONObject(response)));
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                                break;
                            case UNKNOWN:
                            default:
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (client != null) {
            client.disconnect();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CHAT_TOKEN, token);
    }

}
