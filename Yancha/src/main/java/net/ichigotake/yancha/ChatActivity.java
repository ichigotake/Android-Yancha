package net.ichigotake.yancha;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import net.ichigotake.colorfulsweets.ics.fragment.FragmentTransit;
import net.ichigotake.yancha.chat.ChatFragment;
import net.ichigotake.yancha.common.api.rest.ApiUri;
import net.ichigotake.yancha.common.context.AppContext;
import net.ichigotake.yancha.common.context.BaseActivity;
import net.ichigotake.yancha.common.context.LoginSession;
import net.ichigotake.yancha.common.user.AppUser;

/**
 * チャット画面のアクティビティ
 */
public class ChatActivity extends BaseActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApiUri uri = new AppUser(this).getApiUri();
        new FragmentTransit(getFragmentManager())
                .setAddBackStack(false)
                .setNextFragment(AppContext.FRAGMENT_ID_CONTENT, ChatFragment.newInstance(uri))
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
