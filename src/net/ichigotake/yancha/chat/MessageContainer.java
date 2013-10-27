package net.ichigotake.yancha.chat;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.message.MessageListAdapter;
import net.ichigotake.yancha.common.ui.ViewContainer;
import net.ichigotake.yanchasdk.lib.model.PostMessageBuilder.PostMessage;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.ListView;

import com.haarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;

class MessageContainer implements ViewContainer {
	
	final private ListView mMessageListView;
	
	final private SparseArray<PostMessage> mMessages;
	
	final private MessageListAdapter mAdapter;
	
	MessageContainer(Context context, View view) {
		mAdapter = new MessageListAdapter(context);
		mMessages = new SparseArray<PostMessage>();
		mMessageListView = (ListView) view.findViewById(R.id.messageList);
		initialize();
	}
	
	void initialize() {
		ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(mAdapter);
		animationAdapter.setAbsListView(mMessageListView);
		mMessageListView.setAdapter(animationAdapter);
	}
	
	void addMessage(PostMessage message) {		
		if (null != mMessages.get(message.getId())) {
			return ;
		}
		
		mMessages.put(message.getId(), message);
		mAdapter.add(message);
		
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
		return mMessages.size()-1;
	}
}
