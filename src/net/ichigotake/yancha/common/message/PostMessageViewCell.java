package net.ichigotake.yancha.common.message;

import android.view.View;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import net.ichigotake.yanchasdk.lib.model.PostMessageBuilder;

import java.text.SimpleDateFormat;

/**
 * 発言を表示する
 *
 * TODO いい感じの名前を考える
 */
class PostMessageViewCell {

    PostMessageViewHolder mHolder;

    private PostMessageViewCell(PostMessageViewHolder holder) {
        mHolder = holder;
    }

    static void initialize(View view, PostMessageBuilder.PostMessage message) {
        PostMessageViewHolder holder = new PostMessageViewHolder(view);
        PostMessageViewCell cell = new PostMessageViewCell(holder);
        cell.initialize(message);
    }

    private void initialize(PostMessageBuilder.PostMessage message) {
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
        if (!profileImageUrl.isEmpty()) {
            UrlImageViewHelper.setUrlDrawable(mHolder.getProfileImageView(), profileImageUrl);
        } else {
            mHolder.getProfileImageView().setBackgroundResource(android.R.drawable.sym_action_chat);
        }
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
