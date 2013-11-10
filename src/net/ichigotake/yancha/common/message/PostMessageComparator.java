package net.ichigotake.yancha.common.message;

import net.ichigotake.yanchasdk.lib.model.PostMessageBuilder;

import java.util.Comparator;

/**
 * 発言一覧をソートする {@link java.util.Comparator}
 */
public class PostMessageComparator implements Comparator<PostMessageBuilder.PostMessage> {

    @Override
    public int compare(PostMessageBuilder.PostMessage postMessage, PostMessageBuilder.PostMessage postMessage2) {
        return (postMessage.getId() > postMessage2.getId()) ? 1: -1;
    }
}
