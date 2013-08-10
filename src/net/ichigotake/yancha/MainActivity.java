package net.ichigotake.yancha;

import net.ichigotake.yancha.core.actionbar.ActionBarSearchable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class MainActivity extends FragmentActivity {
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yc_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		
		new ActionBarSearchable(this).setup(menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
}
