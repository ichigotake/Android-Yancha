package net.ichigotake.yancha.chat;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.api.socketio.YanchaEmitter;
import net.ichigotake.yancha.common.ui.ViewContainer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * メッセージをポストする
 */
class MessagePostContainer implements ViewContainer {

    final private EditText mMessageView;
    
    final private CheckBox mInputModeSwitcher;
    
    MessagePostContainer(View view, YanchaEmitter emitter) {
        mMessageView = (EditText) view.findViewById(R.id.chatSendMessageText);

        MessagePost post = new MessagePost(emitter, mMessageView);
        mInputModeSwitcher = (CheckBox) view.findViewById(R.id.chatInputModeSwitcher);
        mInputModeSwitcher.setOnClickListener(new OnInputModeClickListener(post));
        Button viewSubmit = (Button) view.findViewById(R.id.chatSendMessageSend);
        viewSubmit.setOnClickListener(new OnSubmitButtonClickListener(post));
    }

}
