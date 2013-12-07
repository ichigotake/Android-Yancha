package net.ichigotake.yancha.chat;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.haarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.api.socketio.YanchaEmitter;
import net.ichigotake.yancha.common.message.ChatMessageAdapter;
import net.ichigotake.yancha.common.message.ChatMessageTagMap;
import net.ichigotake.yancha.common.ui.ViewContainer;
import net.ichigotake.yancha.sdk.model.ChatMessage;


/**
 * 発言一覧を表示する
 */
class ChatMessageContainer implements ViewContainer {

    final private long ANIMATION_DURATION = 70;

    final private ListView mMessageListView;

    final private ChatMessageTagMap mMessages;

    final private ChatMessageAdapter mAdapter;

    ChatMessageContainer(Context context, View view, YanchaEmitter emitter) {
        mAdapter = new ChatMessageAdapter(
                context, new ChatPostMessageViewConnector(context, emitter));
        mMessages = new ChatMessageTagMap();
        mMessageListView = (ListView) view.findViewById(R.id.messageList);
        initialize();
    }
    
    void initialize() {
        ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(mAdapter);
        animationAdapter.setAnimationDurationMillis(ANIMATION_DURATION);
        animationAdapter.setAbsListView(mMessageListView);
        mMessageListView.setAdapter(animationAdapter);
    }

    void removeMessage(ChatMessage message) {
        mMessages.remove(message);
        for (int i=mAdapter.getCount()-1; i>=0; i--) {
            ChatMessage _message = mAdapter.getItem(i);
            if (message.getId() == _message.getId()) {
                mAdapter.remove(_message);
            }
        }
        mAdapter.notifyDataSetChanged();
    }
    
    void addMessage(ChatMessage message) {
        if (mMessages.exists(message)) {
            mMessages.update(message);
            mAdapter.update(message);
        } else {

            mMessages.add(message);

            int count = mAdapter.getCount();
            if (0 == count) {
                mAdapter.add(message);
            } else {
                int lastIndex = count-1;
                for (int i=lastIndex; i>=0; i--) {
                    ChatMessage _message = mAdapter.getItem(i);
                    if (message.getId() >= _message.getId()) {
                        if (lastIndex == i) {
                            mAdapter.add(message);
                        } else {
                            mAdapter.insert(message, i+1);
                        }
                        break;
                    }
                }
            }
            while (mAdapter.getCount() > 100) {
                mAdapter.remove(mAdapter.getItem(0));
            }

        }

        mAdapter.notifyDataSetChanged();

        if (isLastPosition()) {
            scrollBottom();
        }
    }
    
    private void scrollBottom() {
        mMessageListView.post(new Runnable() {            
            @Override
            public void run() {
                mMessageListView.setSelection(getBottomPosition());
            }
        });
    }
    
    private boolean isLastPosition() {
        return (getBottomPosition() == mMessageListView.getLastVisiblePosition()+1);
    }
    
    private int getBottomPosition() {
        return mAdapter.getCount();
    }
}
