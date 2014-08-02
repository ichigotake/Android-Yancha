package net.ichigotake.android.yancha.app.login;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.AsyncHttpResponse;

import net.ichigotake.android.common.app.BaseDialogFragment;
import net.ichigotake.android.yancha.app.ChatServer;
import net.ichigotake.android.yancha.app.R;
import net.ichigotake.yancha.sdk.api.ApiEndpoint;

public final class LoginDialogFragment extends BaseDialogFragment {

    public static final String FRAGMENT_TAG = "LoginFragment";
    private OnGetTokenListener listener;

    public static void open(FragmentManager fragmentManager) {
        fragmentManager.beginTransaction()
                .add(new LoginDialogFragment(), FRAGMENT_TAG)
                .commitAllowingStateLoss();
    }

    public static void dismiss(FragmentManager fragmentManager) {
        LoginDialogFragment loginDialogFragment = (LoginDialogFragment)fragmentManager
                .findFragmentByTag(LoginDialogFragment.FRAGMENT_TAG);
        if (loginDialogFragment != null) {
            loginDialogFragment.dismiss();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, parent, false);
        view.findViewById(R.id.fragment_login_with_twitter).setOnClickListener(v -> {
            String requestUrl = ChatServer.getServerHost() + ApiEndpoint.LOGIN_TWITTER
                    + "?callback_url=yancha://login/twitter&token_only=1";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(requestUrl));
            startActivityForResult(intent, 0);
        });
        final EditText nicknameView = (EditText) view.findViewById(R.id.fragment_login_with_nickname_input);
        view.findViewById(R.id.fragment_login_with_nickname).setOnClickListener(v -> {
            String nickname = nicknameView.getText().toString().trim();
            if (TextUtils.isEmpty(nickname)) {
                return;
            }
            String requestUrl = ChatServer.getServerHost() + ApiEndpoint.LOGIN_SIMPLE
                    + "?token_only=1&nick=" + Uri.encode(nickname);
            AsyncHttpClient.getDefaultInstance().executeString(
                    new AsyncHttpGet(requestUrl),
                    new AsyncHttpClient.StringCallback() {
                        @Override
                        public void onCompleted(Exception e, AsyncHttpResponse asyncHttpResponse, String s) {
                            onGetToken(s);
                        }
                    }
            );
        });
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog =  super.onCreateDialog(savedInstanceState);
        dialog.setTitle(R.string.login_welcome);
        setCancelable(false);
        return dialog;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (OnGetTokenListener)activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private void onGetToken(String token) {
        if (listener != null) {
            listener.onTokenResponse(token);
        }
    }
}
