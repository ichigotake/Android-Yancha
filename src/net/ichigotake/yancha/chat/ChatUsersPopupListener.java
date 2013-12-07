package net.ichigotake.yancha.chat;

import android.app.Activity;
import android.view.View;
import android.widget.ListPopupWindow;

import net.ichigotake.colorfulsweets.lib.ui.Display;
import net.ichigotake.yancha.common.user.AppUser;
import net.ichigotake.yancha.sdk.model.ChatUsers;

class ChatUsersPopupListener {

    final private ListPopupWindow mPopup;
    
    final private ChatUserAdapter mAdapter;
    
    final private Activity mActivity;
    
    ChatUsersPopupListener(Activity activity, View anchor) {
        mActivity = activity;
        mAdapter = new ChatUserAdapter(anchor.getContext());
        mPopup = new ListPopupWindow(anchor.getContext());
        mPopup.setAnchorView(anchor);
        mPopup.setAdapter(mAdapter);
    }
    
    void setUsers(ChatUsers users) {
        AppUser own = new AppUser(mActivity);
        ChatUsers excludeMySelfUsers = users.excludeMyself(own);
        excludeMySelfUsers.sort();

        mAdapter.clear();
        mAdapter.add(own);
        mAdapter.addAll(excludeMySelfUsers);
        mAdapter.notifyDataSetChanged();
        mPopup.setModal(true);
        mPopup.setWidth((int) Display.calcDensity(mActivity, 240));
    }

    void show() {
        mPopup.show();
    }

}
