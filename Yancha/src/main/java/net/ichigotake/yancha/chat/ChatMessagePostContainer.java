package net.ichigotake.yancha.chat;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.api.socketio.YanchaEmitter;
import net.ichigotake.yancha.common.ui.ViewContainer;

/**
 * メッセージをポストする
 */
class ChatMessagePostContainer implements ViewContainer {

    final private EditText mMessageView;
    final private ChatTagContainer mTagContainer;
    final private CheckBox mInputModeSwitcher;
    
    ChatMessagePostContainer(View view, YanchaEmitter emitter, ChatTagContainer tagContainer) {
        mMessageView = (EditText) view.findViewById(R.id.chatSendMessageText);

        mTagContainer = tagContainer;
        ChatMessagePost post = new ChatMessagePost(emitter, mMessageView, mTagContainer);
        mInputModeSwitcher = (CheckBox) view.findViewById(R.id.chatInputModeSwitcher);
        mInputModeSwitcher.setOnClickListener(new OnInputModeClickListener(post));
        Button viewSubmit = (Button) view.findViewById(R.id.chatSendMessageSend);
        viewSubmit.setOnClickListener(new OnSubmitButtonClickListener(post));
    }

}
