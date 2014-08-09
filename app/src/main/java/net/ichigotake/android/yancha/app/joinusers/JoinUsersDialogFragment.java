package net.ichigotake.android.yancha.app.joinusers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import net.ichigotake.android.common.app.BaseDialogFragment;
import net.ichigotake.android.common.os.BundleMerger;
import net.ichigotake.android.yancha.app.R;
import net.ichigotake.yancha.sdk.chat.ChatUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class JoinUsersDialogFragment extends BaseDialogFragment {

    private static final String FRAGMENT_TAG = "JoinUsersDialogFragment";
    private static final String KEY_JOIN_USERS = "join_users";
    private final List<ChatUser> joinUsers = new ArrayList<>();

    public static void open(FragmentManager fragmentManager, List<ChatUser> joinUsers) {
        Fragment fragment = new JoinUsersDialogFragment();
        Bundle args = new Bundle();
        args.putParcelableArray(KEY_JOIN_USERS, joinUsers.toArray(new ChatUser[joinUsers.size()]));
        fragment.setArguments(args);
        fragmentManager.beginTransaction()
                .add(fragment, FRAGMENT_TAG)
                .commit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChatUser[] joinUserArray = (ChatUser[]) BundleMerger
                .merge(getArguments(), savedInstanceState).getParcelableArray(KEY_JOIN_USERS);
        this.joinUsers.addAll(Arrays.asList(joinUserArray));
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_join_users, null);
        ListView joinUsersView = (ListView) view.findViewById(R.id.fragment_join_users);
        JoinUserAdapter adapter = new JoinUserAdapter(getActivity());
        adapter.addAll(joinUsers);
        joinUsersView.setAdapter(adapter);
        setCancelable(true);
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setNegativeButton(R.string.dialog_close, (dialog, which) -> dismiss())
                .create();
    }

}
