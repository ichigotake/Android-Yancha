package net.ichigotake.yancha.login;

import android.app.Activity;
import android.net.Uri;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import net.ichigotake.colorfulsweets.lib.net.UriBuilder;
import net.ichigotake.colorfulsweets.lib.net.http.AsyncStringRequest;
import net.ichigotake.colorfulsweets.lib.net.http.ResponseListener;
import net.ichigotake.colorfulsweets.lib.ui.dialog.LoadingProgressDialogListener;
import net.ichigotake.colorfulsweets.lib.ui.dialog.MessageDialogBuilder;
import net.ichigotake.colorfulsweets.lib.ui.dialog.ShowConnectionErrorDialogListener;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.api.rest.ApiUri;
import net.ichigotake.yancha.common.api.rest.YanchaApiField;
import net.ichigotake.yancha.common.context.LoginSession;
import net.ichigotake.yancha.common.user.AppUser;

/**
 * シンプルログインを実行する
 */
class SubmitSimpleLogin {

    final private Activity mActivity;
    
    final private LoginViewHolder mHolder;
    final private AppUser mAppUser;
    final private RequestQueue mQueue;

    SubmitSimpleLogin(Activity activity, LoginViewHolder holder) {
        mActivity = activity;
        mHolder = holder;
        mAppUser = new AppUser(activity);
        mQueue = Volley.newRequestQueue(activity);
    }
    
    void send() {
        String nickname = mHolder.getLoginSimple().getText().toString();
        String authority = mHolder.getLoginServer().getText().toString();
        
        mAppUser.setNickname(nickname);
        mAppUser.setConnectServer(authority);
        
        SimpleLoginApiRequest api = new SimpleLoginApiRequest(mAppUser);
        api.registerListener(new LoadingProgressDialogListener(mActivity));
        api.registerListener(new ShowConnectionErrorDialogListener(mActivity));
        api.registerListener(new SimpleaApiEventListener());
        mQueue.add(api.createRequest());
    }
    
    private class SimpleaApiEventListener implements ResponseListener<String> {

        @Override
        public void onResponse(String response) {
            if (! TextUtils.isEmpty(response)) {
                mAppUser.setToken(response);
                new LoginSession(mActivity)
                        .login();
            } else {
                new MessageDialogBuilder(mActivity)
                        .setMessage(R.string.yc_connection_failed)
                        .setDefaultPositiveText()
                        .show();
            }
        }

        @Override
        public void onError(VolleyError error) {
            // TODO エラーハンドリング
        }
    }

    private class SimpleLoginApiRequest extends AsyncStringRequest {

        final private AppUser mUser;

        SimpleLoginApiRequest(AppUser uesr) {
            mUser = uesr;
        }

        @Override
        protected Uri getRequestUri() {
            ApiUri api = mUser.getApiUri();
            return new UriBuilder()
                    .setScheme(api.getScheme())
                    .setAuthrity(api.getAuthority())
                    .setPath(api.getSimpleLoginPath())
                    .appendQueryParameter(YanchaApiField.NICK, mUser.getNickname())
                    .appendQueryParameter(YanchaApiField.TOKEN_ONLY, "1")
                    .build();
        }

        @Override
        protected int getMethod() {
            return Request.Method.GET;
        }

        @Override
        protected ResponseListener<String> createResponse() {
            return new SimpleaApiEventListener();
        }

    }

}
