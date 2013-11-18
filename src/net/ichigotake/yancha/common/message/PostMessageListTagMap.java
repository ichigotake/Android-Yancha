package net.ichigotake.yancha.common.message;

import android.util.SparseArray;

import net.ichigotake.yanchasdk.lib.model.PostMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 発言一覧をタグ毎にプールしておくもの
 */
public class PostMessageListTagMap {

    final private List<String> mJoinTagList;

    final private Map<String, SparseArray<PostMessage>> mAllMessages;

    public PostMessageListTagMap() {
        mJoinTagList = new ArrayList<String>();
        mAllMessages = new HashMap<String, SparseArray<PostMessage>>();
    }

    public boolean exists(PostMessage message) {
        for (SparseArray<PostMessage> tagedMessages : mAllMessages.values()) {
            PostMessage containsMessage = tagedMessages.get(message.getId());
            if (null != containsMessage) {
                return true;
            }
        }
        return false;
    }

    public void add(PostMessage message) {
        for (String tag : message.getTags()) {
            if (! mAllMessages.containsKey(tag)) {
                mAllMessages.put(tag, new SparseArray<PostMessage>());
            }
            SparseArray<PostMessage> messages = mAllMessages.get(tag);
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

    public void update(PostMessage message) {
        for (SparseArray<PostMessage> tagedMessages : mAllMessages.values()) {
            PostMessage containsMessage = tagedMessages.get(message.getId());
            if (null != containsMessage) {
                tagedMessages.remove(message.getId());
                tagedMessages.put(message.getId(), message);
            }
        }
    }

    public void remove(PostMessage message) {
        for (SparseArray<PostMessage> tagedMessages : mAllMessages.values()) {
            PostMessage containsMessage = tagedMessages.get(message.getId());
            if (null != containsMessage) {
                tagedMessages.remove(message.getId());
            }
        }
    }
}
