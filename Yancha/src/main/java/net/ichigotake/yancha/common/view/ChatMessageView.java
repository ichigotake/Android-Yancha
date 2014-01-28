package net.ichigotake.yancha.common.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.ichigotake.colorfulsweets.lib.view.ViewHolder;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.user.ProfileImageViewHelper;

import java.text.SimpleDateFormat;

public class ChatMessageView extends RelativeLayout implements ViewHolder {

    final private TextView mNickname;
    final private ImageView mProfileImage;
    final private TextView mMessage;
    final private TextView mPlusplus;
    final private TextView mTimestamp;
    final private ProfileImageViewHelper mImageHelper;

    public ChatMessageView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.yc_common_message_cell, this);
        mNickname = (TextView) findViewById(R.id.messageCellNickname);
        mProfileImage = (ImageView) findViewById(R.id.messageCellProfileImageUrl);
        mMessage = (TextView) findViewById(R.id.messageCellMessage);
        mPlusplus = (TextView) findViewById(R.id.messageCellPlusplus);
        mTimestamp = (TextView) findViewById(R.id.messageCellTimestamp);
        mImageHelper = new ProfileImageViewHelper();
    }

    public void setMessage(CharSequence message) {
        mMessage.setText(message);
    }

    public void setNickname(String nickname) {
        mNickname.setText(nickname);
    }

    public void setTimestamp(long timestamp) {
        setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp));
    }

    public void setTimestamp(CharSequence timestamp) {
        mTimestamp.setText(timestamp);
    }

    public void setPlusplus(int plusplus) {
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
            mPlusplus.setVisibility(View.VISIBLE);
            mPlusplus.setText(plusplusText);
        } else {
            mPlusplus.setText("");
            mPlusplus.setVisibility(View.GONE);
        }

    }

    public void setProfileImageUrl(String url) {
        mImageHelper.setDrawable(mProfileImage, url);
    }
}
