package net.ichigotake.yancha.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.ichigotake.yancha.R;

/**
 * ログイン画面で利用するビューホルダ
 */
class LoginViewHolder {

    final private EditText mLoginServer;
    
    final private Button mLoginSimpleSubmit;
    
    final private EditText mLoginSimple;
    
    final private Button mLoginTwitter;
    
    final private TextView mVersionName;
    
    LoginViewHolder(View view) {
        mLoginServer = (EditText) view.findViewById(R.id.loginServer);
        mLoginSimpleSubmit = (Button) view.findViewById(R.id.loginAuthSimpleSend);
        mLoginSimple = (EditText) view.findViewById(R.id.loginAuthSimpleNickname);
        mLoginTwitter = (Button) view.findViewById(R.id.loginAuthTwitter);
        mVersionName = (TextView) view.findViewById(R.id.loginVersionName);
    }
    
    EditText getLoginServer() {
        return mLoginServer;
    }
    
    Button getLoginSimpleSubmit() {
        return mLoginSimpleSubmit;
    }
    
    EditText getLoginSimple() {
        return mLoginSimple;
    }
    
    Button getLoginTwitter() {
        return mLoginTwitter;
    }
    
    TextView getVersionName() {
        return mVersionName;
    }
}
