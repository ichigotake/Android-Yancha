package net.ichigotake.yancha.chat;

import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

import net.ichigotake.yancha.common.user.JoinTagComparator;
import net.ichigotake.yancha.sdk.model.ChatTag;
import net.ichigotake.yancha.sdk.model.ChatTagList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class SelectedTagOnClickListener implements OnClickListener {

    final private ChatTagList mTags;
    final private TextView mSelectedTagView;
    private PopupMenu mPopup;
    
    SelectedTagOnClickListener(TextView selectedTagView, ChatTagList tags) {
        mSelectedTagView = selectedTagView;
        mTags = tags;
    }
    
    @Override
    public void onClick(View view) {
        mPopup = new PopupMenu(view.getContext(), view);
        mPopup.setOnMenuItemClickListener(new PopupOnClickListener());
        List<String> tags = new ArrayList<String>();
        for (Map.Entry<String, ChatTag> tag : mTags.toMap().entrySet()) {
            tags.add(tag.getValue().getFormattedName());
        }
        Collections.sort(tags, new JoinTagComparator());
        for (String tag : tags) {
            mPopup.getMenu().add(tag);
        }

        mPopup.show();
    }
    
    private class PopupOnClickListener implements OnMenuItemClickListener {

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            mSelectedTagView.setText(item.getTitle());
            mPopup.dismiss();
            return false;
        }
        
    }
    
}
