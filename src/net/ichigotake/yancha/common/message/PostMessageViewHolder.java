package net.ichigotake.yancha.common.message;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.ui.ViewHolder;

/**
 * 発言のビューホルダー
 */
public class PostMessageViewHolder implements ViewHolder {

    final private TextView mSeparatorView;

    final private ViewGroup mContentView;

    final private TextView mNickname;

    final private ImageView mProfileImage;

    final private TextView mMessage;

    final private TextView mPlusplus;

    final private TextView mTimestamp;

    // TODO 12/21までに実装する
    //final private ImageView mPreviewView;

    PostMessageViewHolder(View view) {
        mSeparatorView = (TextView) view.findViewById(R.id.messageCellCountSeparator);
        mContentView = (ViewGroup) view.findViewById(R.id.common_message_cell);
        mNickname = (TextView) view.findViewById(R.id.messageCellNickname);
        mProfileImage = (ImageView) view.findViewById(R.id.messageCellProfileImageUrl);;
        mMessage = (TextView) view.findViewById(R.id.messageCellMessage);
        mPlusplus = (TextView) view.findViewById(R.id.messageCellPlusplus);
        mTimestamp = (TextView) view.findViewById(R.id.messageCellTimestamp);
        //mPreviewView = (ImageView) view.findViewById(R.id.messageCellPreview);
    }

    public TextView getSeparatorView() {
        return mSeparatorView;
    }

    public ViewGroup getContentView() {
        return mContentView;
    }

    public TextView getNicknameView() {
        return mNickname;
    }

    public ImageView getProfileImageView() {
        return mProfileImage;
    }

    public TextView getMessageView() {
        return mMessage;
    }

    public TextView getPlusplusView() {
        return mPlusplus;
    }

    public TextView getTimestampView() {
        return mTimestamp;
    }

    //ImageView getPreviewView() {
    //    return mPreviewView;
    //}
}
