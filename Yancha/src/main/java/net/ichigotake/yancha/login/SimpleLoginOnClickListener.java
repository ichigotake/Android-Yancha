package net.ichigotake.yancha.login;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

class SimpleLoginOnClickListener implements OnClickListener {

    final private SubmitSimpleLogin mLogin;
    
    SimpleLoginOnClickListener(FragmentActivity activity, LoginViewHolder holder) {
        mLogin = new SubmitSimpleLogin(activity, holder);
    }
    
    @Override
    public void onClick(View arg0) {
        mLogin.send();
    }
}
