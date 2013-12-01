package net.ichigotake.yancha.chat;

import java.util.List;

import net.ichigotake.yancha.R;
import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * ログインユーザーを表示するためのアダプタ
 */
public class JoinUserListAdapter extends ArrayAdapter<String> {

    public JoinUserListAdapter(Context context, List<String> users) {
        super(context, R.layout.yc_join_users_cell, users);
    }

}
