package net.ichigotake.yancha.common.chat;

import android.util.SparseArray;

import net.ichigotake.yancha.sdk.model.ChatMessage;

/**
 * メッセージ一覧
 */
public class ChatMessageList {
    
    /**
     * TODO 一意なメッセージ一覧を保持する方法を考える
     */
    private SparseArray<ChatMessage> messageList;

    public SparseArray<ChatMessage> getMessageList() {
        return messageList;
    }

    public void setMessageList(SparseArray<ChatMessage> messageList) {
        this.messageList = messageList;
    }

}
