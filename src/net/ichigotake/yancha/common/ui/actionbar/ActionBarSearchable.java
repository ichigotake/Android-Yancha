package net.ichigotake.yancha.common.ui.actionbar;

import net.ichigotake.colorfulsweets.lib.fragment.FragmentTransit;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.context.AppContext;
import net.ichigotake.yancha.search.ChatLogSearchFragment;
import android.app.SearchManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
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
		//SearchManager searchManager = (SearchManager) fragmentActivity.getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
		searchView.setOnSearchClickListener(new SearchIconOnClickListener());
		//SearchableInfo searchable = searchManager.getSearchableInfo(fragmentActivity.getComponentName());
		//searchView.setSearchableInfo(searchable);
		//searchView.setIconifiedByDefault(false);
		//searchView.setOnQueryTextListener(new SearchableOnQueryTextListener());		
	}
	
	private class SearchIconOnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			new FragmentTransit(fragmentActivity)
				.toReplace(AppContext.FRAGMENT_ID_CONTENT, ChatLogSearchFragment.createInstance());
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
			
			Fragment nextFragment = ChatLogSearchFragment.createInstance();
			nextFragment.setArguments(args);
			
			new FragmentTransit(fragmentManager)
				.toReplace(AppContext.FRAGMENT_ID_CONTENT, nextFragment);
		}

	}

}
