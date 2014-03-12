package net.ichigotake.yancha.chat;

import android.view.View;
import android.widget.TextView;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.widget.ViewContainer;
import net.ichigotake.yancha.common.chat.JoinTagListStorage;
import net.ichigotake.yancha.sdk.model.ChatTags;

class ChatTagContainer implements ViewContainer {

    final private TextView mSelectedTagView;
    
    final private JoinTagListStorage mTagStorage;
    
    final private ChatTags mTags;
    
    ChatTagContainer(View view, ChatTags tags) {
        mSelectedTagView = (TextView) view.findViewById(R.id.chatSelectedTagSelected);
        mSelectedTagView.setOnClickListener(new SelectedTagOnClickListener(mSelectedTagView, tags));
        mTagStorage = new JoinTagListStorage(view.getContext());
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
