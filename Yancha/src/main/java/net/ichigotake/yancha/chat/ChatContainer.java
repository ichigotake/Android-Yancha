package net.ichigotake.yancha.chat;

import android.app.Activity;
import android.view.View;

import net.ichigotake.yancha.common.api.socketio.YanchaEmitter;
import net.ichigotake.yancha.common.widget.ViewContainer;
import net.ichigotake.yancha.common.chat.AppUser;
import net.ichigotake.yancha.sdk.model.ChatUsers;
import net.ichigotake.yancha.sdk.model.ChatMessage;
import net.ichigotake.yancha.sdk.model.ChatTags;
import net.ichigotake.yancha.sdk.model.ChatUser;

/**
 * チャット画面を表示
 */
public class ChatContainer implements ViewContainer {

    final private ChatMessageContainer mChatMessageContainer;
    
    final private ChatMessagePostContainer mChatMessagePostContainer;
    
    final private ChatTagContainer mChatTagContainer;
    
    final private ChatUsersContainer mChatUsersContainer;

    final private AppUser mMyself;
    
    public ChatContainer(Activity activity, YanchaEmitter emitter, View view) {
        mChatMessageContainer = new ChatMessageContainer(activity, view, emitter);
        mChatTagContainer = new ChatTagContainer(view, getDefaultTagList());
        mChatMessagePostContainer = new ChatMessagePostContainer(view, emitter, mChatTagContainer);
        mChatUsersContainer = new ChatUsersContainer(activity, view);
        mMyself = new AppUser();
    }

    public void initialize() {
        mChatMessageContainer.initialize();
    }

    public ChatTags getTagList() {
        return mChatTagContainer.getTags();
    }
    
    public void addMessage(ChatMessage message) {
        mChatMessageContainer.addMessage(message);
    }

    public void removeMessage(ChatMessage message) {
        mChatMessageContainer.removeMessage(message);
    }
    
    public void updateJoinUsers(ChatUsers users) {
        updateMyself(users);
        mChatUsersContainer.update(users);
    }

    public void updateMyself(ChatUsers users) {
        for (ChatUser user : users.toList()) {
            if (mMyself.isMyself(user)) {
                updateMyself(user);
            }
        }
    }

    public void updateMyself(ChatUser user) {
        mMyself.update(user);
    }

    private ChatTags getDefaultTagList() {
        ChatTags tags = new ChatTags();
        tags.add("PUBLIC");
        tags.add("FROMLINGR");
        return tags;
    }

}
