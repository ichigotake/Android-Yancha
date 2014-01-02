package net.ichigotake.yancha.common.ui;

import net.ichigotake.colorfulsweets.lib.widget.ViewConnector;
import net.ichigotake.yancha.common.message.ChatMessageViewHolder;
import net.ichigotake.yancha.sdk.model.ChatMessage;

/**
 * 発言とビューを繫ぐ
 */
public interface MessageViewConnector extends ViewConnector<ChatMessageViewHolder, ChatMessage> {

    boolean isEnabled(int position, ChatMessage item);

    int getItemPosition(int position);
}
