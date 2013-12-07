package net.ichigotake.yancha.chat;

import android.app.Activity;
import android.view.View;

import net.ichigotake.yancha.common.api.socketio.YanchaEmitter;
import net.ichigotake.yancha.common.ui.ViewContainer;
import net.ichigotake.yancha.common.user.AppUser;
import net.ichigotake.yanchasdk.lib.model.ChatUsers;
import net.ichigotake.yanchasdk.lib.model.ChatMessage;
import net.ichigotake.yanchasdk.lib.model.ChatTagList;
import net.ichigotake.yanchasdk.lib.model.ChatUser;

/**
 * �`���b�g��ʂ�\������
 */
public class ChatContainer implements ViewContainer {

	final private ChatMessageContainer mChatMessageContainer;
	
	final private MessagePostContainer mMessagePostContainer;
	
	final private ChatTagContainer mChatTagContainer;
	
	final private ChatUsersContainer mChatUsersContainer;

    final private AppUser mMyself;
	
	public ChatContainer(Activity activity, YanchaEmitter emitter, View view) {
		mChatMessageContainer = new ChatMessageContainer(activity, view, emitter);
		mMessagePostContainer = new MessagePostContainer(view, emitter);
		mChatTagContainer = new ChatTagContainer(view, getDefaultTagList());
		mChatUsersContainer = new ChatUsersContainer(activity, view);
        mMyself = new AppUser(activity);
	}

	public ChatTagList getTagList() {
		return mChatTagContainer.getTagList();
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

    /**
     * とりあえずのデバッグ用
     * @return
     */
	final private ChatTagList getDefaultTagList() {
		ChatTagList tags = new ChatTagList();
		tags.add("PUBLIC");
		tags.add("FROMLINGR");
		tags.add("PRECUDA");
		tags.add("KANKORE");
		tags.add("GITHUB");
		return tags;
	}

}
