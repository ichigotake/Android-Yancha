package net.ichigotake.android.yancha.app.chat;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.ichigotake.android.common.widget.SparseArrayAdapter;
import net.ichigotake.android.yancha.app.ChatServer;
import net.ichigotake.android.yancha.app.R;
import net.ichigotake.yancha.sdk.chat.ChatMessage;
import net.ichigotake.yancha.sdk.chat.ChatUser;

import java.util.Collection;

public class ChatMessageAdapter extends SparseArrayAdapter<ChatMessage> {

    private final Context context;
    private final String serverHost;
    private final LayoutInflater inflate;
    private final OnMessageItemClickListener listener;

    public ChatMessageAdapter(Context context, SparseArray<ChatMessage> messages) {
        this(context, messages, null);
    }

    public ChatMessageAdapter(Context context, SparseArray<ChatMessage> messages, OnMessageItemClickListener listener) {
        this.context = context;
        this.inflate = LayoutInflater.from(context);
        this.serverHost = ChatServer.getServerHost();
        this.objects = messages;
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage item = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflate.inflate(R.layout.chat_message_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String profileImageUrl = TextUtils.isEmpty(item.getProfileImageUrl())
                ? serverHost + ChatUser.DEFAULT_PROFILE_IMAGE_PATH : item.getProfileImageUrl();
        Picasso.with(context).load(profileImageUrl).into(holder.userIcon);
        holder.nickname.setText(item.getNickname());
        holder.message.setText(item.getMessage());
        holder.timestamp.setText(DateFormat.format("yyyy-M-d HH:mm", item.getCreatedTime()));
        if (item.getPlusplus() == 0) {
            holder.plusPlus.setVisibility(View.GONE);
        } else if (item.getPlusplus() >= 50) {
            holder.plusPlus.setText("★ x " + item.getPlusplus());
            holder.plusPlus.setVisibility(View.VISIBLE);
        } else {
            String plusPlus = "";
            for (int i=0; i<item.getPlusplus(); i++) {
                plusPlus += "★";
            }
            holder.plusPlus.setText(plusPlus);
            holder.plusPlus.setVisibility(View.VISIBLE);
        }
        if (listener != null) {
            convertView.setOnClickListener(new OnItemClickListener(item, listener));
        }
        return convertView;
    }

    @Override
    public void addAll(Collection<ChatMessage> collection) {
        for (ChatMessage item : collection) {
            objects.put(item.getId(), item);
        }
    }

    private static class ViewHolder {
        private final ImageView userIcon;
        private final TextView nickname;
        private final TextView timestamp;
        private final TextView message;
        private final TextView plusPlus;

        private ViewHolder(View view) {
            this.userIcon = (ImageView) view.findViewById(R.id.chat_message_item_user_icon);
            this.nickname = (TextView) view.findViewById(R.id.chat_message_item_nickname);
            this.timestamp = (TextView) view.findViewById(R.id.chat_message_item_timestamp);
            this.message = (TextView) view.findViewById(R.id.chat_message_item_message);
            this.plusPlus = (TextView) view.findViewById(R.id.chat_message_item_plus_plus);
        }
    }

    private static class OnItemClickListener implements View.OnClickListener {

        private final ChatMessage item;
        private final OnMessageItemClickListener listener;

        private OnItemClickListener(ChatMessage item, OnMessageItemClickListener listener) {
            this.item = item;
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            listener.onPlusPlusClick(item);
        }
    }
}
