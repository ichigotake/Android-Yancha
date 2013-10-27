package net.ichigotake.yancha.chat;

import java.util.ArrayList;
import java.util.List;

import net.ichigotake.yancha.R;
import net.ichigotake.yancha.common.ChatStatus;
import net.ichigotake.yancha.common.message.MessageListAdapter;
import net.ichigotake.yancha.common.message.SendMessage;
import net.ichigotake.yancha.common.message.SendMessageListener;
import net.ichigotake.yancha.common.ui.ViewContainer;
import net.ichigotake.yancha.common.user.JoinTagListStorage;
import net.ichigotake.yanchasdk.lib.model.JoinTagList;
import net.ichigotake.yanchasdk.lib.model.JoinUserFacrory;
import net.ichigotake.yanchasdk.lib.model.PostMessageBuilder.PostMessage;
import net.ichigotake.yanchasdk.lib.model.PostMessageFactory;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.base.Optional;
import com.google.common.eventbus.EventBus;
import com.haarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;

/**
 * チャット画面を出力するビューコンテナ
 */
public class ChatContainer implements ViewContainer {

	final private EventBus eventBus = new EventBus();
	
	final private Fragment fragment;
	
	final private Handler handler;
	
	private ListView messageListView;
	
	private SparseArray<PostMessage> messages;
	
	private MessageListAdapter messageListAdapter;
	
	private TextView chatJoinUsersCountView;
	
	private JoinUsersContainer mJoinUsersContainer;
	
	private TextView statusView;
	
	private TextView chatSelectedTagSelected;
	
	private JoinTagList mTags;
	
	public ChatContainer(Fragment fragment) {
		this.fragment = fragment;
		this.messages = new SparseArray<PostMessage>();
		handler = new Handler();
	}

	public void initializeView(View view) {
		chatJoinUsersCountView = (TextView) view.findViewById(R.id.chatJoinUsersCount);

		mJoinUsersContainer = new JoinUsersContainer(fragment.getActivity(), view);
		
		List<PostMessage> messages = new ArrayList<PostMessage>();
		messageListAdapter = new MessageListAdapter(fragment.getActivity(), messages);
		ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(messageListAdapter);
		messageListView = (ListView) view.findViewById(R.id.messageList);
		animationAdapter.setAbsListView(messageListView);
		messageListView.setAdapter(animationAdapter);
		
		mTags = getDefaultTagList();
		JoinTagListStorage tagStorage = new JoinTagListStorage(view.getContext());
		tagStorage.putAll(mTags);
		
		final EditText viewMessage = (EditText) view.findViewById(R.id.chatSendMessageText);
		Button viewSubmit = (Button) view.findViewById(R.id.chatSendMessageSend);
		viewSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String message = viewMessage.getText().toString();
				if (message.isEmpty()) {
					return ;
				}
				
				eventBus.post(new SendMessage(message));
				//TODO 発言失敗等での再利用用に発言ヒストリーを取っておきたい
				viewMessage.setText("");
			}
		});
		
		statusView = (TextView) view.findViewById(R.id.chatStatus);
		
		chatSelectedTagSelected = (TextView) view.findViewById(R.id.chatSelectedTagSelected);
		chatSelectedTagSelected.setOnClickListener(new SelectedTagOnClickListener(mTags));
	}
	
	public JoinTagList getTagList() {
		return mTags;
	}
	
	public void updateStatus(ChatStatus status) {
		final Optional<Integer> message = ChatStatus.valueOfMessage(status);
		if (message.isPresent()) {
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					statusView.setText(message.get());
				}
			});
		}
	}
	
	public void registerListener(SendMessageListener listener) {
		eventBus.register(listener);
	}
	
	public void addMessage(JSONObject json) {		
		try {
			if (messages.get(json.getInt("id")) != null) {
				return ;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return ;
		}
		
		while (messageListAdapter.getCount() > 100) {
			messageListAdapter.remove(messageListAdapter.getItem(0));
		}
		
		PostMessage message;
		try {
			message = PostMessageFactory.create(json);
		} catch (JSONException e) {
			e.printStackTrace();
			return ;
		}
		
		messages.put(message.getId(), message);
		messageListAdapter.add(message);
	
		messageListAdapter.notifyDataSetChanged();
		messageListView.invalidateViews();
		
		scrollBottomWhenNewMessage();
	}
	
	public void updateJoinUsers(String response) {
		List<String> users;
		try {
			users = JoinUserFacrory.createNicknameList(response);
		} catch (JSONException e) {
			users = new ArrayList<String>();
		}
		
		mJoinUsersContainer.setUsers(users);
		updateJoinUsersCount(users.size());
	}
	
	public void updateJoinUsersCount(int count) {
		chatJoinUsersCountView.setText(count + "人");
	}
	
	private void scrollBottomWhenNewMessage() {
		messageListView.post(new Runnable() {			
			@Override
			public void run() {
				final int bottomPosition = messages.size()-1;
				if (bottomPosition == messageListView.getLastVisiblePosition()+1) {
					messageListView.setSelection(bottomPosition);
				}
			}
		});
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
