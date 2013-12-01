package net.ichigotake.yancha.common.message;

import android.view.View;
import android.widget.TextView;

import net.ichigotake.yancha.common.user.ProfileImageViewHelper;
import net.ichigotake.yanchasdk.lib.model.PostMessage;

import java.text.SimpleDateFormat;

/**
 * 発言を表示する
 *
 * TODO いい感じの名前を考える
 */
public class PostMessageViewCell {

    PostMessageViewHolder mHolder;

    private PostMessageViewCell(PostMessageViewHolder holder) {
        mHolder = holder;
    }

    public static void initialize(PostMessageViewHolder holder, PostMessage message) {
        PostMessageViewCell cell = new PostMessageViewCell(holder);
        cell.initialize(message);
    }

    private void initialize(PostMessage message) {
        setNickname(message.getNickname());
        setMesage(message.getMessage());
        setProfileImage(message.getProfileImageUrl());
        setPlusplus(message.getPlusplus());
        setTimestamp(message.getCreatedTime());
    }

    private void setNickname(String nickname) {
        mHolder.getNicknameView().setText(nickname);
    }

    private void setMesage(String message) {
        mHolder.getMessageView().setText(message);
    }

    private void setProfileImage(String profileImageUrl) {
        new ProfileImageViewHelper()
            .setDrawable(mHolder.getProfileImageView(), profileImageUrl);
    }

    private void setPlusplus(int plusplus) {
        TextView plusplusView = mHolder.getPlusplusView();
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
            plusplusView.setVisibility(View.GONE);
        }

    }

    private void setTimestamp(long createdTime) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(createdTime/100);
        mHolder.getTimestampView().setText(timestamp);
    }

}
