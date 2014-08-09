package net.ichigotake.android.common.os;

import android.app.Activity;
import android.os.Message;

public final class ActivityJobWorker {

    private final ActivityJobHandler handler = new ActivityJobHandler();

    public void enqueueFragmentManagerJob(FragmentManagerJob job) {
        Message message = handler.obtainMessage();
        message.obj = job;
        handler.sendMessage(message);
    }

    public void enqueueActivityJob(ActivityJob job) {
        Message message = handler.obtainMessage();
        message.obj = job;
        handler.sendMessage(message);
    }

    public void setActivity(Activity activity) {
        this.handler.setActivity(activity);
    }

    public void resume() {
        handler.resume();
    }

    public void pause() {
        handler.pause();
    }
}
