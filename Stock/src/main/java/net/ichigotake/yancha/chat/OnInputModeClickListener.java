package net.ichigotake.yancha.chat;

import android.view.View;
import android.widget.CheckBox;

/**
 * 入力モードを切り替えるクリックリスナ
 */
class OnInputModeClickListener implements View.OnClickListener {

    final private ChatMessagePost mChatMessagePost;

    public OnInputModeClickListener(ChatMessagePost messagePost) {
        mChatMessagePost = messagePost;
    }

    @Override
    public void onClick(View view) {
        CheckBox checkBox = (CheckBox) view;
        if (checkBox.isChecked()) {
            mChatMessagePost.setMultipleLineInputMode();
        } else {
            mChatMessagePost.setSingleLineInputMode();
        }
    }

}


