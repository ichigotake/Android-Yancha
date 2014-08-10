package net.ichigotake.android.yancha.app.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.ichigotake.android.yancha.app.R;

public final class ProgressBarFragment extends Fragment {

    private static final String FRAGMENT_TAG = "ProgressBarFragment";

    public static void show(FragmentManager fragmentManager) {
        if (fragmentManager.findFragmentByTag(FRAGMENT_TAG) != null) {
            return;
        }
        fragmentManager.beginTransaction()
                .add(android.R.id.content, new ProgressBarFragment(), FRAGMENT_TAG)
                .commit();
    }

    public static void hide(FragmentManager fragmentManager) {
        Fragment fragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .remove(fragment)
                    .commit();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_progress_bar, parent, false);
    }

}
