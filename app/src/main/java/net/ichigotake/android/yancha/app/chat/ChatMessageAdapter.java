package net.ichigotake.android.yancha.app.chat;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmitriy.tarasov.android.intents.IntentUtils;
import com.squareup.picasso.Picasso;

import net.ichigotake.android.common.os.ActivityJobWorker;
import net.ichigotake.android.common.widget.SparseArrayAdapter;
import net.ichigotake.android.common.widget.TextLinkUtils;
import net.ichigotake.android.yancha.app.ChatServer;
import net.ichigotake.android.yancha.app.R;
import net.ichigotake.yancha.sdk.chat.ChatMessage;
import net.ichigotake.yancha.sdk.chat.ChatUser;

import java.util.Collection;

public class ChatMessageAdapter extends SparseArrayAdapter<ChatMessage> {

    private final Context context;
    private final OnMessageClickListener onMessageClickListener;
    private final String serverHost;
    private final LayoutInflater inflate;
    private final boolean hasSocketIoClient;
    private final ActivityJobWorker worker;
    private ChatUser myData;

    public ChatMessageAdapter(Activity activity, ActivityJobWorker worker, SparseArray<ChatMessage> messages) {
        this(activity, worker, messages, null);
    }

    public ChatMessageAdapter(
            Activity activity, ActivityJobWorker worker, SparseArray<ChatMessage> messages, OnMessageClickListener onMessageClickListener
    ) {
        this.context = activity.getApplicationContext();
        this.onMessageClickListener = onMessageClickListener;
        this.inflate = activity.getLayoutInflater();
        this.serverHost = ChatServer.getServerHost();
        this.objects = messages;
        this.hasSocketIoClient = true;
        this.worker = worker;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ChatMessage item = getItem(position);
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

        TextLinkUtils.autoLink(holder.message, new TextLinkUtils.OnClickListener() {
            @Override
            public void onLinkClicked(String link) {
                IntentUtils.openLink(link);
            }

            @Override
            public void onClicked() {
                if (onMessageClickListener != null) {
                    onMessageClickListener.onClick(position, item);
                }
            }
        });

        boolean canDeleteMessage = hasSocketIoClient
                && myData != null
                && TextUtils.equals(item.getUserKey(), myData.getUserKey());
        if (canDeleteMessage) {
            holder.userIconContainer.setOnClickListener(v -> {
                worker.enqueueFragmentManagerJob(value -> DeleteMessageDialogFragment.open(value, item));
            });
            holder.iconForEditable.setVisibility(View.VISIBLE);
        } else {
            holder.iconForEditable.setVisibility(View.GONE);
            holder.userIconContainer.setOnClickListener((view) -> {});
        }
        return convertView;
    }

    @Override
    public void addAll(Collection<ChatMessage> collection) {
        for (ChatMessage item : collection) {
            objects.put(item.getId(), item);
        }
    }

    public void setMyData(ChatUser myData) {
        this.myData = myData;
    }

    private static class ViewHolder {
        private final ImageView userIcon;
        private final TextView nickname;
        private final TextView timestamp;
        private final TextView message;
        private final TextView plusPlus;
        private final View userIconContainer;
        private final View iconForEditable;

        private ViewHolder(View view) {
            this.userIcon = (ImageView) view.findViewById(R.id.chat_message_item_user_icon);
            this.nickname = (TextView) view.findViewById(R.id.chat_message_item_nickname);
            this.timestamp = (TextView) view.findViewById(R.id.chat_message_item_timestamp);
            this.message = (TextView) view.findViewById(R.id.chat_message_item_message);
            this.plusPlus = (TextView) view.findViewById(R.id.chat_message_item_plus_plus);
            this.userIconContainer = view.findViewById(R.id.chat_message_item_user_icon_container);
            this.iconForEditable = view.findViewById(R.id.chat_message_item_icon_for_editable);
        }
    }

}
