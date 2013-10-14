package net.ichigotake.yancha.context;

import net.ichigotake.yancha.common.ui.actionbar.ActionBarSearchable;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class BaseFragmentActivity extends FragmentActivity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//‹@”\ŽÀ‘•‚µŽŸ‘æŠJ•ú
		//getMenuInflater().inflate(R.menu.main, menu);
		
		new ActionBarSearchable(this).setup(menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
}
