package net.ichigotake.yancha.common.message;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.ui.ViewHolder;

/**
 * 発言のビューホルダー
 */
public class PostMessageViewHolder implements ViewHolder {

    final private View mContentView;

    final private TextView mNickname;

    final private ImageView mProfileImage;

    final private TextView mMessage;

    final private TextView mPlusplus;

    final private TextView mTimestamp;

    // TODO 12/21までに実装する
    //final private ImageView mPreviewView;

    PostMessageViewHolder(View view) {
        mContentView = view;
        mNickname = (TextView) view.findViewById(R.id.messageCellNickname);
        mProfileImage = (ImageView) view.findViewById(R.id.messageCellProfileImageUrl);;
        mMessage = (TextView) view.findViewById(R.id.messageCellMessage);
        mPlusplus = (TextView) view.findViewById(R.id.messageCellPlusplus);
        mTimestamp = (TextView) view.findViewById(R.id.messageCellTimestamp);
        //mPreviewView = (ImageView) view.findViewById(R.id.messageCellPreview);
    }

    public View getContentView() {
        return mContentView;
    }

    TextView getNicknameView() {
        return mNickname;
    }

    ImageView getProfileImageView() {
        return mProfileImage;
    }

    TextView getMessageView() {
        return mMessage;
    }

    TextView getPlusplusView() {
        return mPlusplus;
    }

    TextView getTimestampView() {
        return mTimestamp;
    }

    //ImageView getPreviewView() {
    //    return mPreviewView;
    //}
}
