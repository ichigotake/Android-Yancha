package net.ichigotake.android.yancha.app.information;

import android.app.FragmentManager;
import android.content.Context;
import android.view.ActionProvider;
import android.view.View;

public final class InformationFragmentActionProvider extends ActionProvider {

    private final FragmentManager fragmentManager;

    public InformationFragmentActionProvider(Context context, FragmentManager fragmentManager) {
        super(context);
        this.fragmentManager = fragmentManager;
    }

    @Override
    public View onCreateActionView() {
        return null;
    }
    @Override
    public boolean onPerformDefaultAction() {
        InformationFragment.open(fragmentManager);
        return super.onPerformDefaultAction();
    }
}
