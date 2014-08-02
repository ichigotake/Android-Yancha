package net.ichigotake.android.yancha.app.activity.phone;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.WindowManager;

import net.ichigotake.android.yancha.app.ChatServer;
import net.ichigotake.android.yancha.app.R;
import net.ichigotake.android.yancha.app.chat.ChatMessagesFragment;
import net.ichigotake.android.yancha.app.chat.ChatMessageInputFragment;
import net.ichigotake.android.yancha.app.chat.SocketIoClient;
import net.ichigotake.android.yancha.app.chat.SocketIoClientActivity;
import net.ichigotake.android.yancha.app.chat.SocketIoClientFragment;
import net.ichigotake.android.yancha.app.chat.SocketIoEvent;
import net.ichigotake.android.yancha.app.information.InformationFragmentActionProvider;
import net.ichigotake.android.yancha.app.login.LoginDialogFragment;
import net.ichigotake.android.yancha.app.login.OnGetTokenListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * チャット画面
 *
 *  {@link ChatActivity} で {@link SocketIoClient} を保持する。
 *  {@link Fragment} では {@link SocketIoClientActivity} を利用して {@link SocketIoClient} を受け取る。
 *
 * SocketIO のイベントハンドルの役割
 *
 *  {@link ChatActivity}
 *      主にセッション管理にまつわるイベントを実装する
 *
 *  {@link ChatMessagesFragment}
 *      セッション管理は {@link ChatActivity} へ任せ、発言一覧の表示に関わるイベントを取り扱う
 *
 *  {@link ChatMessageInputFragment}
 *      セッション管理は {@link ChatActivity} へ任せ、テキストの送信に関わるイベントを取り扱う
 *
 *  {@link LoginDialogFragment}
 *      トークン取得処理を担い、成功時に {@link ChatActivity} の持つ {@link OnGetTokenListener} へコールバックする
 */
public final class ChatActivity extends Activity
        implements SocketIoClientActivity, OnGetTokenListener {

    private static final String KEY_PREFERENCE = "ChatActivity";
    private static final String KEY_CHAT_TOKEN = "chat_token";
    private final List<Fragment> attachedFragmentList = new ArrayList<>();
    private SocketIoClient socketIoClient;
    private String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        token = getSharedPreferences(KEY_PREFERENCE, MODE_PRIVATE).getString(KEY_CHAT_TOKEN, "");
        handleUriScheme(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleUriScheme(intent);
    }

    private void handleUriScheme(Intent intent) {
        if (intent == null) {
            return ;
        }
        Uri data = intent.getData();
        if (data == null) {
            return ;
        }
        String token = data.getQueryParameter("token");
        LoginDialogFragment.dismiss(getFragmentManager());
        connectSocket(token);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        attachedFragmentList.add(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_message, menu);
        menu.findItem(R.id.action_information).setActionProvider(
                new InformationFragmentActionProvider(this, getFragmentManager())
        );
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(token)) {
            LoginDialogFragment.open(getFragmentManager());
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
        getSharedPreferences(KEY_PREFERENCE, MODE_PRIVATE)
                .edit()
                .putString(KEY_CHAT_TOKEN, token)
                .apply();
        this.token = token;
        try {
            socketIoClient = SocketIoClient.run(
                    ChatServer.getServerHost(),
                    (SocketIoEvent event, String response) -> {
                        try {
                            switch (event) {
                                case CONNECT:
                                    socketIoClient.emit(SocketIoEvent.TOKEN_LOGIN, token);
                                    break;
                                case TOKEN_LOGIN:
                                    JSONObject json = new JSONObject();
                                    json.put("PUBLIC", 0);
                                    json.put("FROMLINGR", 0);
                                    socketIoClient.emit(SocketIoEvent.JOIN_TAG, json);
                                    break;
                                case NO_SESSION:
                                    LoginDialogFragment.open(getFragmentManager());
                                    break;
                                case DISCONNECT:
                                    break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dispatchSocketIoEvent(event, response);
                    });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SocketIoClient getSocketIoClient() {
        return socketIoClient;
    }

    private void dispatchSocketIoEvent(final SocketIoEvent event, final String response) {
        Log.d("ChatActivity", event + " => " + response);
        for (final Fragment fragment : attachedFragmentList) {
            if (fragment.isResumed() && fragment instanceof SocketIoClientFragment) {
                runOnUiThread(() -> ((SocketIoClientFragment) fragment).onSocketIoEvent(event, response));
            }
        }
    }

    @Override
    public void onTokenResponse(String token) {
        LoginDialogFragment.dismiss(getFragmentManager());
        this.token = token;
        connectSocket(token);
    }
}
