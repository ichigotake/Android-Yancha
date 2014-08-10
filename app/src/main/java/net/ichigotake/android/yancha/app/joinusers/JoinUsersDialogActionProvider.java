package net.ichigotake.android.yancha.app.joinusers;

import android.content.Context;
import android.view.ActionProvider;
import android.view.View;

import net.ichigotake.android.common.os.ActivityJobWorker;
import net.ichigotake.yancha.sdk.chat.ChatUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class JoinUsersDialogActionProvider extends ActionProvider {

    private final List<ChatUser> joinUsers = new ArrayList<>();
    private final ActivityJobWorker worker;

    public JoinUsersDialogActionProvider(Context context, ActivityJobWorker worker) {
        super(context.getApplicationContext());
        this.worker = worker;
    }

    @Override
    public View onCreateActionView() {
        return null;
    }

    @Override
    public boolean onPerformDefaultAction() {
        this.worker.enqueueFragmentManagerJob((value) -> JoinUsersDialogFragment.open(value, joinUsers));
        return super.onPerformDefaultAction();
    }

    public void setJoinUsers(Collection<ChatUser> joinUsers) {
        this.joinUsers.clear();
        this.joinUsers.addAll(joinUsers);
    }

}
