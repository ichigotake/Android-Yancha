package net.ichigotake.yancha.common.ui;

import net.ichigotake.yancha.common.message.PostMessageViewHolder;
import net.ichigotake.yanchasdk.lib.model.PostMessage;

/**
 * 発言とビューを繫ぐ
 */
public interface MessageViewConnector extends ViewConnector<PostMessageViewHolder, PostMessage> {
}
