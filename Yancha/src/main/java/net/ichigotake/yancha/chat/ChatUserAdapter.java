package net.ichigotake.yancha.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.api.RequestManager;
import net.ichigotake.yancha.sdk.model.ChatUser;
import net.ichigotake.yancha.sdk.model.ChatUsers;

import java.util.List;

/**
 * ログインユーザーのアダプタ
 */
public class ChatUserAdapter extends ArrayAdapter<ChatUser> {

    final private LayoutInflater mInflater;

    public ChatUserAdapter(Context context) {
        this(context, new ChatUsers().toList());
    }

    public ChatUserAdapter(Context context, List<ChatUser> users) {
        super(context, R.layout.yc_join_users_cell, users);
        mInflater = LayoutInflater.from(context);
    }

    public void addAll(ChatUsers users) {
        addAll(users.toList());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.yc_join_users_cell, null);
        }

        ChatUser user = getItem(position);

        NetworkImageView iconView = (NetworkImageView) convertView.findViewById(R.id.profileImage);
        TextView nicknameView = (TextView) convertView.findViewById(R.id.nickname);

        iconView.setImageUrl(user.getProfileImageUrl(), RequestManager.getImageLoader());
        nicknameView.setText(user.getNickname());

        return convertView;
    }

}
