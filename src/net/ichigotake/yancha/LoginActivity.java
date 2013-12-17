package net.ichigotake.yancha;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.common.base.Optional;

import net.ichigotake.colorfulsweets.lib.fragment.FragmentTransit;
import net.ichigotake.colorfulsweets.lib.intent.UriScheme;
import net.ichigotake.yancha.common.context.AppContext;
import net.ichigotake.yancha.common.context.BaseActivity;
import net.ichigotake.yancha.common.context.LoginSession;
import net.ichigotake.yancha.common.user.AppUser;
import net.ichigotake.yancha.login.LoginFragment;

/**
 * ログイン画面のアクティビティ
 */
public class LoginActivity extends BaseActivity {
        
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final AppUser appUser = new AppUser(this);
        Intent intent = getIntent();
        if (UriScheme.isCallback(intent)) {
            Uri uri= intent.getData();
            Optional<String> token = Optional.fromNullable(uri.getQueryParameter("token"));
            if (token.isPresent()) {
                appUser.setToken(token.get());
                new LoginSession(this)
                    .login();
                return ; // TODO メソッドの末尾以外でのreturnはやめよう
            }
        }

        new FragmentTransit(this)
                .setAddBackStack(false)
                .toReplace(AppContext.FRAGMENT_ID_CONTENT, LoginFragment.newInstance());

    }
    
}
