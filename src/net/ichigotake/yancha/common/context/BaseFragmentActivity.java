package net.ichigotake.yancha.common.context;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.ui.actionbar.ActionBarSearchable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class BaseFragmentActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yc_main);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.searchable, menu);

		new ActionBarSearchable(this).setup(menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
}
