package net.ichigotake.yancha.chat;

import android.app.Activity;
import android.view.View;
import android.widget.ListPopupWindow;

import net.ichigotake.colorfulsweets.lib.ui.Display;
import net.ichigotake.yancha.common.user.AppUser;
import net.ichigotake.yanchasdk.lib.model.JoinUsers;

class JoinUsersPopupListener {

    final private ListPopupWindow mPopup;
    
    final private JoinUserAdapter mAdapter;
    
    final private Activity mActivity;
    
    JoinUsersPopupListener(Activity activity, View anchor) {
        mActivity = activity;
        mAdapter = new JoinUserAdapter(anchor.getContext());
        mPopup = new ListPopupWindow(anchor.getContext());
        mPopup.setAnchorView(anchor);
        mPopup.setAdapter(mAdapter);
    }
    
    void setUsers(JoinUsers users) {
        AppUser own = new AppUser(mActivity);
        JoinUsers excludeMySelfUsers = users.excludeMyself(own);
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
