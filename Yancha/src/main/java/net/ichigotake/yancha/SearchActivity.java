package net.ichigotake.yancha;

import android.os.Bundle;
import android.view.MenuItem;

import net.ichigotake.colorfulsweets.ics.fragment.FragmentTransit;
import net.ichigotake.yancha.common.context.AppContext;
import net.ichigotake.yancha.search.LogSearchFragment;

/**
 * 過去ログ
 */
public class SearchActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setTitle(R.string.yc_title_search);

        new FragmentTransit(getFragmentManager())
                .setAddBackStack(false)
                .setNextFragment(AppContext.FRAGMENT_ID_CONTENT, LogSearchFragment.newInstance())
                .transition();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu_search_icon:
                new FragmentTransit(getFragmentManager())
                        .setAddBackStack(false)
                        .setNextFragment(AppContext.FRAGMENT_ID_CONTENT, LogSearchFragment.newInstance())
                        .transition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
