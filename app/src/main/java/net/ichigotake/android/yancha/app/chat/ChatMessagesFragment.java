package net.ichigotake.android.yancha.app.chat;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nhaarman.listviewanimations.itemmanipulation.AnimateDismissAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.OnDismissCallback;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

import net.ichigotake.android.yancha.app.R;
import net.ichigotake.yancha.sdk.chat.ChatMessage;
import net.ichigotake.yancha.sdk.chat.ChatMessageFactory;
import net.ichigotake.yancha.sdk.chat.ChatUser;
import net.ichigotake.yancha.sdk.chat.ChatUserFactory;

import org.json.JSONException;
import org.json.JSONObject;

public final class ChatMessagesFragment extends Fragment implements SocketIoClientFragment {

    private SparseArray<ChatMessage> messages = new SparseArray<ChatMessage>(100);
    private ChatMessageAdapter adapter;
    private AnimateDismissAdapter dismissAdapter;
    private ChatUser myData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_messages, parent, false);
        ListView messagesView = (ListView) view.findViewById(R.id.fragment_chat_message_list);
        messagesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChatMessage item = (ChatMessage) parent.getItemAtPosition(position);
                SocketIoClient client = ((SocketIoClientActivity)getActivity()).getSocketIoClient();
                client.emit(SocketIoEvent.PLUS_PLUS, item.getId() + "");
            }
        });
        adapter = new ChatMessageAdapter(getActivity(), messages);
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
                    if (myData != null &&
                            !TextUtils.equals(myData.getUserKey(), receivedMessage.getUserKey())) {
                        vibe();
                    }
                    break;
                case DELETE_USER_MESSAGE:
                    ChatMessage deletedMessage = ChatMessageFactory.create(new JSONObject(response));
                    dismissAdapter.animateDismiss(messages.indexOfKey(deletedMessage.getId()));
                    break;
                case TOKEN_LOGIN:
                    myData = ChatUserFactory.fromTokenLoginEvent(response);
                    adapter.setMyData(myData);
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void vibe() {
        Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(300);
    }

}
