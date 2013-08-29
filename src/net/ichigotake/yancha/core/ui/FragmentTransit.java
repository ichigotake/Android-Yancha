package net.ichigotake.yancha.core.ui;

import net.ichigotake.yancha.R;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Fragment‚Ì‘JˆÚ‚ð‚·‚é
 */
public class FragmentTransit {

	private Fragment fragment;
	
	public FragmentTransit(Fragment fragment) {
		this.fragment = fragment;
	}
	
	public void toNext(final Fragment nextFragment) {
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				toNextSync(nextFragment);
			}
		};
		new Handler().post(runnable);
	}
	
	public void toNextSync(final Fragment nextFragment) {
		FragmentManager manager = fragment.getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.wrap_fragment, nextFragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}
	
}
