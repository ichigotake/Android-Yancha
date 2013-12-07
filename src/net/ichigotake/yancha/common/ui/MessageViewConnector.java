package net.ichigotake.yancha.common.ui;

import net.ichigotake.yancha.common.message.ChatMessageViewHolder;
import net.ichigotake.yanchasdk.lib.model.ChatMessage;

/**
 * 発言とビューを繫ぐ
 */
public interface MessageViewConnector extends ViewConnector<ChatMessageViewHolder, ChatMessage> {
}
