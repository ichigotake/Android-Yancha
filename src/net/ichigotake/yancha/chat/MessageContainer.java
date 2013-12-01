package net.ichigotake.yancha.chat;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.message.PostMessageAdapter;
import net.ichigotake.yancha.common.message.PostMessageListTagMap;
import net.ichigotake.yancha.common.ui.ViewContainer;
import net.ichigotake.yanchasdk.lib.model.PostMessage;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.haarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;


/**
 * 発言一覧を表示する
 */
class MessageContainer implements ViewContainer {

    final private long ANIMATION_DURATION = 70;

    final private ListView mMessageListView;

    final private PostMessageListTagMap mMessages;

    final private PostMessageAdapter mAdapter;

    MessageContainer(Context context, View view) {
        mAdapter = new PostMessageAdapter(context);
        mMessages = new PostMessageListTagMap();
        mMessageListView = (ListView) view.findViewById(R.id.messageList);
        initialize();
    }
    
    void initialize() {
        ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(mAdapter);
        animationAdapter.setAnimationDurationMillis(ANIMATION_DURATION);
        animationAdapter.setAbsListView(mMessageListView);
        mMessageListView.setAdapter(animationAdapter);
    }

    void removeMessage(PostMessage message) {
        mMessages.remove(message);
        for (int i=mAdapter.getCount()-1; i>=0; i--) {
            PostMessage _message = mAdapter.getItem(i);
            if (message.getId() == _message.getId()) {
                mAdapter.remove(_message);
            }
        }
        mAdapter.notifyDataSetChanged();
    }
    
    void addMessage(PostMessage message) {
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
                    PostMessage _message = mAdapter.getItem(i);
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
