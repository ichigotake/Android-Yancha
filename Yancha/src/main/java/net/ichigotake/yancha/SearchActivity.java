package net.ichigotake.yancha;

import android.os.Bundle;
import android.view.MenuItem;

import net.ichigotake.colorfulsweets.lib.compat.actionbar.ActionBarSetting;
import net.ichigotake.colorfulsweets.lib.fragment.FragmentTransit;
import net.ichigotake.yancha.common.context.AppContext;
import net.ichigotake.yancha.common.context.BaseActivity;
import net.ichigotake.yancha.search.LogSearchFragment;

/**
 * 過去ログ
 */
public class SearchActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new ActionBarSetting(getSupportActionBar()).setTitle(R.string.yc_title_search);

        new FragmentTransit(this)
                .setAddBackStack(false)
                .toReplace(AppContext.FRAGMENT_ID_CONTENT, LogSearchFragment.newInstance());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu_search_icon:
                new FragmentTransit(this)
                        .setAddBackStack(false)
                        .toReplace(AppContext.FRAGMENT_ID_CONTENT, LogSearchFragment.newInstance());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
