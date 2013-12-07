package net.ichigotake.yancha.chat;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import net.ichigotake.yancha.R;
import net.ichigotake.yanchasdk.lib.model.ChatUsers;

/**
 * ログインユーザーを表示する
 */
class ChatUsersContainer {

    final private TextView mJoinUsersCountView;
    final private ChatUsersPopupListener mPopup;
    final private View mUsersIcon;

    ChatUsersContainer(Activity activity, View view) {
        mJoinUsersCountView = (TextView) view.findViewById(R.id.chatJoinUsersCount);
        mUsersIcon = view.findViewById(R.id.chatJoinUsersIcon);
        mUsersIcon.setOnClickListener(new IconClickListener());
        mPopup = new ChatUsersPopupListener(activity, mUsersIcon);
    }
    
    void setUsers(ChatUsers users) {
        mPopup.setUsers(users);
        mJoinUsersCountView.setText(users.count() + "人");
    }
    
    void update(ChatUsers users) {
        setUsers(users);
    }
    
    private class IconClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            mPopup.show();
        }

    }
}
