package net.ichigotake.yancha.core.actionbar;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.core.ui.FragmentTransit;
import net.ichigotake.yancha.search.ChatLogSearchFragment;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

/**
 * アクションバーに検索窓を設置
 */
public class ActionBarSearchable {

	final private FragmentManager fragmentManager;
	
	final private FragmentActivity fragmentActivity;
	
	public ActionBarSearchable(FragmentActivity fragmentActivity) {
		this.fragmentActivity = fragmentActivity;
		fragmentManager = fragmentActivity.getSupportFragmentManager();
	}
	
	public void setup(Menu menu) {
		SearchManager searchManager = (SearchManager) fragmentActivity.getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
		SearchableInfo searchable = searchManager.getSearchableInfo(fragmentActivity.getComponentName());
		searchView.setSearchableInfo(searchable);
		searchView.setIconifiedByDefault(false);
		searchView.setOnQueryTextListener(new SearchableOnQueryTextListener());		
	}
	
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
			
			Fragment nextFragment = ChatLogSearchFragment.createInstance();
			nextFragment.setArguments(args);
			
			Fragment currentFragment = fragmentManager.findFragmentById(R.id.wrap_fragment);
			new FragmentTransit(currentFragment).toNext(nextFragment);
		}

	}

}
