package net.ichigotake.yancha.chat;

import android.view.View;
import android.widget.TextView;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.ui.ViewContainer;
import net.ichigotake.yancha.common.user.JoinTagListStorage;
import net.ichigotake.yancha.sdk.model.ChatTagList;

class ChatTagContainer implements ViewContainer {

	final private TextView mSelectedTagView;
	
	final private JoinTagListStorage mTagStorage;
	
	final private ChatTagList mTags;
	
	ChatTagContainer(View view, ChatTagList tags) {
		mSelectedTagView = (TextView) view.findViewById(R.id.chatSelectedTagSelected);
		mSelectedTagView.setOnClickListener(new SelectedTagOnClickListener(tags));
		mTagStorage = new JoinTagListStorage(view.getContext());
		mTagStorage.putAll(tags);
		mTags = tags;
	}
	
	ChatTagList getTagList() {
		return mTags;
	}
}
