package net.ichigotake.yancha.common.message;

import android.util.SparseArray;

import net.ichigotake.yanchasdk.lib.model.PostMessageBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 発言一覧をタグ毎にプールしておくもの
 */
public class PostMessageListTagMap {

    final private List<String> mJoinTagList;

    final private Map<String, SparseArray<PostMessageBuilder.PostMessage>> mAllMessages;

    public PostMessageListTagMap() {
        mJoinTagList = new ArrayList<String>();
        mAllMessages = new HashMap<String, SparseArray<PostMessageBuilder.PostMessage>>();
    }

    public boolean exists(PostMessageBuilder.PostMessage message) {
        for (SparseArray<PostMessageBuilder.PostMessage> tagedMessages : mAllMessages.values()) {
            PostMessageBuilder.PostMessage containsMessage = tagedMessages.get(message.getId());
            if (null != containsMessage) {
                return true;
            }
        }
        return false;
    }

    public void add(PostMessageBuilder.PostMessage message) {
        for (String tag : message.getTags()) {
            if (! mAllMessages.containsKey(tag)) {
                mAllMessages.put(tag, new SparseArray<PostMessageBuilder.PostMessage>());
            }
            SparseArray<PostMessageBuilder.PostMessage> messages = mAllMessages.get(tag);
            messages.put(message.getId(), message);
            if (containsTag(tag)) {
                messages.put(message.getId(), message);
            }
        }
    }

    public boolean containsTag(List<String> tags) {
        for (String tag : tags) {
            if (mJoinTagList.contains(tag)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsTag(String tag) {
        return mJoinTagList.contains(tag);
    }

    public void update(PostMessageBuilder.PostMessage message) {
        for (SparseArray<PostMessageBuilder.PostMessage> tagedMessages : mAllMessages.values()) {
            PostMessageBuilder.PostMessage containsMessage = tagedMessages.get(message.getId());
            if (null != containsMessage) {
                tagedMessages.remove(message.getId());
                tagedMessages.put(message.getId(), message);
            }
        }
    }
}
