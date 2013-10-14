package net.ichigotake.yancha.common.ui;

import net.ichigotake.yancha.R;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Fragment‚Ì‘JˆÚ‚ð‚·‚é
 */
public class FragmentTransit {

	final private FragmentManager mFragmentManager;
	
	private boolean mIsAddBackStack = true;
	
	@Deprecated
	public FragmentTransit(Fragment fragment) {
		this.mFragmentManager = fragment.getActivity().getSupportFragmentManager();
	}
	
	public FragmentTransit(FragmentActivity activity) {
		mFragmentManager = activity.getSupportFragmentManager();
	}
	
	public FragmentTransit setIsAddBackStack(boolean isAdd) {
		mIsAddBackStack = isAdd;
		return this;
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
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		transaction.replace(R.id.wrap_fragment, nextFragment);
		if (mIsAddBackStack) {
			transaction.addToBackStack(null);
		}
		transaction.commit();
	}
	
}
