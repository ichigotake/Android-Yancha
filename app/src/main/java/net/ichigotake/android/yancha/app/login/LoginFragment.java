package net.ichigotake.android.yancha.app.login;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.AsyncHttpResponse;

import net.ichigotake.android.yancha.app.ChatServer;
import net.ichigotake.android.yancha.app.R;
import net.ichigotake.yancha.sdk.api.ApiEndpoint;

public final class LoginFragment extends DialogFragment {

    public static final String FRAGMENT_TAG = "LoginFragment";
    private OnGetTokenListener listener;

    public static void open(FragmentManager fragmentManager) {
        fragmentManager.beginTransaction()
                .add(new LoginFragment(), FRAGMENT_TAG)
                .commitAllowingStateLoss();
    }

    public static void dismiss(FragmentManager fragmentManager) {
        LoginFragment loginFragment = (LoginFragment)fragmentManager
                .findFragmentByTag(LoginFragment.FRAGMENT_TAG);
        if (loginFragment != null) {
            loginFragment.dismiss();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, parent, false);
        view.findViewById(R.id.fragment_login_with_twitter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String requestUrl = ChatServer.getServerHost() + ApiEndpoint.LOGIN_TWITTER
                        + "?callback_url=yancha://login/twitter&token_only=1";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(requestUrl));
                startActivityForResult(intent, 0);
            }
        });
        final EditText nicknameView = (EditText) view.findViewById(R.id.fragment_login_with_nickname_input);
        view.findViewById(R.id.fragment_login_with_nickname).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname = nicknameView.getText().toString();
                String requestUrl = ChatServer.getServerHost() + ApiEndpoint.LOGIN_SIMPLE
                        + "?token_only=1&nick=" + Uri.encode(nickname);
                AsyncHttpClient.getDefaultInstance().executeString(
                        new AsyncHttpGet(requestUrl),
                        new AsyncHttpClient.StringCallback() {
                            @Override
                            public void onCompleted(Exception e, AsyncHttpResponse asyncHttpResponse, String s) {
                                onGetToken(s);
                            }
                        });
            }
        });
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog =  super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
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
