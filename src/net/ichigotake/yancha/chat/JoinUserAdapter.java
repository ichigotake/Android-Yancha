package net.ichigotake.yancha.chat;

import java.util.List;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.user.ProfileImageViewHelper;
import net.ichigotake.yanchasdk.lib.model.JoinUser;
import net.ichigotake.yanchasdk.lib.model.JoinUsers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ログインユーザーのアダプタ
 */
public class JoinUserAdapter extends ArrayAdapter<JoinUser> {

    final private LayoutInflater mInflater;
    final private ProfileImageViewHelper mImageHelper;

    public JoinUserAdapter(Context context) {
        this(context, new JoinUsers().toList());
    }

    public JoinUserAdapter(Context context, List<JoinUser> users) {
        super(context, R.layout.yc_join_users_cell, users);
        mInflater = LayoutInflater.from(context);
        mImageHelper = new ProfileImageViewHelper();
    }

    public void addAll(JoinUsers users) {
        addAll(users.toList());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.yc_join_users_cell, null);
        }

        JoinUser user = getItem(position);

        ImageView iconView = (ImageView) convertView.findViewById(R.id.profileImage);
        TextView nicknameView = (TextView) convertView.findViewById(R.id.nickname);

        mImageHelper.setDrawable(iconView, user.getProfileImageUrl());
        nicknameView.setText(user.getNickname());

        return convertView;
    }

}
