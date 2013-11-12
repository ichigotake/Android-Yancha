package net.ichigotake.yancha;

import net.ichigotake.colorfulsweets.lib.fragment.FragmentTransit;
import net.ichigotake.yancha.chat.ChatFragment;
import net.ichigotake.yancha.common.context.AppContext;
import net.ichigotake.yancha.common.context.BaseActivity;
import net.ichigotake.yancha.common.context.LoginSession;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * チャット画面のアクティビティ
 */
public class ChatActivity extends BaseFragmentActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		new FragmentTransit(this)
			.setAddBackStack(false)
			.toReplace(AppContext.FRAGMENT_ID_CONTENT, ChatFragment.newInstance());
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
