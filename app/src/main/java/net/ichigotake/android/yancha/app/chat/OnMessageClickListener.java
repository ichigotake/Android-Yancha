package net.ichigotake.android.yancha.app.chat;

import net.ichigotake.yancha.sdk.chat.ChatMessage;

public interface OnMessageClickListener {

    void onClick(int position, ChatMessage item);
}
