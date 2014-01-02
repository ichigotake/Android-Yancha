package net.ichigotake.yancha.common.ui;

import android.content.Context;
import android.widget.TextView;

import net.ichigotake.yancha.R;

/**
 * 発言のセパレータをセットする
 */
public class MessageSeparator {

    final private Context mContext;

    public MessageSeparator(Context context) {
        mContext = context;
    }

    public void update(TextView label, int count) {
        label.setText(getText(count));
    }

    private String getText(int count) {
        return mContext.getString(R.string.yc_count_separator, count);
    }
}
