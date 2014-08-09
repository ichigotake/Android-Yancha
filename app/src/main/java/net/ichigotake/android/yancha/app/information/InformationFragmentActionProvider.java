package net.ichigotake.android.yancha.app.information;

import android.content.Context;
import android.view.ActionProvider;
import android.view.View;

import net.ichigotake.android.common.os.ActivityJobWorkerClient;

public final class InformationFragmentActionProvider extends ActionProvider {

    private final ActivityJobWorkerClient workerClient;

    public InformationFragmentActionProvider(Context context, ActivityJobWorkerClient workerClient) {
        super(context);
        this.workerClient = workerClient;
    }

    @Override
    public View onCreateActionView() {
        return null;
    }

    @Override
    public boolean onPerformDefaultAction() {
        workerClient.getWorker().enqueueFragmentManagerJob(InformationFragment::open);
        return super.onPerformDefaultAction();
    }
}
