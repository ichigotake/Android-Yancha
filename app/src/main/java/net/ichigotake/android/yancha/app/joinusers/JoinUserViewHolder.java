package net.ichigotake.android.yancha.app.joinusers;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.ichigotake.android.yancha.app.R;

class JoinUserViewHolder {

    static int layoutResourceId = R.layout.join_user_item;

    private final ImageView icon;
    private final TextView nickname;

    JoinUserViewHolder(View convertView) {
        this.icon = (ImageView) convertView.findViewById(R.id.join_user_item_icon);
        this.nickname = (TextView) convertView.findViewById(R.id.join_user_item_nickname);
    }

    ImageView getIcon() {
        return icon;
    }

    TextView getNickname() {
        return nickname;
    }

}
