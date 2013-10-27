package net.ichigotake.yancha.chat;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.ui.ViewContainer;
import net.ichigotake.yancha.common.user.JoinTagListStorage;
import net.ichigotake.yanchasdk.lib.model.JoinTagList;
import android.view.View;
import android.widget.TextView;

class JoinTagContainer implements ViewContainer {

	final private TextView mSelectedTagView;
	
	final private JoinTagListStorage mTagStorage;
	
	final private JoinTagList mTags;
	
	JoinTagContainer(View view, JoinTagList tags) {
		mSelectedTagView = (TextView) view.findViewById(R.id.chatSelectedTagSelected);
		mSelectedTagView.setOnClickListener(new SelectedTagOnClickListener(tags));
		mTagStorage = new JoinTagListStorage(view.getContext());
		mTagStorage.putAll(tags);
		mTags = tags;
	}
	
	JoinTagList getTagList() {
		return mTags;
	}
}
