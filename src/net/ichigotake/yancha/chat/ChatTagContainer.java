package net.ichigotake.yancha.chat;

import android.view.View;
import android.widget.TextView;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.ui.ViewContainer;
import net.ichigotake.yancha.common.user.JoinTagStorage;
import net.ichigotake.yancha.sdk.model.ChatTags;

class ChatTagContainer implements ViewContainer {

    final private TextView mSelectedTagView;
    
    final private JoinTagStorage mTagStorage;
    
    final private ChatTags mTags;
    
    ChatTagContainer(View view, ChatTags tags) {
        mSelectedTagView = (TextView) view.findViewById(R.id.chatSelectedTagSelected);
        mSelectedTagView.setOnClickListener(new SelectedTagOnClickListener(mSelectedTagView, tags));
        mTagStorage = new JoinTagStorage(view.getContext());
        mTagStorage.putAll(tags);
        mTags = tags;
    }

    String getText() {
        return mSelectedTagView.getText().toString();
    }

    ChatTags getTags() {
        return mTags;
    }
}
