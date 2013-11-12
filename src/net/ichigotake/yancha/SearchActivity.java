package net.ichigotake.yancha;

import android.os.Bundle;

import net.ichigotake.colorfulsweets.lib.fragment.FragmentTransit;
import net.ichigotake.yancha.common.context.AppContext;
import net.ichigotake.yancha.common.context.BaseFragmentActivity;
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

}
