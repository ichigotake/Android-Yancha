package net.ichigotake.android.yancha.app.chat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import net.ichigotake.android.common.os.BundleMerger;
import net.ichigotake.android.yancha.app.R;
import net.ichigotake.yancha.sdk.chat.ChatMessage;

public final class DeleteMessageDialogFragment extends DialogFragment {

    private static final String FRAGMENT_TAG = "delete_message_dialog";
    private static final String KEY_MESSAGE = "message";
    private ChatMessage message;

    public static void open(FragmentManager fragmentManager, ChatMessage message) {
        DeleteMessageDialogFragment fragment = new DeleteMessageDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_MESSAGE, message);
        fragment.setArguments(args);
        fragmentManager.beginTransaction()
                .add(fragment, FRAGMENT_TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        message = BundleMerger.merge(getArguments(), savedInstanceState).getParcelable(KEY_MESSAGE);
    }

    private View createView(LayoutInflater inflater, ChatMessage message) {
        View view = inflater.inflate(R.layout.fragment_delete_message_dialog, null);
        ListView messagesView = (ListView) view.findViewById(R.id.fragment_delete_message_confirm_messages);
        SparseArray<ChatMessage> messages = new SparseArray<ChatMessage>();
        messages.put(message.getId(), message);
        ChatMessageAdapter adapter = new ChatMessageAdapter(getActivity(), messages);
        messagesView.setAdapter(adapter);
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (!(getActivity() instanceof SocketIoClientActivity)) {
            throw new IllegalStateException("Activity must implements SocketIoClientActivity");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(createView(getActivity().getLayoutInflater(), message))
                .setPositiveButton("削除する", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SocketIoClient client = ((SocketIoClientActivity)getActivity()).getSocketIoClient();
                        client.emit(SocketIoEvent.DELETE_USER_MESSAGE, message.getId() + "");
                    }
                })
                .setNegativeButton("閉じる",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dismiss();
                            }
                        }
                );
        return builder.create();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_MESSAGE, message);
    }
}
