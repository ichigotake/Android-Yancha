package net.ichigotake.yancha.common.chat;

import android.util.SparseArray;

import net.ichigotake.yancha.sdk.model.ChatMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 発言一覧をタグ毎にプールしておくもの
 */
public class ChatMessageTagMap {

    final private List<String> mJoinTagList;

    final private Map<String, SparseArray<ChatMessage>> mAllMessages;

    public ChatMessageTagMap() {
        mJoinTagList = new ArrayList<String>();
        mAllMessages = new HashMap<String, SparseArray<ChatMessage>>();
    }

    public boolean exists(ChatMessage message) {
        for (SparseArray<ChatMessage> tagedMessages : mAllMessages.values()) {
            ChatMessage containsMessage = tagedMessages.get(message.getId());
            if (null != containsMessage) {
                return true;
            }
        }
        return false;
    }

    public void add(ChatMessage message) {
        for (String tag : message.getTags()) {
            if (! mAllMessages.containsKey(tag)) {
                mAllMessages.put(tag, new SparseArray<ChatMessage>());
            }
            SparseArray<ChatMessage> messages = mAllMessages.get(tag);
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

    public void update(ChatMessage message) {
        for (SparseArray<ChatMessage> tagedMessages : mAllMessages.values()) {
            ChatMessage containsMessage = tagedMessages.get(message.getId());
            if (null != containsMessage) {
                tagedMessages.remove(message.getId());
                tagedMessages.put(message.getId(), message);
            }
        }
    }

    public void remove(ChatMessage message) {
        for (SparseArray<ChatMessage> tagedMessages : mAllMessages.values()) {
            ChatMessage containsMessage = tagedMessages.get(message.getId());
            if (null != containsMessage) {
                tagedMessages.remove(message.getId());
            }
        }
    }
}
