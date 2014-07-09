package net.ichigotake.android.yancha.app;

import android.content.Context;
import android.content.Intent;
import android.view.ActionProvider;
import android.view.View;

import org.jetbrains.annotations.NotNull;

public final class IntentTripActionProvider extends ActionProvider {

    private final Context context;
    private final Intent intent;

    public IntentTripActionProvider(Context context, Intent intent) {
        super(context);
        this.context = context;
        this.intent = intent;
    }

    @NotNull
    @Override
    public View onCreateActionView() {
        return null;
    }

    @Override
    public boolean onPerformDefaultAction() {
        context.startActivity(intent);
        return super.onPerformDefaultAction();
    }
}
