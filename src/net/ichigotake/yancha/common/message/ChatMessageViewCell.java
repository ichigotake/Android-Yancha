package net.ichigotake.yancha.common.message;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import net.ichigotake.yancha.common.ui.MessageSeparator;
import net.ichigotake.yancha.common.user.ProfileImageViewHelper;
import net.ichigotake.yanchasdk.lib.model.ChatMessage;

import java.text.SimpleDateFormat;

/**
 * 発言を表示する
 *
 * TODO いい感じの名前を考える
 */
public class ChatMessageViewCell {

    final private MessageSeparator mSeparator;

    public ChatMessageViewCell(Context context) {
        mSeparator = new MessageSeparator(context);
    }

    public void initializeSeparator(ChatMessageViewHolder holder, int position) {
        holder.getContentView().setVisibility(View.GONE);
        holder.getSeparatorView().setVisibility(View.VISIBLE);
        mSeparator.update(holder.getSeparatorView(), position);
    }

    public void initializeMessage(ChatMessageViewHolder holder, ChatMessage message) {
        holder.getContentView().setVisibility(View.VISIBLE);
        holder.getSeparatorView().setVisibility(View.GONE);
        initialize(holder, message);
    }

    private void initialize(ChatMessageViewHolder holder, ChatMessage message) {
        setNickname(holder, message.getNickname());
        setMesage(holder, message.getMessage());
        setProfileImage(holder, message.getProfileImageUrl());
        setPlusplus(holder, message.getPlusplus());
        setTimestamp(holder, message.getCreatedTime());
    }

    private void setNickname(ChatMessageViewHolder holder, String nickname) {
        holder.getNicknameView().setText(nickname);
    }

    private void setMesage(ChatMessageViewHolder holder, String message) {
        holder.getMessageView().setText(message);
    }

    private void setProfileImage(ChatMessageViewHolder holder, String profileImageUrl) {
        new ProfileImageViewHelper()
            .setDrawable(holder.getProfileImageView(), profileImageUrl);
    }

    private void setPlusplus(ChatMessageViewHolder holder, int plusplus) {
        TextView plusplusView = holder.getPlusplusView();
        if (plusplus > 0) {
            String plusplusText;
            if (plusplus >= 50) {
                plusplusText = "★ x " + plusplus;
            } else {
                StringBuilder builder = new StringBuilder();
                for (int i=0; i<plusplus; i++) {
                    builder.append("★");
                }
                plusplusText = builder.toString();
            }
            plusplusView.setVisibility(View.VISIBLE);
            plusplusView.setText(plusplusText);
        } else {
            plusplusView.setText("");
            plusplusView.setVisibility(View.GONE);
        }

    }

    private void setTimestamp(ChatMessageViewHolder holder, long createdTime) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(createdTime/100);
        holder.getTimestampView().setText(timestamp);
    }

}
