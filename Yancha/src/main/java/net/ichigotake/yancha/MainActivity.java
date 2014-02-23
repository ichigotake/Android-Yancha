package net.ichigotake.yancha;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.common.base.Optional;

import net.ichigotake.colorfulsweets.common.intent.UriScheme;
import net.ichigotake.colorfulsweets.ics.fragment.FragmentTransit;
import net.ichigotake.yancha.chat.ChatFragment;
import net.ichigotake.yancha.common.chat.LoginSession;
import net.ichigotake.yancha.common.chat.AppUser;
import net.ichigotake.yancha.login.LoginFragment;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final AppUser appUser = new AppUser();
        final Intent intent = getIntent();
        if (UriScheme.isCallback(intent)) {
            final Optional<Uri> uri = Optional.fromNullable(intent.getData());
            if (uri.isPresent()) {
                final Optional<String> token = Optional.fromNullable(uri.get().getQueryParameter("token"));
                if (token.isPresent()) {
                    appUser.setToken(token.get());
                    new LoginSession(this)
                            .login();

                    transition(ChatFragment.newInstance(appUser.getApiUri()));
                    return ;
                }
            }
        }

        if (appUser.hasToken()) {
            transition(ChatFragment.newInstance(appUser.getApiUri()));
        } else {
            transition(LoginFragment.newInstance());
        }
    }

    private void transition(Fragment nextFragment) {
        new FragmentTransit(getFragmentManager())
                .setAddBackStack(false)
                .setNextFragment(YanchaApp.FRAGMENT_ID_CONTENT, nextFragment)
                .transition();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        switch (menu.getItemId()) {
            case R.id.menu_logout:
                new LoginSession(this)
                        .logout();
                return true;
        }

        return super.onOptionsItemSelected(menu);
    }

}
