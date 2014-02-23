package net.ichigotake.yancha.common.widget.actionbar;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SearchView.OnQueryTextListener;

import net.ichigotake.colorfulsweets.ics.fragment.FragmentTransit;
import net.ichigotake.yancha.AppContext;
import net.ichigotake.yancha.search.LogSearchFragment;

/**
 * アクションバーの検索UI
 */
public class ActionBarSearchable {

    final private FragmentManager fragmentManager;
    
    final private Activity activity;
    
    public ActionBarSearchable(Activity activity) {
        this.activity = activity;
        fragmentManager = activity.getFragmentManager();
    }
    
    public void setup(Menu menu) {
        //SearchManager searchManager = (SearchManager) activity.getSystemService(Context.SEARCH_SERVICE);
        //SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        //searchView.setOnSearchClickListener(new SearchIconOnClickListener());
        //SearchableInfo searchable = searchManager.getSearchableInfo(activity.getComponentName());
        //searchView.setSearchableInfo(searchable);
        //searchView.setIconifiedByDefault(false);
        //searchView.setOnQueryTextListener(new SearchableOnQueryTextListener());        
    }
    
    private class SearchIconOnClickListener implements OnClickListener {

        @Override
        public void onClick(View arg0) {
            new FragmentTransit(fragmentManager)
                    .setNextFragment(AppContext.FRAGMENT_ID_CONTENT, LogSearchFragment.newInstance())
                    .transition();
        }
        
    }

    // 保留
    private class SearchableOnQueryTextListener implements OnQueryTextListener {
            
        @Override
        public boolean onQueryTextSubmit(String query) {
            if ("".equals(query)) {
                return false;
            }
            
            doSearch(query);
            return true;
        }
        
        @Override
        public boolean onQueryTextChange(String arg0) {
            return false;
        }

        private void doSearch(String query) {
            final Bundle args = new Bundle();
            args.putString(SearchManager.QUERY, query);
            
            Fragment nextFragment = LogSearchFragment.newInstance();
            nextFragment.setArguments(args);
            
            new FragmentTransit(fragmentManager)
                    .setNextFragment(AppContext.FRAGMENT_ID_CONTENT, nextFragment)
                    .transition();
        }

    }

}
