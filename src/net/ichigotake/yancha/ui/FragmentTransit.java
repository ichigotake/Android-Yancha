package net.ichigotake.yancha.ui;

import net.ichigotake.yancha.R;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

public class FragmentTransit {

	private FragmentActivity activity;
	
	public FragmentTransit(Fragment fragment) {
		this.activity = fragment.getActivity();
	}
	
	public void toNext(final Fragment nextFragment) {
		Log.d("FragmentTransit", "before toNext");
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				toNextSync(nextFragment);
				Log.d("FragmentTransit", " toNext->toNextSync");
			}
		};
		new Handler().post(runnable);
		Log.d("FragmentTransit", "after toNext");
	}
	
	public void toNextSync(final Fragment nextFragment) {
		Log.d("FragmentTransit", "before toNextSync");
		FragmentManager manager = activity.getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.wrap_fragment, nextFragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}
	
}
