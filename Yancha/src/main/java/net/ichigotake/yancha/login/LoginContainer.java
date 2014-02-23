package net.ichigotake.yancha.login;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.YanchaApp;
import net.ichigotake.yancha.common.api.rest.ApiUri;
import net.ichigotake.yancha.common.widget.ViewContainer;
import net.ichigotake.yancha.common.chat.AppUser;

/**
 * ログイン画面を表示
 */
class LoginContainer implements ViewContainer {

    final private Activity mActivity;
    
    final private AppUser appUser;
    
    private LoginContainer(Activity activity) {
        this.mActivity = activity;
        this.appUser = new AppUser(activity);
    }
    
    static LoginContainer initialize(Activity activity, View view) {
        final LoginContainer container = new LoginContainer(activity);
        container.initializeView(view);
        return container;
    }
    
    public void initializeView(final View view) {
        final LoginViewHolder holder = new LoginViewHolder(view);
        
        final EditText serverHost = holder.getLoginServer();
        final ApiUri uri = appUser.getApiUri();
        if (uri.isHostnameEmpty()) {
            serverHost.setText(R.string.yc_login_server_default);
        } else {
            serverHost.setText(uri.getHostname());
        }
        
        holder.getLoginSimpleSubmit().setOnClickListener(
                new SimpleLoginOnClickListener(mActivity, holder));
        
        final EditText loginSimple = holder.getLoginSimple();
        loginSimple.setText(appUser.getNickname());
        loginSimple.setSelection(appUser.getNickname().length());
        loginSimple.setOnEditorActionListener(
                new SimpleLoginOnEditorActionListener(mActivity, holder));
        
        final Button loginTwitter = holder.getLoginTwitter();
        loginTwitter.setOnClickListener(
                new TwitterLoginOnClickListener(mActivity, serverHost));
        
        final TextView versionView = holder.getVersionName();
        versionView.setText(YanchaApp.getFullVersionName());
    }
}
