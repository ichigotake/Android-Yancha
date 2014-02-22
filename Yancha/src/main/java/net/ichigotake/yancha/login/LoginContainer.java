package net.ichigotake.yancha.login;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.api.rest.ApiUri;
import net.ichigotake.yancha.common.context.AppContext;
import net.ichigotake.yancha.common.ui.ViewContainer;
import net.ichigotake.yancha.common.user.AppUser;

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
        LoginContainer container = new LoginContainer(activity);
        container.initializeView(view);
        return container;
    }
    
    public void initializeView(View view) {
        LoginViewHolder holder = new LoginViewHolder(view);
        
        EditText serverHost = holder.getLoginServer();
        ApiUri uri = appUser.getApiUri();
        if (uri.isHostnameEmpty()) {
            serverHost.setText(R.string.yc_login_server_default);
        } else {
            serverHost.setText(uri.getHostname());
        }
        
        holder.getLoginSimpleSubmit().setOnClickListener(
                new SimpleLoginOnClickListener(mActivity, holder));
        
        EditText loginSimple = holder.getLoginSimple();
        loginSimple.setText(appUser.getNickname());
        loginSimple.setSelection(appUser.getNickname().length());
        loginSimple.setOnEditorActionListener(
                new SimpleLoginOnEditorActionListener(mActivity, holder));
        
        Button loginTwitter = holder.getLoginTwitter();
        loginTwitter.setOnClickListener(
                new TwitterLoginOnClickListener(mActivity, serverHost));
        
        TextView versionView = holder.getVersionName();
        String versionName = new AppContext(mActivity).getFullVersionName();
        versionView.setText(versionName);
    }
}
