package net.ichigotake.yancha.chat;

import android.app.Activity;
import android.view.View;

import net.ichigotake.yancha.common.api.socketio.YanchaEmitter;
import net.ichigotake.yancha.common.ui.ViewContainer;
import net.ichigotake.yancha.common.user.AppUser;
import net.ichigotake.yanchasdk.lib.model.ChatUser;
import net.ichigotake.yanchasdk.lib.model.JoinTagList;
import net.ichigotake.yanchasdk.lib.model.JoinUsers;
import net.ichigotake.yanchasdk.lib.model.PostMessage;

/**
 * �`���b�g��ʂ�\������
 */
public class ChatContainer implements ViewContainer {

	final private PostMessageContainer mPostMessageContainer;
	
	final private MessagePostContainer mMessagePostContainer;
	
	final private JoinTagContainer mJoinTagContainer;
	
	final private JoinUsersContainer mJoinUsersContainer;

    final private AppUser mMyself;
	
	public ChatContainer(Activity activity, YanchaEmitter emitter, View view) {
		mPostMessageContainer = new PostMessageContainer(activity, view, emitter);
		mMessagePostContainer = new MessagePostContainer(view, emitter);
		mJoinTagContainer = new JoinTagContainer(view, getDefaultTagList());
		mJoinUsersContainer = new JoinUsersContainer(activity, view);
        mMyself = new AppUser(activity);
	}

	public JoinTagList getTagList() {
		return mJoinTagContainer.getTagList();
	}
	
	public void addMessage(PostMessage message) {
		mPostMessageContainer.addMessage(message);
	}

    public void removeMessage(PostMessage message) {
        mPostMessageContainer.removeMessage(message);
    }
	
	public void updateJoinUsers(JoinUsers users) {
        updateMyself(users);
		mJoinUsersContainer.update(users);
	}

    public void updateMyself(JoinUsers users) {
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
	final private JoinTagList getDefaultTagList() {
		JoinTagList tags = new JoinTagList();
		tags.add("PUBLIC");
		tags.add("FROMLINGR");
		tags.add("PRECUDA");
		tags.add("KANKORE");
		tags.add("GITHUB");
		return tags;
	}

}
