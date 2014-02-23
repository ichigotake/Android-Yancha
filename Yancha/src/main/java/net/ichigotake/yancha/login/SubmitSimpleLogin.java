package net.ichigotake.yancha.login;

import android.app.Activity;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import net.ichigotake.colorfulsweets.common.app.dialog.MessageDialogBuilder;
import net.ichigotake.colorfulsweets.common.net.UriBuilder;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.api.rest.ApiUri;
import net.ichigotake.yancha.common.api.rest.YanchaApiField;
import net.ichigotake.yancha.common.chat.LoginSession;
import net.ichigotake.yancha.common.chat.AppUser;

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
        mAppUser = new AppUser();
        mQueue = Volley.newRequestQueue(activity);
    }
    
    void send() {
        String nickname = mHolder.getLoginSimple().getText().toString();
        String authority = mHolder.getLoginServer().getText().toString();
        
        mAppUser.setNickname(nickname);
        mAppUser.setConnectServer(authority);
        
        mQueue.add(createRequest());
    }
    
    private Request createRequest() {
        final ApiUri api = mAppUser.getApiUri();
        final String url = new UriBuilder()
                .setScheme(api.getScheme())
                .setAuthrity(api.getAuthority())
                .setPath(api.getSimpleLoginPath())
                .appendQueryParameter(YanchaApiField.NICK, mAppUser.getNickname())
                .appendQueryParameter(YanchaApiField.TOKEN_ONLY, "1")
                .build().toString();
        return new StringRequest(url, new Response.Listener<String>() {
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
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }

}
