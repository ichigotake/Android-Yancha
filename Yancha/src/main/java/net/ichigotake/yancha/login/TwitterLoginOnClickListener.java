package net.ichigotake.yancha.login;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import net.ichigotake.yancha.common.user.AppUser;

class TwitterLoginOnClickListener implements OnClickListener {

    final private Activity mActivity;
    
    final private AppUser mAppUser;
    
    final private TextView mServerHostView;
    
    TwitterLoginOnClickListener(Activity activity, TextView serverHostView) {
        mActivity = activity;
        mAppUser = new AppUser(activity);
        mServerHostView = serverHostView;
    }
    
    @Override
    public void onClick(View v) {
        String host = mServerHostView.getText().toString();
        mAppUser.setConnectServer(host);
        new TwitterLogin(mActivity).start(mAppUser.getConnectServerAuthority());
    }

}
