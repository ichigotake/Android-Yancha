package net.ichigotake.android.yancha.app.joinusers;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import net.ichigotake.android.common.widget.ArrayAdapter;
import net.ichigotake.android.yancha.app.ChatServer;
import net.ichigotake.yancha.sdk.chat.ChatUser;

public class JoinUserAdapter extends ArrayAdapter<ChatUser, JoinUserViewHolder> {

    private final Context context;

    public JoinUserAdapter(Context context) {
        super(context);
        this.context = context.getApplicationContext();
    }

    @Override
    protected View generateView(LayoutInflater inflater, int position, ViewGroup parent) {
        return inflater.inflate(JoinUserViewHolder.layoutResourceId, parent, false);
    }

    @Override
    protected void bindView(int position, View convertView, JoinUserViewHolder holder) {
        ChatUser item = getItem(position);
        holder.getNickname().setText(item.getNickname());
        Picasso.with(context)
                .load(TextUtils.isEmpty(item.getProfileImageUrl())
                        ? ChatServer.getServerHost() + ChatUser.DEFAULT_PROFILE_IMAGE_PATH
                        : item.getProfileImageUrl())
                .into(holder.getIcon());
    }

    @Override
    protected JoinUserViewHolder generateTag(int position, View convertView) {
        return new JoinUserViewHolder(convertView);
    }
}
