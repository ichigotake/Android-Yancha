package net.ichigotake.yancha.common.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import net.ichigotake.colorfulsweets.common.view.ViewHolder;
import net.ichigotake.yancha.R;

import java.text.SimpleDateFormat;

public class ChatMessageView extends RelativeLayout implements ViewHolder {

    final private TextView mNickname;
    final private NetworkImageView mProfileImage;
    final private TextView mMessage;
    final private TextView mPlusplus;
    final private TextView mTimestamp;
    final private ImageLoader mImageLoader;

    public ChatMessageView(Context context, ImageLoader imageLoader) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.yc_common_message_cell, this);
        mNickname = (TextView) findViewById(R.id.messageCellNickname);
        mProfileImage = (NetworkImageView) findViewById(R.id.messageCellProfileImageUrl);
        mMessage = (TextView) findViewById(R.id.messageCellMessage);
        mPlusplus = (TextView) findViewById(R.id.messageCellPlusplus);
        mTimestamp = (TextView) findViewById(R.id.messageCellTimestamp);
        mImageLoader = imageLoader;
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
        mProfileImage.setImageUrl(url, mImageLoader);
    }
}
