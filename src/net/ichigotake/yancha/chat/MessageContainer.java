package net.ichigotake.yancha.chat;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.message.MessageListAdapter;
import net.ichigotake.yancha.common.message.PostMessageComparator;
import net.ichigotake.yancha.common.message.PostMessageListTagMap;
import net.ichigotake.yancha.common.ui.ViewContainer;
import net.ichigotake.yanchasdk.lib.model.PostMessageBuilder.PostMessage;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.ListView;

import com.haarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * 発言一覧を表示する
 */
class MessageContainer implements ViewContainer {
	
	final private ListView mMessageListView;

    final private PostMessageListTagMap mMessages;
	
	final private MessageListAdapter mAdapter;

	MessageContainer(Context context, View view) {
		mAdapter = new MessageListAdapter(context);
		mMessages = new PostMessageListTagMap();
		mMessageListView = (ListView) view.findViewById(R.id.messageList);
		initialize();
	}
	
	void initialize() {
		ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(mAdapter);
		animationAdapter.setAbsListView(mMessageListView);
		mMessageListView.setAdapter(animationAdapter);
	}
	
	void addMessage(PostMessage message) {
        if (mMessages.exists(message)) {
            return ;
        }

		mMessages.add(message);
		mAdapter.add(message);
        mAdapter.sort(new PostMessageComparator());
		
		while (mAdapter.getCount() > 100) {
			mAdapter.remove(mAdapter.getItem(0));
		}

		mAdapter.notifyDataSetChanged();
		mMessageListView.invalidateViews();
		
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
