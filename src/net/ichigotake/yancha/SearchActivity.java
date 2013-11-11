package net.ichigotake.yancha;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import net.ichigotake.colorfulsweets.lib.context.ActivityTransit;
import net.ichigotake.colorfulsweets.lib.fragment.FragmentTransit;
import net.ichigotake.yancha.common.context.AppContext;
import net.ichigotake.yancha.common.context.BaseFragmentActivity;
import net.ichigotake.yancha.common.ui.actionbar.ActionBarSearchable;
import net.ichigotake.yancha.search.ChatLogSearchFragment;

/**
 * Created by ichigotake on 2013/11/11.
 */
public class SearchActivity extends BaseFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new FragmentTransit(this)
                .setAddBackStack(false)
                .toReplace(AppContext.FRAGMENT_ID_CONTENT, ChatLogSearchFragment.createInstance());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu_search_icon:
                new FragmentTransit(this)
                        .setAddBackStack(false)
                        .toReplace(AppContext.FRAGMENT_ID_CONTENT, ChatLogSearchFragment.createInstance());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
