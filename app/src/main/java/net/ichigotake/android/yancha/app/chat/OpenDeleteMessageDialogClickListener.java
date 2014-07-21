package net.ichigotake.android.yancha.app.chat;

import android.app.FragmentManager;
import android.view.View;

import net.ichigotake.yancha.sdk.chat.ChatMessage;

public final class OpenDeleteMessageDialogClickListener implements View.OnClickListener {

    private final FragmentManager fragmentManager;
    private final ChatMessage chatMessage;

    public OpenDeleteMessageDialogClickListener(FragmentManager fragmentManager, ChatMessage chatMessage) {
        this.fragmentManager = fragmentManager;
        this.chatMessage = chatMessage;
    }

    @Override
    public void onClick(View v) {
        DeleteMessageDialogFragment.open(fragmentManager, chatMessage);
    }
}
