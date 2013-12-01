package net.ichigotake.yancha.login;

import net.ichigotake.yancha.common.user.User;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

class TwitterLoginOnClickListener implements OnClickListener {

    final private Activity mActivity;
    
    final private User mUser;
    
    final private TextView mServerHostView;
    
    TwitterLoginOnClickListener(FragmentActivity activity, TextView serverHostView) {
        mActivity = activity;
        mUser = new User(activity);
        mServerHostView = serverHostView;
    }
    
    @Override
    public void onClick(View v) {
        String host = mServerHostView.getText().toString();
        mUser.setConnectServerAuthority(host);
        new TwitterLogin(mActivity).start(mUser.getConnectServerAuthority());
    }

}
