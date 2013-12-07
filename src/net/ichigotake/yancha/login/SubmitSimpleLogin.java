package net.ichigotake.yancha.login;

import net.ichigotake.colorfulsweets.lib.net.http.HttpAccessEventListener;
import net.ichigotake.colorfulsweets.lib.net.http.HttpAccessResponse;
import net.ichigotake.colorfulsweets.lib.ui.dialog.LoadingProgressDialogListener;
import net.ichigotake.colorfulsweets.lib.ui.dialog.MessageDialogBuilder;
import net.ichigotake.colorfulsweets.lib.ui.dialog.ShowConnectionErrorDialogListener;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.api.rest.YanchaApiLogin;
import net.ichigotake.yancha.common.context.LoginSession;
import net.ichigotake.yancha.common.user.AppUser;

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
    final private AppUser mAppUser;
    
    SubmitSimpleLogin(FragmentActivity activity, LoginViewHolder holder) {
        mActivity = activity;
        mHolder = holder;
        mAppUser = new AppUser(activity);
    }
    
    void send() {
        String nickname = mHolder.getLoginSimple().getText().toString();
        String authority = mHolder.getLoginServer().getText().toString();
        
        mAppUser.setNickname(nickname);
        mAppUser.setConnectServer(authority);
        
        //TODO ���O�C�����s���̕���
        YanchaApiLogin loginApi = new YanchaApiLogin(mAppUser);
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
                    mAppUser.setToken(content.get());
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
