package net.ichigotake.yancha.chat;

import net.ichigotake.colorfulsweets.lib.ui.Display;
import net.ichigotake.yanchasdk.lib.model.JoinUsers;

import android.app.Activity;
import android.view.View;
import android.widget.ListPopupWindow;

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
        users.sort();
        mAdapter.clear();
        mAdapter.addAll(users);
        mAdapter.notifyDataSetChanged();
        mPopup.setWidth((int)Display.calcDensity(mActivity, 240));
    }
    
    void show() {
        mPopup.show();
    }
    
    void dismiss() {
        mPopup.dismiss();
    }

}
