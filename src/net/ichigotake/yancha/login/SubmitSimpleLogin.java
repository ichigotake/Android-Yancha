package net.ichigotake.yancha.login;

import net.ichigotake.colorfulsweets.lib.net.http.HttpAccessEventListener;
import net.ichigotake.colorfulsweets.lib.net.http.HttpAccessResponse;
import net.ichigotake.colorfulsweets.lib.ui.dialog.LoadingProgressDialogListener;
import net.ichigotake.colorfulsweets.lib.ui.dialog.MessageDialogBuilder;
import net.ichigotake.colorfulsweets.lib.ui.dialog.ShowConnectionErrorDialogListener;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.api.rest.YanchaApiLogin;
import net.ichigotake.yancha.common.context.LoginSession;
import net.ichigotake.yancha.common.user.User;

import org.apache.http.ParseException;

import android.support.v4.app.FragmentActivity;

import com.google.common.base.Optional;
import com.google.common.eventbus.Subscribe;

/**
 * シンプルログインを実行する
 */
class SubmitSimpleLogin {

    final private FragmentActivity mActivity;
    
    final private LoginViewHolder mHolder;
    final private User mUser;
    
    SubmitSimpleLogin(FragmentActivity activity, LoginViewHolder holder) {
        mActivity = activity;
        mHolder = holder;
        mUser = new User(activity);
    }
    
    void send() {
        String nickname = mHolder.getLoginSimple().getText().toString();
        String authority = mHolder.getLoginServer().getText().toString();
        
        mUser.setNickname(nickname);
        mUser.setConnectServerAuthority(authority);
        
        //TODO ���O�C�����s���̕���
        YanchaApiLogin loginApi = new YanchaApiLogin(mUser);
        loginApi.registerListener(new LoadingProgressDialogListener(mActivity));
        loginApi.registerListener(new ShowConnectionErrorDialogListener(mActivity));
        loginApi.registerListener(new SimpleaApiEventListener());
        loginApi.start();
    }
    
    private class SimpleaApiEventListener implements HttpAccessEventListener {
        
        @Subscribe
        public void onSuccess(HttpAccessResponse response) {
            try {
                Optional<String> content = response.getContent();
                if (content.isPresent()) {
                    mUser.setToken(content.get());
                    new LoginSession(mActivity)
                            .login();
                } else {
                    new MessageDialogBuilder(mActivity)
                        .setMessage(R.string.yc_connection_failed)
                        .setDefaultPositiveText()
                        .show();
                }
                
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    
}
