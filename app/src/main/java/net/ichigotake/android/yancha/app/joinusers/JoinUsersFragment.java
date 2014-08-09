package net.ichigotake.android.yancha.app.joinusers;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import net.ichigotake.android.common.os.ActivityJobWorker;
import net.ichigotake.android.common.os.ActivityJobWorkerClient;
import net.ichigotake.android.yancha.app.R;
import net.ichigotake.android.yancha.app.chat.SocketIoClientFragment;
import net.ichigotake.android.yancha.app.chat.SocketIoEvent;
import net.ichigotake.yancha.sdk.chat.ChatUserFactory;

import org.json.JSONException;

public final class JoinUsersFragment extends Fragment implements SocketIoClientFragment {

    private ActivityJobWorker worker;
    private JoinUsersDialogActionProvider joinUsersDialogActionProvider;

    public static JoinUsersFragment newInstance() {
        return new JoinUsersFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof ActivityJobWorkerClient)) {
            throw new IllegalStateException("Activity must be implements ActivityJobWorkerClient");
        }
        this.worker = ((ActivityJobWorkerClient)activity).getWorker();
        this.joinUsersDialogActionProvider = new JoinUsersDialogActionProvider(
                getActivity(),
                worker
        );
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.join_users, menu);
        menu.findItem(R.id.action_join_users).setActionProvider(joinUsersDialogActionProvider);
    }

    @Override
    public void onSocketIoEvent(SocketIoEvent event, String response) {
        try {
            switch (event) {
                case NICKNAMES:
                    Log.d("JoinUsersFragment", "response: " + response);
                    joinUsersDialogActionProvider.setJoinUsers(ChatUserFactory.fromNicknameEvent(response));
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDetach() {
        this.worker = null;
        super.onDetach();
    }

}
