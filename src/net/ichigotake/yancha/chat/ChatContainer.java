package net.ichigotake.yancha.chat;

import net.ichigotake.yancha.common.ChatStatus;
import net.ichigotake.yancha.common.api.YanchaEmitter;
import net.ichigotake.yancha.common.ui.ViewContainer;
import net.ichigotake.yanchasdk.lib.model.JoinTagList;
import net.ichigotake.yanchasdk.lib.model.PostMessageBuilder.PostMessage;
import android.app.Activity;
import android.view.View;

/**
 * チャット画面を表示する
 */
public class ChatContainer implements ViewContainer {

	final private MessageContainer mMessageContainer;
	
	final private MessagePostContainer mMessagePostContainer;
	
	final private JoinTagContainer mJoinTagContainer;
	
	final private JoinUsersContainer mJoinUsersContainer;
	
	final private StatusContainer mStatusContainer;
	
	public ChatContainer(Activity activity, YanchaEmitter emitter, View view) {
		mMessageContainer = new MessageContainer(activity, view);
		mMessagePostContainer = new MessagePostContainer(view, emitter);
		mJoinTagContainer = new JoinTagContainer(view, getDefaultTagList());
		mJoinUsersContainer = new JoinUsersContainer(activity, view);
		mStatusContainer = new StatusContainer(view);
	}

	public JoinTagList getTagList() {
		return mJoinTagContainer.getTagList();
	}
	
	void addMessage(PostMessage message) {
		mMessageContainer.addMessage(message);
	}
	
	public void updateStatus(ChatStatus status) {
		mStatusContainer.updateStatus(status);
	}
	
	public void updateJoinUsers(String response) {
		mJoinUsersContainer.update(response);
	}
	
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
