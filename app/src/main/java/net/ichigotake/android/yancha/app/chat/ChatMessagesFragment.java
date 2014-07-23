package net.ichigotake.android.yancha.app.chat;

import android.app.Fragment;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.nhaarman.listviewanimations.itemmanipulation.AnimateDismissAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.OnDismissCallback;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

import net.ichigotake.android.yancha.app.R;
import net.ichigotake.yancha.sdk.chat.ChatMessage;
import net.ichigotake.yancha.sdk.chat.ChatMessageFactory;
import net.ichigotake.yancha.sdk.chat.ChatUserFactory;

import org.json.JSONException;
import org.json.JSONObject;

public final class ChatMessagesFragment extends Fragment implements SocketIoClientFragment {

    private SparseArray<ChatMessage> messages = new SparseArray<ChatMessage>(100);
    private ChatMessageAdapter adapter;
    private AnimateDismissAdapter dismissAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_messages, parent, false);
        ListView messagesView = (ListView) view.findViewById(R.id.fragment_chat_message_list);
        adapter = new ChatMessageAdapter(getActivity(), messages, new OnMessageClickListener() {
            @Override
            public void onClick(int position, ChatMessage item) {
                SocketIoClient client = ((SocketIoClientActivity)getActivity()).getSocketIoClient();
                client.emit(SocketIoEvent.PLUS_PLUS, item.getId() + "");
            }
        });
        SwingBottomInAnimationAdapter swingAdapter = new SwingBottomInAnimationAdapter(adapter);
        swingAdapter.setAbsListView(messagesView);
        dismissAdapter = new AnimateDismissAdapter(swingAdapter, new OnDismissCallback() {
            @Override
            public void onDismiss(AbsListView absListView, int[] ints) {
                for (int position : ints) {
                    messages.removeAt(position);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        dismissAdapter.setAbsListView(messagesView);
        messagesView.setAdapter(dismissAdapter);
        return view;
    }

    @Override
    public void onSocketIoEvent(SocketIoEvent event, String response) {
        try {
            switch (event) {
                case USER_MESSAGE:
                    ChatMessage receivedMessage = ChatMessageFactory.create(new JSONObject(response));
                    messages.put(receivedMessage.getId(), receivedMessage);
                    adapter.notifyDataSetChanged();
                    break;
                case DELETE_USER_MESSAGE:
                    ChatMessage deletedMessage = ChatMessageFactory.create(new JSONObject(response));
                    dismissAdapter.animateDismiss(messages.indexOfKey(deletedMessage.getId()));
                    break;
                case TOKEN_LOGIN:
                    adapter.setMyData(ChatUserFactory.fromTokenLoginEvent(response));
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
