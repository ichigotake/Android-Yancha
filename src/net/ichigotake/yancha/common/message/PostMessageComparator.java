package net.ichigotake.yancha.common.message;


import net.ichigotake.yanchasdk.lib.model.PostMessage;

import java.util.Comparator;

/**
 * 発言一覧をソートする {@link java.util.Comparator}
 */
public class PostMessageComparator implements Comparator<PostMessage> {

    @Override
    public int compare(PostMessage postMessage, PostMessage postMessage2) {
        return (postMessage.getId() > postMessage2.getId()) ? 1: -1;
    }
}
