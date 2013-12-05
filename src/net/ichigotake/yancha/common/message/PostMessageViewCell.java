package net.ichigotake.yancha.common.message;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import net.ichigotake.yancha.common.ui.MessageSeparator;
import net.ichigotake.yancha.common.user.ProfileImageViewHelper;
import net.ichigotake.yanchasdk.lib.model.PostMessage;

import java.text.SimpleDateFormat;

/**
 * 発言を表示する
 *
 * TODO いい感じの名前を考える
 */
public class PostMessageViewCell {

    final private MessageSeparator mSeparator;

    public PostMessageViewCell(Context context) {
        mSeparator = new MessageSeparator(context);
    }

    public void initializeSeparator(PostMessageViewHolder holder, int position) {
        holder.getContentView().setVisibility(View.GONE);
        holder.getSeparatorView().setVisibility(View.VISIBLE);
        mSeparator.update(holder.getSeparatorView(), position);
    }

    public void initializeMessage(PostMessageViewHolder holder, PostMessage message) {
        holder.getContentView().setVisibility(View.VISIBLE);
        holder.getSeparatorView().setVisibility(View.GONE);
        initialize(holder, message);
    }

    private void initialize(PostMessageViewHolder holder, PostMessage message) {
        setNickname(holder, message.getNickname());
        setMesage(holder, message.getMessage());
        setProfileImage(holder, message.getProfileImageUrl());
        setPlusplus(holder, message.getPlusplus());
        setTimestamp(holder, message.getCreatedTime());
    }

    private void setNickname(PostMessageViewHolder holder, String nickname) {
        holder.getNicknameView().setText(nickname);
    }

    private void setMesage(PostMessageViewHolder holder, String message) {
        holder.getMessageView().setText(message);
    }

    private void setProfileImage(PostMessageViewHolder holder, String profileImageUrl) {
        new ProfileImageViewHelper()
            .setDrawable(holder.getProfileImageView(), profileImageUrl);
    }

    private void setPlusplus(PostMessageViewHolder holder, int plusplus) {
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

    private void setTimestamp(PostMessageViewHolder holder, long createdTime) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(createdTime/100);
        holder.getTimestampView().setText(timestamp);
    }

}
