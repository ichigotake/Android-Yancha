package net.ichigotake.android.yancha.app.chat;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

import net.ichigotake.android.yancha.app.R;
import net.ichigotake.yancha.sdk.chat.ChatMessage;
import net.ichigotake.yancha.sdk.chat.ChatMessageFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class ChatMessagesFragment extends Fragment implements SocketIoClientFragment {

    private List<ChatMessage> messages = new ArrayList<ChatMessage>();
    private ChatMessageAdapter adapter;

    public static ChatMessagesFragment newInstance() {
        return new ChatMessagesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_messages, parent, false);
        ListView messagesView = (ListView) view.findViewById(R.id.fragment_chat_message_list);
        adapter = new ChatMessageAdapter(getActivity(), messages);
        SwingBottomInAnimationAdapter swingAdapter = new SwingBottomInAnimationAdapter(adapter);
        swingAdapter.setAbsListView(messagesView);
        messagesView.setAdapter(swingAdapter);
        return view;
    }

    @Override
    public void onSocketIoEvent(SocketIoEvent event, String response) {
        try {
            switch (event) {
                case USER_MESSAGE:
                    messages.add(ChatMessageFactory.create(new JSONObject(response)));
                    adapter.notifyDataSetChanged();
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
