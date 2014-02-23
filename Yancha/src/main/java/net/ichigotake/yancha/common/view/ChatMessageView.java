package net.ichigotake.yancha.common.view;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import net.ichigotake.colorfulsweets.common.view.ViewHolder;
import net.ichigotake.yancha.R;
import net.ichigotake.yancha.YanchaApp;
import net.ichigotake.yancha.common.api.RequestManager;

import java.util.Calendar;

public class ChatMessageView extends RelativeLayout implements ViewHolder {

    private TextView mNickname;
    private NetworkImageView mProfileImage;
    private TextView mMessage;
    private TextView mPlusplus;
    private TextView mTimestamp;

    public ChatMessageView(Context context) {
        super(context);
        init(context);
    }

    public ChatMessageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChatMessageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.yc_common_message_cell, this);
        mNickname = (TextView) findViewById(R.id.messageCellNickname);
        mProfileImage = (NetworkImageView) findViewById(R.id.messageCellProfileImageUrl);
        mMessage = (TextView) findViewById(R.id.messageCellMessage);
        mPlusplus = (TextView) findViewById(R.id.messageCellPlusplus);
        mTimestamp = (TextView) findViewById(R.id.messageCellTimestamp);
    }

    public void setMessage(CharSequence message) {
        mMessage.setText(message);
    }

    public void setNickname(String nickname) {
        mNickname.setText(nickname);
    }

    public void setTimestamp(long timestamp) {
        final Calendar calendar = Calendar.getInstance(YanchaApp.LOCALE);
        calendar.setTimeInMillis(timestamp);
        setTimestamp(DateFormat.format("yyyy-MM-dd kk:mm:ss", calendar));
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
        mProfileImage.setImageUrl(url, RequestManager.getImageLoader());
    }
}
