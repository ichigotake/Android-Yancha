package net.ichigotake.yancha.chat;

import android.view.View;
import android.widget.CheckBox;

/**
 * 入力モードを切り替えるクリックリスナ
 */
class OnInputModeClickListener implements View.OnClickListener {

    final private MessagePost mMessagePost;

    public OnInputModeClickListener(MessagePost messagePost) {
        mMessagePost = messagePost;
    }

    @Override
    public void onClick(View view) {
        CheckBox checkBox = (CheckBox) view;
        if (checkBox.isChecked()) {
            mMessagePost.setMultipleLineInputMode();
        } else {
            mMessagePost.setSingleLineInputMode();
        }
    }

}


