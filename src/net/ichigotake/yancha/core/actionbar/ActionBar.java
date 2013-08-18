package net.ichigotake.yancha.core.actionbar;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;


public class ActionBar {
	
	final private android.app.ActionBar actionBar;
	
	final private FragmentManager fragmentManager;
	
	public ActionBar(FragmentActivity activity) {
		this.fragmentManager = activity.getSupportFragmentManager();
		actionBar = activity.getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
	}

	public void setupWithSetDisplayHomeAsUpDisabled() {
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(false);
	}
	
	public void setupWithSetDisplayHomeAsUpEnabled() {
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
	}
	
	public boolean setOnOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
			return true;

		}
		
		return false;
	}
}
